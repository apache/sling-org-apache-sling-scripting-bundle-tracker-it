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
import static org.junit.Assert.assertTrue;

public class ExampleBundleExtend2IT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/examplebundle-extend2";

    @Test
    public void testOne() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.one/1.0.0";

        Document document = getDocument(ROOT + "/one.html");
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "org__002e__apache__002e__sling__002e__scripting__002e__examplebundle__002e__precompiled__002e__hello.__0031____002e__0__002e__0.hello__002e__html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

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
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "org__002e__apache__002e__sling__002e__scripting__002e__examplebundle__002e__precompiled__002e__hello.__0031____002e__0__002e__0.hello__002e__html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

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
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "/javax.script/org.apache.sling.scripting.examplebundle.extend2.two/1.0.0/two.html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

        Elements h = document.select("#h-extend2-one");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-precompiled-1");
        assertEquals("The w.html script should have been provided by org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0", 1
                , w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));

        Elements localTemplate = document.select("div.template.two");
        assertEquals("two", localTemplate.html());

        Elements inheritedTemplate = document.select("div.inherited > div.precompiled1Template > p");
        assertEquals("Hello, John Doe!", inheritedTemplate.html());

        Elements absoluteTemplate1 = document.select("div.absolute-path-1 > div.precompiled1Template > p");
        assertEquals("Hello, John Doe!", absoluteTemplate1.html());

        Elements absoluteTemplate2 = document.select("div.absolute-path-2 > div.exampledBundleAppsHello > p");
        assertEquals("Hello, Jack Schitt!", absoluteTemplate2.html());
    }

    @Test
    public void testTwoV1() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.two/1.0.0";

        Document document = getDocument(ROOT + "/two-v1.html");
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "/javax.script/org.apache.sling.scripting.examplebundle.extend2.two/1.0.0/two.html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

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
    public void testThree() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.three";

        Document document = getDocument(ROOT + "/three.html");
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "/javax.script/apps/sling/scripting/examplebundle/hello/hello.html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

        Elements h = document.select("#h-extend2-three");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-examplebundle-hello");
        assertEquals("The w.html script should have been provided by sling/scripting/examplebundle/hello", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }
}
