/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conversion.nativetypes;

public class SourcePropertyEntity {
    private SourcePropertyField src;
    private SourcePropertyField other;

    public SourcePropertyField getSrc() {
        return src;
    }

    public void setSrc(SourcePropertyField src) {
        this.src = src;
    }

    public SourcePropertyField getOther() {
        return other;
    }

    public void setOther(SourcePropertyField other) {
        this.other = other;
    }
}
