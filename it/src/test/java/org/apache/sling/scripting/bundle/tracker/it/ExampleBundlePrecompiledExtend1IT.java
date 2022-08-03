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

public class ExampleBundlePrecompiledExtend1IT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/examplebundle-precompiled-extend1";

    // SLING-10015
    @Test
    public void testHello() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.extend1.hello/1.0.0";
        Document document = getDocument(ROOT + "/hello.html");
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0/hello.html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

        Elements h = document.select("#h-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));
        
        Elements template = document.select("#h-precompiled-template-wrapper1 > div.precompiled1TemplateExtend1 > p");
        assertEquals("Resource based servlet resolution failed.", 1, template.size());
        assertEquals("Hello, extended World!", template.html());

        Elements w = document.select("#w-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testHello_0_9() throws Exception {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.extend1.hello/0.9.0";
        Document document = getDocument(ROOT + "/hello-v0_9.html");
        Elements h2 = document.select("h2");
        assertEquals(expectedRT, h2.html());
        final String expectedScriptDriver = "org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0/hello.html";
        assertTrue(h2.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(h2.attr(DATA_SCRIPT)));

        Elements h = document.select("#h-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements template = document.select("#h-precompiled-template-wrapper1 > div.precompiled1Template > p");
        assertEquals("Resource based servlet resolution failed.", 1, template.size());
        assertEquals("Hello, World!", template.html());

        Elements w = document.select("#w-precompiled-1");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));
    }

    @Test
    public void testSling11508Level3() throws Exception {
        Document document = getDocument(ROOT + "/sling-11508-level-3.html");
        Elements body = document.select("body");
        assertEquals("It works!", body.html());
    }
}
