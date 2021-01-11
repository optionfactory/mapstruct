/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conversion.nativetypes;

public class SourcePropertyResponseField {
    private final String sourcePropName;

    public SourcePropertyResponseField(String sourcePropName) {
        this.sourcePropName = sourcePropName;
    }

    public String getSourcePropName() {
        return sourcePropName;
    }
}
