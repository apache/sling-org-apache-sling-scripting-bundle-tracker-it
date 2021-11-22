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

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScriptMatchPostIT extends AbstractEndpointTestBase {

    private static final String SCRIPT_MATCHING_BASE = BASE + "/examplebundle/script-matching-no-version";

    @Test
    public void testPOSTMethodMatchingNoExtension() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE, HttpPost.METHOD_NAME);
        assertTrue(document.html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion/testpost/POST.html"));
    }

    @Test
    public void testPOSTResourceLabelMatching() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + ".html", HttpPost.METHOD_NAME);
        assertTrue(document.select("div").html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion/testpost/testpost.POST.html"));
    }

    @Test
    public void testMainPOSTMethodMatchingNoExtension() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + "-main", HttpPost.METHOD_NAME);
        assertTrue(document.html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion/testmainpost/POST.html"));
    }

    @Test
    public void testMainPOSTResourceLabelMatching() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + "-main.html", HttpPost.METHOD_NAME);
        assertTrue(document.select("div").html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion/testmainpost/POST.html"));
    }

    @Test
    public void testMainSelectorPOSTMethodMatchingNoExtension() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + "-mainselector", HttpPost.METHOD_NAME);
        assertTrue(!document.html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion"));
    }

    @Test
    public void testMainSelectorPOSTResourceLabelMatching() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + "-mainselector.html", HttpPost.METHOD_NAME);
        assertTrue(document.select("div").html().contains("org.apache.sling.scripting.examplebundle.scriptmatching.noversion/testmainselectorpost/testmainselectorpost.POST.html"));
    }
}
