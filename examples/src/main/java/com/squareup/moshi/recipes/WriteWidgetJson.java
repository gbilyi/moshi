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

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.recipes.models.*;

import java.io.IOException;
import java.util.Arrays;

public final class WriteWidgetJson {
    public void run() {
        BaseWidget widget1 = new Widget1("BLUE");
        BaseWidget widget2 = new Widget2("GREEN");
        BaseWidget widget3 = new Widget2("RED");

        Container nestedContainer2 = new Container(Arrays.asList(widget2, widget3));
        Container nestedContainer = new Container(Arrays.asList(widget1, nestedContainer2));
        Container container = new Container(Arrays.asList(nestedContainer, widget2));

        System.out.println("original object: " + container);

        Moshi moshi = new Moshi.Builder().add(new BaseWidgetAdapter()).build();
        JsonAdapter<Container> jsonAdapter = moshi.adapter(Container.class).indent(" ");

        String json = jsonAdapter.toJson(container);
        System.out.println(json);

        try {
            Container widget = jsonAdapter.fromJson(json);
            System.out.println("widget: " + widget);

            if (widget.equals(container)) {
                System.out.println("Deserialized result is identical");
            } else {
                System.out.println("Original and deserialized objects are different!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
        new WriteWidgetJson().run();
    }
}
