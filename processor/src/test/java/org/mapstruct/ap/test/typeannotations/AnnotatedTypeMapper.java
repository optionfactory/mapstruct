/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.typeannotations;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnotatedTypeMapper {

    AnnotatedTypeMapper INSTANCE = Mappers.getMapper( AnnotatedTypeMapper.class );

    NotAnnotatedTypeProperty toNotAnnotated(AnnotatedTypeProperty car);

    AnnotatedTypeProperty toAnnotated(AnnotatedTypeProperty car);

    NotAnnotatedTypeProperty toNotAnnotated(NotAnnotatedTypeProperty car);

    AnnotatedTypeProperty toAnnotated(NotAnnotatedTypeProperty car);

    default @TestTypeAnnotation String fromAnnotatedToAnnotated(@TestTypeAnnotation String x) {
        return "fromAnnotatedToAnnotated";
    }

    default String fromAnnotated(@TestTypeAnnotation String x) {
        return "fromAnnotated";
    }

    default @TestTypeAnnotation String toAnnotated(String x) {
        return "toAnnotated";
    }

    default String noAnnotations(String x) {
        return "noAnnotations";
    }
}
