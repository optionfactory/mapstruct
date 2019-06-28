/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.typeannotations;

public class AnnotatedTypeProperty {
    private String prop;

    public @TestTypeAnnotation String getProp() {
        return prop;
    }

    public void setProp(@TestTypeAnnotation String prop) {
        this.prop = prop;
    }
}
