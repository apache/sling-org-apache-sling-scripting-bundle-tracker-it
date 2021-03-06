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

public class ExampleBundleExtend2TeleportedIT extends AbstractTeleportedTestBase {

    @Test
    public void testOne() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.one/1.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertEquals("org.apache.sling.scripting.examplebundle.extend1.hello/1.0.0", main.getResourceSuperType());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(2, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "name-provider.js"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));

            assertEquals("org.apache.sling.scripting.examplebundle.extend1.hello/1.0.0", main.getResourceSuperType());
        }
    }

    @Test
    public void testTwo() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.two/1.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertEquals("org.apache.sling.scripting.examplebundle.extend2.one/1.0.0", main.getResourceSuperType());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(4, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "1.0.0.servlet",
                    "two.html",
                    "two-templates.html",
                    "use-script-repo.js"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));
            assertEquals("org.apache.sling.scripting.examplebundle.extend2.one/1.0.0", main.getResourceSuperType());
        }
    }

    @Test
    public void testThree() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.extend2.three";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertEquals("sling/scripting/examplebundle/hello", main.getResourceSuperType());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(1, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));

            assertEquals("sling/scripting/examplebundle/hello", main.getResourceSuperType());
        }
    }
}
