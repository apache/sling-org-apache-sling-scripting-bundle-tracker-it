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

public class ExampleBundlePrecompiledIT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/examplebundle-precompiled";

    @Test
    public void testHello() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.hello/2.0.0";
        Document document = getDocument(ROOT + "/hello.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-precompiled-2");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-2");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testHelloV2() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.hello/2.0.0";
        Document document = getDocument(ROOT + "/hello-v2.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-precompiled-2");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-2");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testHelloV1() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0";
        Document document = getDocument(ROOT + "/hello-v1.html");
        assertEquals(expectedRT, document.select("h2").html());

        Elements h = document.select("#h-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testPathBasedServlet() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.pathcaller";
        final String callee = ("apps.org__002e__apache__002e__sling__002e__scripting__002e__examplebundle__002e__precompiled__002e__path" +
                ".path__002d__script__002e__html");
        Document document = getDocument(ROOT + "/path-based-servlet.html");
        assertEquals(expectedRT, document.select("h2").html());
        assertEquals("Cannot detect callee script which is supposed to be a path based servlet.", callee,
                document.select("div.caller > span.script").html());
    }
}
