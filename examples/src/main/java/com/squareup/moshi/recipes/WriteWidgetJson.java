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

import java.util.Arrays;

import static com.squareup.moshi.recipes.models.Suit.*;

public final class WriteWidgetJson {
    public void run() {
        BaseWidget widget1 = new Widget("BLUE");
        BaseWidget widget2 = new Widget("GREEN");

        Container nestedContainer = new Container(Arrays.asList(widget1, widget2));

        Container container = new Container(Arrays.asList(nestedContainer, widget2));

        Main main = new Main(container);

        Moshi moshi = new Moshi.Builder().add(new BaseWidgetAdapter()).build();
        JsonAdapter<Main> jsonAdapter = moshi.adapter(Main.class).indent(" ");

        String json = jsonAdapter.toJson(main);
        System.out.println(json);
    }

    public static void main(String[] args) {
        new WriteWidgetJson().run();
    }
}
