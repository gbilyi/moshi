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
import com.squareup.moshi.recipes.models.*;
import okio.BufferedSink;

import java.io.IOException;
import java.util.List;

final class BaseWidgetAdapter {

    @ToJson
    void write(JsonWriter writer, BaseWidget baseWidget, JsonAdapter<Container> delegate) throws IOException {
        if (baseWidget instanceof Widget) {
            writeWidget(writer, baseWidget);
        } else {
            Container container = (Container) baseWidget;
//            writeWidgetsArray(writer, container.children);
            delegate.toJson(writer, container);

        }
    }


    private void writeWidget(JsonWriter writer, BaseWidget baseWidget) throws IOException {
        writer.beginObject();
        Widget widget = (Widget) baseWidget;
        writer.name("color").value(widget.color);
        writer.endObject();
    }

    public void writeWidgetsArray(JsonWriter writer, List<BaseWidget> widgets) throws IOException {
        writer.beginArray();
        for (BaseWidget widget : widgets) {
            writeWidget(writer, widget);
        }
        writer.endArray();
    }

    @FromJson
    BaseWidget fromJson(String json) {
        if (json.equals("BLA-ZZZ")) {
            //return new Container();
        }
        return new Widget(json);
    }
}
