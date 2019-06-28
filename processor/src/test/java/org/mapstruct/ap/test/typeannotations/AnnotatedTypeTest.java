/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.typeannotations;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;

@WithClasses({
    AnnotatedTypeProperty.class,
    NotAnnotatedTypeProperty.class,
    TestTypeAnnotation.class,
    AnnotatedTypeMapper.class})
@RunWith(AnnotationProcessorTestRunner.class)
public class AnnotatedTypeTest {

    @Test
    public void filtersOutBasedOnExpectedParametersTypeAnnotations() {
        final NotAnnotatedTypeProperty notAnnotated = AnnotatedTypeMapper.INSTANCE.toNotAnnotated(
            new AnnotatedTypeProperty() );
        assertThat( notAnnotated.getProp() ).isEqualTo( "fromAnnotated" );
    }

    @Test
    public void filtersOutBasedOnExpectedReturnTypeAnnotations() {
        final AnnotatedTypeProperty annotated = AnnotatedTypeMapper.INSTANCE.toAnnotated(
            new NotAnnotatedTypeProperty() );
        assertThat( annotated.getProp() ).isEqualTo( "toAnnotated" );

    }

    @Test
    public void filtersOutBasedOnBothParametersAndReturnTypeAnnotations() {
        final AnnotatedTypeProperty annotated = AnnotatedTypeMapper.INSTANCE.toAnnotated(
            new AnnotatedTypeProperty() );
        assertThat( annotated.getProp() ).isEqualTo( "fromAnnotatedToAnnotated" );
    }

    @Test
    public void filtersOutBasedOnBothParametersAndReturnType() {
        final NotAnnotatedTypeProperty notAnnotated = AnnotatedTypeMapper.INSTANCE.toNotAnnotated(
            new NotAnnotatedTypeProperty() );
        assertThat( notAnnotated.getProp() ).isEqualTo( "noAnnotations" );
    }
}
