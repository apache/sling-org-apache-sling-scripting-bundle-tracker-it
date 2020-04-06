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

import java.util.Map;
import java.util.Set;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleBundleTeleportedIT extends AbstractTeleportedTestBase {

    @Test
    public void testHello1() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.hello/1.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.getResource("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(7, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "h.html.servlet",
                    "w.html",
                    "w.html.servlet",
                    "hello.html",
                    "hello.html.servlet",
                    "html.servlet"
            );
            assertEquals(expectedChildren, children.keySet());

            for (Resource child : children.values()) {
                assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                        child.getResourceSuperType());
            }
        }
    }

    @Test
    public void testHello2() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.hello/2.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.getResource("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(7, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "h.html.servlet",
                    "w.html",
                    "w.html.servlet",
                    "hello.html",
                    "hello.html.servlet",
                    "html.servlet"
            );
            assertEquals(expectedChildren, children.keySet());

            for (Resource child : children.values()) {
                assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                        child.getResourceSuperType());
            }
        }
    }

    @Test
    public void testAppsHello() throws LoginException {
        final String expectedRT = "sling/scripting/examplebundle/hello";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.getResource("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(7, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "h.html.servlet",
                    "w.html",
                    "w.html.servlet",
                    "hello.html",
                    "hello.html.servlet",
                    "html.servlet"
            );
            assertEquals(expectedChildren, children.keySet());

            for (Resource child : children.values()) {
                assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                        child.getResourceSuperType());
            }
        }
    }
}