/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Licensed to the Apache Software Foundation (ASF) under one
 ~ or more contributor license agreements.  See the NOTICE file
 ~ distributed with this work for additional information
 ~ regarding copyright ownership.  The ASF licenses this file
 ~ to you under the Apache License, Version 2.0 (the
 ~ "License"); you may not use this file except in compliance
 ~ with the License.  You may obtain a copy of the License at
 ~
 ~   http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing,
 ~ software distributed under the License is distributed on an
 ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 ~ KIND, either express or implied.  See the License for the
 ~ specific language governing permissions and limitations
 ~ under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package org.apache.sling.scripting.bundle.tracker.it;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleBundleClassicIT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/examplebundle-classic";

    @Test
    public void testHello() throws Exception {
        final String expectedRT = "sling/scripting/examplebundle-classic/hello";
        Document document = getDocument(ROOT + "/hello.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-classic");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-classic");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }
}
