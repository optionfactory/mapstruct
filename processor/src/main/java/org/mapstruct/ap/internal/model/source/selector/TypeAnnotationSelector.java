/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.internal.model.source.selector;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.lang.model.type.DeclaredType;
import org.mapstruct.ap.internal.model.common.Parameter;
import org.mapstruct.ap.internal.model.common.Type;
import org.mapstruct.ap.internal.model.source.Method;

public class TypeAnnotationSelector implements MethodSelector {

    @Override
    public <T extends Method> List<SelectedMethod<T>> getMatchingMethods(Method mappingMethod,
            List<SelectedMethod<T>> methods,
            List<Type> sourceTypes,
            Type targetType,
            SelectionCriteria criteria) {

        final List<Set<DeclaredType>> sourceTypesAnnotations = sourceTypes.stream().map( sourceType
                -> sourceType
                        .getTypeMirror()
                        .getAnnotationMirrors()
                        .stream()
                        .map( m -> m.getAnnotationType() )
                        .collect( Collectors.toSet() ) )
                .collect( Collectors.toList() );

        final Set<DeclaredType> targetTypeAnnotations = targetType.getTypeMirror().getAnnotationMirrors()
                .stream().map( m -> m.getAnnotationType() ).collect( Collectors.toSet() );

        final List<Candidate<SelectedMethod<T>>> compatibleMethods = methods.stream()
                .filter( method -> {
                    // source type match
                    List<Parameter> parameters = method.getMethod().getSourceParameters();
                    for (int i = 0; i < sourceTypesAnnotations.size(); i++) {
                        Set<DeclaredType> sourceTypeAnnotations = sourceTypesAnnotations.get( i );
                        Set<DeclaredType> methodParameterTypeAnnotations = parameters.get( i )
                            .getType()
                            .getTypeMirror()
                            .getAnnotationMirrors()
                            .stream()
                            .map( m -> m.getAnnotationType() )
                            .collect( Collectors.toSet() );
                        if (!sourceTypeAnnotations.containsAll( methodParameterTypeAnnotations )) {
                            return false;
                        }
                    }
                    return true;
                } ).filter( method -> {
                    // target type match
                    Type returnType = method.getMethod().getResultType();
                    Set<DeclaredType> returnTypeAnnotations = returnType
                        .getTypeMirror()
                        .getAnnotationMirrors()
                        .stream()
                        .map( m -> m.getAnnotationType() )
                        .collect( Collectors.toSet() );
                    return targetTypeAnnotations.containsAll( returnTypeAnnotations );
                } )
                .map( m -> new Candidate<>( m, specificity( m, sourceTypes.size() ) ) )
                .collect( Collectors.toList() );
        final long maxScore = compatibleMethods.stream().mapToLong( c -> c.getScore() ).max().orElse( 0L );
        return compatibleMethods.stream()
            .filter( c -> c.getScore() == maxScore )
            .map( c -> c.getCandidate() )
            .collect( Collectors.toList() );
    }

    private static class Candidate<T> {
        private final T candidate;
        private final long score;

        Candidate(T candidate, long score) {
            this.candidate = candidate;
            this.score = score;
        }

        public T getCandidate() {
            return candidate;
        }

        public long getScore() {
            return score;
        }
    }

    private long specificity(SelectedMethod<?> method, int sourceTypesCount) {
        final List<Parameter> parameters = method.getMethod().getSourceParameters();
        final long matchingParameterTypeAnnotations = parameters.subList( 0, sourceTypesCount ).stream()
                .mapToLong( p -> p.getType()
                    .getTypeMirror()
                    .getAnnotationMirrors()
                    .stream()
                    .map( m -> m.getAnnotationType() )
                    .distinct()
                    .count() )
                .sum();
        final Type returnType = method.getMethod().getResultType();
        final long matchingReturnTypeAnnotations = returnType.getTypeMirror().getAnnotationMirrors().stream()
            .map( m -> m.getAnnotationType() )
            .distinct()
            .count();
        return matchingParameterTypeAnnotations + matchingReturnTypeAnnotations;
    }
}
