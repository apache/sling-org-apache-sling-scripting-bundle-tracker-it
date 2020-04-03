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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.junit.rules.TeleporterRule;
import org.junit.Rule;

public class AbstractTeleportedTestBase {

    protected static final Map<String, Object> AUTH_MAP;

    static {
        AUTH_MAP = new HashMap<>();
        AUTH_MAP.put(ResourceResolverFactory.USER, "admin");
        AUTH_MAP.put(ResourceResolverFactory.PASSWORD, "admin".toCharArray());
    }

    @Rule
    public TeleporterRule teleporter = TeleporterRule.forClass(getClass(), "org.apache.sling.scripting.bundle.tracker.it.ITCustomizer");

    protected Set<String> getChildrenForServletResource(String resourceType, String... children) {
        HashSet<String> rtChildren = new HashSet<>();
        for (String child : children) {
            rtChildren.add(resourceType + "/" + child);
        }
        return rtChildren;
    }

    protected Map<String, Resource> collectResourceChildren(Resource resource) {
        Map<String, Resource> children = new HashMap<>();
        resource.getChildren().forEach(child -> children.put(child.getPath(), child));
        return children;
    }
}
