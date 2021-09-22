Precompiled Bundled Scripts - extracting capabilities from content packages
====

This project showcases how the [`scriptingbundle-maven-plugin`](https://sling.apache.org/components/scriptingbundle-maven-plugin/)
can be used to extract capabilities from a [FileVault](https://jackrabbit.apache.org/filevault/) content package.
Although both the content package and the generated bundle will be deployed during build-time to the instance,
the rendering for the resource types handled by the scripts available in this project will be performed via the
generated bundle. The servlets instantiated for this bundle will [have priority](https://sling.apache.org/documentation/bundles/scripting.html#script-resolution-order) over scripts from the resource tree
for the same resource type/selectors combinations.
