/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conversion.nativetypes;

public class SourcePropertyResponse {
    private SourcePropertyResponseField dst;
    private SourcePropertyResponseField other;

    public SourcePropertyResponseField getDst() {
        return dst;
    }

    public void setDst(SourcePropertyResponseField dst) {
        this.dst = dst;
    }

    public SourcePropertyResponseField getOther() {
        return other;
    }

    public void setOther(SourcePropertyResponseField other) {
        this.other = other;
    }
}
