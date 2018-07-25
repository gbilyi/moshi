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
package com.squareup.moshi.recipes;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.ToJson;
import com.squareup.moshi.recipes.models.BaseWidget;
import com.squareup.moshi.recipes.models.Container;
import com.squareup.moshi.recipes.models.Widget1;
import com.squareup.moshi.recipes.models.Widget2;

import java.io.IOException;

final class BaseWidgetAdapter {

    @ToJson
    void write(JsonWriter writer, BaseWidget baseWidget, JsonAdapter<Container> delegate) throws IOException {
        if (baseWidget instanceof Widget1 || baseWidget instanceof Widget2) {
            writeWidget(writer, baseWidget);
        } else {
            delegate.toJson(writer, (Container) baseWidget);
        }
    }


    private void writeWidget(JsonWriter writer, BaseWidget baseWidget) throws IOException {
        writer.beginObject();

        if (baseWidget instanceof Widget1) {
            Widget1 widget = (Widget1) baseWidget;
            writer.name("name").value("widget1");
            writer.name("color").value(widget.color);

        } else if (baseWidget instanceof Widget2) {
            Widget2 widget = (Widget2) baseWidget;
            writer.name("name").value("widget2");
            writer.name("color").value(widget.color);
        }

        writer.endObject();
    }

    @FromJson
    BaseWidget fromJson(String json) {
        if (json.equals("BLA-ZZZ")) {
            //return new Container();
        }
        return new Widget1(json);
    }
}
