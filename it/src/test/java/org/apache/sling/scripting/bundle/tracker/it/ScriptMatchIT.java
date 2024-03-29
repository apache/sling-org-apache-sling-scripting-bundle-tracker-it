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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScriptMatchIT extends AbstractEndpointTestBase {
    
    private static final String SCRIPT_MATCHING_BASE = BASE + "/examplebundle/script-matching";

    @Test
    public void testGETMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpGet.METHOD_NAME);
    }

    @Test
    public void testGETMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpGet.METHOD_NAME);
    }

    @Test
    public void testHEADMethodMatching() throws Exception {
        HttpResponse response = getResponse(HttpHead.METHOD_NAME, SCRIPT_MATCHING_BASE + ".html");
        Header[] header = response.getHeaders("X-Script-Name");
        assertEquals("Expected to find one X-Script-Name header.", 1, header.length);
        assertEquals("org.apache.sling.scripting.examplebundle.scriptmatching/1.0.0/HEAD.html", header[0].getValue());
    }

    @Test
    public void testHEADMethodSelectorMatching() throws Exception {
        HttpResponse response = getResponse(HttpHead.METHOD_NAME, SCRIPT_MATCHING_BASE + ".selector-1.html");
        Header[] header = response.getHeaders("X-Script-Name");
        assertEquals("Expected to find one X-Script-Name header.", 1, header.length);
        assertEquals("org.apache.sling.scripting.examplebundle.scriptmatching/1.0.0/selector-1.HEAD.html",
                header[0].getValue());
    }

    @Test
    public void testOPTIONSMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpOptions.METHOD_NAME);
    }

    @Test
    public void testOPTIONSMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpOptions.METHOD_NAME);
    }

    @Test
    public void testPOSTMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpPost.METHOD_NAME);
    }

    @Test
    public void testPOSTMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpPost.METHOD_NAME);
    }

    @Test
    public void testPATCHMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpPatch.METHOD_NAME);
    }

    @Test
    public void testPATCHMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpPatch.METHOD_NAME);
    }

    @Test
    public void testPUTMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpPut.METHOD_NAME);
    }

    @Test
    public void testPUTMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpPut.METHOD_NAME);
    }

    @Test
    public void testDELETEMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpDelete.METHOD_NAME);
    }

    @Test
    public void testDELETEMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpDelete.METHOD_NAME);
    }

    @Test
    public void testTRACEMethodMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".html", HttpTrace.METHOD_NAME);
    }

    @Test
    public void testTRACEMethodSelectorMatching() throws Exception {
        testHttpMethodScriptMatching(SCRIPT_MATCHING_BASE + ".selector-1.html", HttpTrace.METHOD_NAME);
    }

    @Test
    public void testSelectorMatching() throws Exception {
        Document document = getDocument(SCRIPT_MATCHING_BASE + ".selector-2.html");
        assertTrue(document.select("div").html().contains("org.apache.sling.scripting.examplebundle" +
                ".scriptmatching/1.0.0/selector-2.html"));
    }

    /*
     * We need to test that we don't override the default GET servlet for resource types without a script
     * This scenario can happen when we extend a resource type without providing a script ourself
     * in this case, the registration should not override the default servlets for the none default extension.
     * In other words, in this case, only the default extension should be handled by the resource super type -
     * all other extensions should be handled by the default servlet.
     */
    @Test
    public void testDefaultGETServlet() throws Exception {
        // First test that the default extension is handled by the super type
        Document document = getDocument(SCRIPT_MATCHING_BASE + "-no-version-rtsuper.html");
        assertTrue(document.html().contains("/libs/rtsuper"));
        // and now test that the none default json extension is not handled by it
        document = getDocument(SCRIPT_MATCHING_BASE + "-no-version-rtsuper.json");
        assertTrue(!document.html().contains("/libs/rtsuper"));
    }

    private void testHttpMethodScriptMatching(String url, String httpMethod) throws Exception {
        Document document = getDocument(url, httpMethod);
        String path = url.substring(url.lastIndexOf('/'));
        String[] parts = path.split("\\.");
        String selectorString = null;
        String extension;
        if (parts.length == 3) {
            selectorString = parts[1];
            extension = parts[2];
        } else if (parts.length == 2) {
            extension = parts[1];
        } else {
            throw new IllegalArgumentException("The following URL doesn't seem to be correctly handled: " + url);
        }
        String expectedScriptName = "org.apache.sling.scripting.examplebundle.scriptmatching/1.0.0/" +
                (StringUtils.isNotEmpty(selectorString) ? selectorString + "." : "") + httpMethod + "." + extension;
        assertTrue(document.select("div").html().contains(expectedScriptName));
    }

}
