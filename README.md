[<img src="http://sling.apache.org/res/logos/sling.png"/>](http://sling.apache.org)

 [![Build Status](https://builds.apache.org/buildStatus/icon?job=sling-org-apache-sling-scripting-bundle-tracker-it-1.8)](https://builds.apache.org/view/S-Z/view/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it-1.8) [![Test Status](https://img.shields.io/jenkins/t/https/builds.apache.org/view/S-Z/view/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it-1.8.svg)](https://builds.apache.org/view/S-Z/view/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it-1.8/test_results_analyzer/) [![Coverage Status](https://img.shields.io/jenkins/c/https/builds.apache.org/view/S-Z/view/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it-1.8.svg)](https://builds.apache.org/view/S-Z/view/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it-1.8/) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Apache Sling Scripting Bundle Tracker Integration Tests
====

This project provides the integration tests and some test examples for the [Apache Sling Scripting Bundle Tracker](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker) module.

## How to

No matter if you want to run the integration tests or to check out the examples, you'll first have to initialise the experimental [`org-apache-sling-scripting-sightly`](https://github.com/apache/sling-org-apache-sling-scripting-sightly/tree/experimental-scripting-resolver) submodule, which provides a slightly modified HTL engine, able to work with the Apache Sling Scripting Bundle Tracker.

```
git clone https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker.git
cd org-apache-sling-scripting-bundle-tracker
git submodule update
```

### Integration Tests

To run the integration tests do:

```
mvn clean verify -Pit
```

### Example

To play around with a sling instance on localhost port 8080 (override with -Dhttp.port=<port>) that has the [examples](../../examples) installed run:

```
mvn clean verify -Pexample
``` 
