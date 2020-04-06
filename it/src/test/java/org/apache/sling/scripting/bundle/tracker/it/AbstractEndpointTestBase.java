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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.sling.testing.clients.SlingHttpResponse;
import org.apache.sling.testing.junit.rules.SlingInstanceRule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.ClassRule;


public abstract class AbstractEndpointTestBase {

    protected int contentFindTimeout = 3000;
    protected int contentFindRetryDelay = 1000;
    protected static final String BASE = "/content/bundled-scripts";
    protected static final String DATA_RT_ATTRIBUTE = "data-rt";
    protected static final String DATA_SCRIPT = "data-script";

    @ClassRule
    public static final SlingInstanceRule SLING_INSTANCE_RULE = new SlingInstanceRule();

    @ClassRule
    public static final LaunchpadReadyRule LAUNCHPAD_READY_RULE = new LaunchpadReadyRule(Integer.getInteger("launchpad.http.server.port",
            8080));

    private Map<String, Document> documentMap = new ConcurrentHashMap<>();
    private Set<String> resourceAlreadyPresent = Collections.synchronizedSet(new HashSet<>());

    /**
     * Retrieves a jsoup Document from the passed {@code url}. The URL can contain selectors and extensions, but it has to identify a Sling
     * content {@link org.apache.sling.api.resource.Resource}.
     *
     * @param url the URL from which to retrieve the {@link Document}
     * @return the Document
     * @throws Exception if the resource was not found before the timeout elapsed
     */
    protected Document getDocument(String url) throws Exception {
        return getDocument(url, HttpGet.METHOD_NAME);
    }

    protected Document getDocument(String url, String httpMethod, NameValuePair... parameters) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);
        uriBuilder.setParameters(parameters);
        URI uri = uriBuilder.build();
        Document document = documentMap.get(httpMethod + ":" + uri.toString());
        if (document == null) {
            SlingHttpResponse response = getResponse(httpMethod, url);
            document = Jsoup.parse(response.getContent(),
                    System.getProperty("launchpad.http.server.url", "launchpad.http.server.url IS_NOT_SET"));
            documentMap.put(httpMethod + ":" + uri.toString(), document);
        }
        return document;
    }

    protected SlingHttpResponse getResponse(String method, String url, NameValuePair... parameters) throws Exception {
        String resourcePath = url.substring(0, url.indexOf('.'));
        if (!resourceAlreadyPresent.contains(resourcePath)) {
            SLING_INSTANCE_RULE.getAdminClient().waitExists(resourcePath, contentFindTimeout, contentFindRetryDelay);
            resourceAlreadyPresent.add(resourcePath);
        }
        HttpUriRequest request = prepareRequest(method, url, parameters);
        SlingHttpResponse response = SLING_INSTANCE_RULE.getAdminClient().doRequest(request, Collections.emptyList());
        Assert.assertEquals("URL " + url + " did not return a 200 status code.", 200, response.getStatusLine().getStatusCode());
        return response;
    }

    protected HttpUriRequest prepareRequest(String method, String url, NameValuePair... parameters) throws URISyntaxException {
        HttpRequestBase request = null;
        URIBuilder uriBuilder =
                new URIBuilder(System.getProperty("launchpad.http.server.url", "launchpad.http.server.url IS_NOT_SET") + url);
        uriBuilder.setParameters(parameters);
        switch (method) {
            case HttpGet.METHOD_NAME:
                request = new HttpGet(uriBuilder.build());
                break;
            case HttpHead.METHOD_NAME:
                request = new HttpHead(uriBuilder.build());
                break;
            case HttpOptions.METHOD_NAME:
                request = new HttpOptions(uriBuilder.build());
                break;
            case HttpPost.METHOD_NAME:
                request = new HttpPost(uriBuilder.build());
                break;
            case HttpPut.METHOD_NAME:
                request = new HttpPut(uriBuilder.build());
                break;
            case HttpPatch.METHOD_NAME:
                request = new HttpPatch(uriBuilder.build());
                break;
            case HttpTrace.METHOD_NAME:
                request = new HttpTrace(uriBuilder.build());
                break;
            case HttpDelete.METHOD_NAME:
                request = new HttpDelete(uriBuilder.build());
                break;
        }
        return request;
    }

}
