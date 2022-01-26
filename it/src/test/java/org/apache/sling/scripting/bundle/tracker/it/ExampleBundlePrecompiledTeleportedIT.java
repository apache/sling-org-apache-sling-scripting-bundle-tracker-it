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

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExampleBundlePrecompiledTeleportedIT extends AbstractTeleportedTestBase {

    @Test
    public void testHello1() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.hello/1.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(7, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "w.html",
                    "hello.html",
                    "1.0.0.servlet",
                    "templates.html",
                    "name-provider.js",
                    "use-script.js"
            );
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));
        }
    }

    @Test
    public void testHello2() throws LoginException {
        final String expectedRT = "org.apache.sling.scripting.examplebundle.precompiled.hello/2.0.0";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertTrue(main.getValueMap().isEmpty());
            Map<String, Resource> children = collectResourceChildren(main);
            assertEquals(5, children.size());

            Set<String> expectedChildren = getChildrenForServletResource(
                    "/apps/" + expectedRT,
                    "h.html",
                    "w.html",
                    "hello.html",
                    "2.0.0.servlet",
                    "nested"
            );
            assertNotNull(resolver.resolve("/apps/" + expectedRT + "/nested/selector.html"));
            assertEquals(expectedChildren, children.values().stream().map(Resource::getPath).collect(Collectors.toSet()));

            for (Resource child : children.values()) {
                if (!"nested".equals(child.getName())) {
                    assertEquals(child.getPath() + " does not have the expected resource super type", "sling/bundle/resource",
                            child.getResourceSuperType());
                }
            }
        }
    }

    /*
     * We need to test that if we register with an absolute resource type and a relative one we are not ending up with
     * a registration in the wrong prefix. As an example, when we register a resource type called rtsuper we end up with a capability
     * for [rtsuper,/apps/rtsuper]. That will make it so that the resolver prepends the search path prefix (which is configurable).
     * For a default configuration, this could end up being /apps/rtsuper or /libs/rtsuper, based on the prefix value, which is a mistake
     * because, in that case, the resource super type would be on both and could cause a cycle.
     *
     * To recreate this scenario without changing the resolver config for the prefix selection this test has the script in
     * /apps and the extends in /libs with a value for /apps/rtsuper - hence, we expect that we are not finding a super type
     * on the script in /apps because otherwise it would have picked the wrong prefix (which we avoid by not registering
     * relative resource types when there is an absolute one for the same resource type).
     */
    @Test
    public void testRtSuper() throws LoginException {
        final String expectedRT = "rtsuper";
        ResourceResolverFactory resourceResolverFactory = teleporter.getService(ResourceResolverFactory.class);
        try (ResourceResolver resolver = resourceResolverFactory.getResourceResolver(AUTH_MAP)) {
            Resource main = resolver.resolve("/apps/" + expectedRT);
            assertNotNull(main);
            assertNull(main.getResourceSuperType());
        }
    }
}
