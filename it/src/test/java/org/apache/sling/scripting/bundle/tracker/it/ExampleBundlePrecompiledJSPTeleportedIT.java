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
import java.util.stream.Collectors;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleBundlePrecompiledJSPTeleportedIT extends AbstractTeleportedTestBase {

    @Test
    public void testHello() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.jsp.hello";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(4, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "hello.jsp",
                    "h.jsp",
                    "w.jsp",
                    "org.apache.sling.scripting.examplebundle.precompiled.jsp.hello.servlet"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));

            for (Resource child : children.values()) {
                assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                        child.getResourceSuperType());
            }
        }
    }

    @Test
    public void testBase() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.jsp.base";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(3, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "html.jsp",
                    "html.servlet",
                    "selector.jsp"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));
            assertTrue(String.format("Did not expect any properties on the %s resource.", main.getPath()), main.getValueMap().isEmpty());
            for (Resource child : children.values()) {
                assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                        child.getResourceSuperType());
            }
        }
    }

    @Test
    public void testExtends() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.jsp.extends";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(1, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "selector.jsp"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));

            assertEquals("org.apache.sling.scripting.examplebundle.precompiled.jsp.base", main.getResourceSuperType());
        }
    }
}
