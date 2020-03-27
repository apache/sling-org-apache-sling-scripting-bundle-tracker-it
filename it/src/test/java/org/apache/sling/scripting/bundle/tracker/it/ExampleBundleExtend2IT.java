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

public class ExampleBundleExtend2IT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/examplebundle-extend2";

    @Test
    public void testOne() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.one/1.0.0";

        Document document = getDocument(ROOT + "/one.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-extend2-one");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("The w.html script should have been provided by org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0", 1
                , w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testOneV1() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.one/1.0.0";

        Document document = getDocument(ROOT + "/one-v1.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-extend2-one");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("The w.html script should have been provided by org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0", 1
                , w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testTwo() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.two/1.0.0";

        Document document = getDocument(ROOT + "/two.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-extend2-two");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("The w.html script should have been provided by org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0", 1
                , w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testTwoV1() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.two/1.0.0";

        Document document = getDocument(ROOT + "/two-v1.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-extend2-two");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("The w.html script should have been provided by org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0", 1
                , w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }
}
