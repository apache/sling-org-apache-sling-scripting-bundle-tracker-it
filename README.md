[<img src="https://sling.apache.org/res/logos/sling.png"/>](https://sling.apache.org)

 [![Build Status](https://builds.apache.org/buildStatus/icon?job=Sling/sling-org-apache-sling-scripting-bundle-tracker-it/master)](https://builds.apache.org/job/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master) [![Test Status](https://img.shields.io/jenkins/t/https/builds.apache.org/job/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master.svg)](https://builds.apache.org/job/Sling/job/sling-org-apache-sling-scripting-bundle-tracker-it/job/master/test_results_analyzer/) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![scripting](https://sling.apache.org/badges/group-scripting.svg)](https://github.com/apache/sling-aggregator/blob/master/docs/groups/scripting.md)

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
