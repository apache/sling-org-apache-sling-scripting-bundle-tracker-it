[![Apache Sling](https://sling.apache.org/res/logos/sling.png)](https://sling.apache.org)

&#32;[![Build Status](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master/badge/icon)](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master/)&#32;[![Test Status](https://img.shields.io/jenkins/tests.svg?jobUrl=https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master/)](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master/test/?width=800&height=600)&#32;[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-org-apache-sling-scripting-bundle-tracker-it&metric=coverage)](https://sonarcloud.io/dashboard?id=apache_sling-org-apache-sling-scripting-bundle-tracker-it)&#32;[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-org-apache-sling-scripting-bundle-tracker-it&metric=alert_status)](https://sonarcloud.io/dashboard?id=apache_sling-org-apache-sling-scripting-bundle-tracker-it)&#32;[![scripting](https://sling.apache.org/badges/group-scripting.svg)](https://github.com/apache/sling-aggregator/blob/master/docs/groups/scripting.md) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Apache Sling Scripting Bundle Tracker Integration Tests
====

This project provides the integration tests and some test examples for the precompiled bundled scripts feature supported by the [Apache Sling Servlets Resolver](https://github.com/apache/sling-org-apache-sling-servlets-resolver) module, starting with the 2.7.0 release.


### Example

To play around with a sling instance on localhost port 8080 (override with -Dhttp.port=<port>) that has the [examples](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker-it/tree/master/examples) installed run:

```
mvn clean verify -Pexample
``` 
