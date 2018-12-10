Apache Sling Scripting Bundle Tracker Integration Tests
====

This project provides the integration tests and some test examples for the [Apache Sling Scripting Bundle Tracker](https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker) module.

## How to

No matter if you want to run the integration tests or to check out the examples, you'll first have to initialise the experimental [`org-apache-sling-scripting-sightly`](https://github.com/apache/sling-org-apache-sling-scripting-sightly/tree/experimental-scripting-resolver) submodule, which provides a slightly modified HTL engine, able to work with the Apache Sling Scripting Bundle Tracker.

```
git clone https://github.com/apache/sling-org-apache-sling-scripting-bundle-tracker.git
cd org-apache-sling-scripting-bundle-tracker
git submodule init
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
