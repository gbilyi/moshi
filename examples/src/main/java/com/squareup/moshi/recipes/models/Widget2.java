/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.moshi.recipes.models;

import java.util.Objects;

public final class Widget2 implements BaseWidget {
    public String name;
    public final String color;

    public Widget2(String color) {
        this.name = Widget2.class.getCanonicalName();
        this.color = color;
    }

    @Override
    public String toString() {
        return "\nWidget2{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Widget2 widget2 = (Widget2) o;
        return Objects.equals(name, widget2.name) &&
                Objects.equals(color, widget2.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
