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

public class ExampleBundleFromCPIT extends AbstractEndpointTestBase {

    private static final String ROOT = BASE + "/example-cp";

    @Test
    public void testHello() throws Exception {
        final String expectedRT = "sling/scripting/example-cp/hello";
        Document document = getDocument(ROOT + "/hello.html");
        Elements heading = document.select("h2.root");
        assertEquals(expectedRT, heading.html());
        final String expectedScriptDriver = "/apps/sling/scripting/example-cp/hello/hello.html";
        assertTrue(heading.hasAttr(DATA_SCRIPT) && expectedScriptDriver.equals(heading.attr(DATA_SCRIPT)));

        Elements h = document.select("#h-examplebundle-hello");
        assertEquals("Resource based servlet resolution failed.", 1, h.size());
        assertEquals("Hello", h.html());
        assertEquals(expectedRT, h.attr(DATA_RT_ATTRIBUTE));

        Elements w = document.select("#w-examplebundle-hello");
        assertEquals("Resource based servlet resolution failed.", 1, w.size());
        assertEquals("World", w.html());
        assertEquals(expectedRT, w.attr(DATA_RT_ATTRIBUTE));

        Elements delegationDiv = document.select("div.examplebundle-precompiled-template");
        assertEquals("Hello, John Doe!", delegationDiv.html());
    }

}
