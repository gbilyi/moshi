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

import com.squareup.moshi.*;
import com.squareup.moshi.recipes.models.*;

import java.io.IOException;
import java.util.Map;

final class BaseAdapter {

    @ToJson
    void write(JsonWriter writer, Base baseWidget, JsonAdapter<Container> delegate) throws IOException {
        if (baseWidget instanceof Widget1 || baseWidget instanceof Widget2) {
            writeWidget(writer, (BaseWidget) baseWidget);
        } else {
            delegate.toJson(writer, (Container) baseWidget);
        }
    }

    private void writeWidget(JsonWriter writer, BaseWidget baseWidget) throws IOException {
        writer.beginObject();

        if (baseWidget instanceof Widget1) {
            Widget1 widget = (Widget1) baseWidget;
            writer.name("name").value(Widget1.class.getCanonicalName());
            writer.name("color").value(widget.color);

        } else if (baseWidget instanceof Widget2) {
            Widget2 widget = (Widget2) baseWidget;
            writer.name("name").value(Widget2.class.getCanonicalName());
            writer.name("color").value(widget.color);
        }

        writer.endObject();
    }

    /*
       is called for every object at the first level.
     */
    @FromJson
    Base fromJson(JsonReader reader,
                        JsonAdapter<Container> containerDelegate,
                        JsonAdapter<Widget1> widget1Delegate,
                        JsonAdapter<Widget2> widget2Delegate) throws IOException {
        Map<String, String> map = (Map<String, String>) reader.readJsonValue();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().equals("widgets")) {
                return containerDelegate.fromJsonValue(map);
            } else {

                String widget1Name = Widget1.class.getCanonicalName();
                String widget2Name = Widget2.class.getCanonicalName();

                if (entry.getValue().equals(widget1Name)) {
                    return widget1Delegate.fromJsonValue(map);
                } else if (entry.getValue().equals(widget2Name)) {
                    return widget2Delegate.fromJsonValue(map);
                } else {
                    //another widget
                }
            }
        }
        //should not reach here.
        return null;
    }


}
