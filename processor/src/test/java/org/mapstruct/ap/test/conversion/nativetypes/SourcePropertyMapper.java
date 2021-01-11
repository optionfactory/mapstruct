/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conversion.nativetypes;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SourceProperty;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SourcePropertyMapper {
    SourcePropertyMapper INSTANCE = Mappers.getMapper( SourcePropertyMapper.class );

    @Mapping(source = "src", target = "dst")
    SourcePropertyResponse map(SourcePropertyEntity sourcePropertyEntity);

    default SourcePropertyResponseField map(
        SourcePropertyField sourcePropertyField,
        @SourceProperty String sourceProperty) {
        return new SourcePropertyResponseField(sourceProperty);
    }
}
