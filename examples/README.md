Examples
====

Because some code is worth a thousand words...

The examples provided by the project also serve as integration tests, therefore they should always work and be relatively up-to-date.
Four bundles showcase how the [`org.apache.sling.scripting.bundle.tracker`](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker/tree/master/README.md).

All the test endpoints will be in the resource tree under the `/content/bundled-scripts/` folder.

All the servlets registered by the Apache Sling Scripting Bundle Tracker will have their providers registered in the `/apps/org.apache.sling.scripting.examplebundle.*` and `/apps/sling/scripting/examplebundle*` folders.

The integration tests using these bundles can be found [here](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker-it/tree/master/it/src/test/java/org/apache/sling/scripting/bundle/tracker/it) and
you can find instructions to start a running sling instance with the provided examples [here](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker-it#example).
