# Camunda

## BPMN Path Finder Task

### To install:

``` mvn clean package ```

### To run:

``` java -jar target/pathfinder-1.0-SNAPSHOT.jar approveInvoice invoiceProcessed ```

### Q&A

``` Q1: How long did it take you to solve the exercise? ```

Exactly 2 hours of coding

``` Q2: What are some edge cases that you might not have considered yet? ```

    - Empty or Malformed BPMN XML.
    - Changes in XML Schema.
    - Empty or Malformed User Supplied Arguments.
    - Networking issues.
    - Very large input which exceeds machine resources.
    - Lack of support for multiple paths.

``` Q3: What kind of problems/limitations can you think of in terms of your implementation? ```

    - Single path support (what if there are multiple paths?).
    - Error handling for XML schema validation (only exception handling without implicit edge cases descriptive exceptions).
    - User input validation is still not supported (it was not required in the task description)
    - Concurrency and thread safety (Concurrent user support is still not there in case of usage by multiple users for example in a web environment).
    - Network dependency (depending on an external resource to fetch the XML data from a different network may still cause some issues).
    - Maybe some logging is needed as well.