/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conversion.nativetypes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.ap.testutil.WithClasses;
import org.mapstruct.ap.testutil.runner.AnnotationProcessorTestRunner;

import static org.assertj.core.api.Assertions.assertThat;

@WithClasses({
        SourcePropertyEntity.class,
        SourcePropertyResponse.class,
        SourcePropertyField.class,
        SourcePropertyResponseField.class,
        SourcePropertyMapper.class
})
@RunWith(AnnotationProcessorTestRunner.class)
public class SourcePropertyTest {

    @Test
    public void foo() {
        SourcePropertyEntity entity = new SourcePropertyEntity();
        SourcePropertyResponse response = SourcePropertyMapper.INSTANCE.map( entity );
        assertThat( response.getDst().getSourcePropName() ).isEqualTo( "src" );
        assertThat( response.getOther().getSourcePropName() ).isEqualTo( "other" );
    }
}
