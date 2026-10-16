# Java Version Lab — Java 8 to Java 25

A hands-on Java learning repository covering **Java 8 to Java 25**, with practical examples, coding exercises, version-wise feature comparisons, and a Spring Boot REST API to explore modern Java features.

The goal of this repository is to understand how Java has evolved over the years, learn new language features through practical coding, and prepare for Java developer interviews.

---

## Table of Contents

* [Overview](#overview)
* [Project Objectives](#project-objectives)
* [Technologies Used](#technologies-used)
* [Prerequisites](#prerequisites)
* [Getting Started](#getting-started)
* [Project Structure](#project-structure)
* [Java Version-Wise Features](#java-version-wise-features)
* [Java 8 Features in Detail](#java-8-features-in-detail)
* [Spring Boot Application](#spring-boot-application)
* [Running the Application](#running-the-application)
* [API Endpoints](#api-endpoints)
* [Coding Exercises](#coding-exercises)
* [Java Version Comparison](#java-version-comparison)
* [Interview Preparation](#interview-preparation)
* [Learning Roadmap](#learning-roadmap)
* [Build and Test](#build-and-test)
* [Git Workflow](#git-workflow)
* [Contributing](#contributing)
* [Resources](#resources)
* [License](#license)

---

## Overview

Java has evolved significantly since Java 8. Each release has introduced improvements to the language, standard library, runtime, garbage collection, concurrency, and developer productivity.

This repository provides a structured way to learn these improvements through small, understandable examples.

Instead of learning features only from documentation, the project focuses on:

* Understanding the problem a feature solves.
* Learning the syntax and underlying concepts.
* Comparing older and newer approaches.
* Writing practical Java code.
* Running examples and verifying their output.
* Solving coding problems based on each feature.
* Understanding common interview questions and real-world use cases.

The repository starts with Java 8 fundamentals and progresses toward modern Java features available in Java 25.

**Primary goal:** Build a strong understanding of Core Java, modern Java programming, and how Java features are used in backend applications.

---

## Project Objectives

### 1. Learn Java version by version

Understand the important features introduced in Java 8, Java 9, Java 10, and subsequent releases through Java 25.

### 2. Practice with executable examples

Each feature should include practical code that can be compiled and executed.

### 3. Compare old and new approaches

For example, compare traditional loops with Stream API operations and anonymous classes with lambda expressions.

### 4. Prepare for technical interviews

Practice feature-based questions, coding exercises, output-prediction problems, and scenario-based questions.

### 5. Apply Java features in Spring Boot

Explore how modern Java features can be used in controllers, services, data transformation, validation, and business logic.

### 6. Build a long-term learning reference

Keep examples organized by Java version so the repository can grow as new concepts are learned.

---

## Technologies Used

| Technology     | Purpose                                    |
| -------------- | ------------------------------------------ |
| Java 25        | Main development JDK                       |
| Java 8–25      | Version-wise feature learning              |
| Spring Boot    | Backend application framework              |
| Spring Web MVC | REST API development                       |
| Maven          | Dependency management and build automation |
| JUnit          | Unit testing                               |
| Git            | Version control                            |
| GitHub         | Source code hosting and collaboration      |
| IntelliJ IDEA  | Development environment                    |

**Note:** Java 25 is the main development JDK for this project. Individual examples may require their corresponding language level or JDK when they use features unavailable in earlier releases.

---

## Prerequisites

Before running the project, install the following:

* JDK 25 or another JDK supported by the project's configured Java version.
* Apache Maven 3.9 or later.
* IntelliJ IDEA or another Java-compatible IDE.
* Git.
* Basic knowledge of Java and object-oriented programming.

Verify your installation:

```bash
java -version
javac -version
mvn -version
git --version
```

The Java compiler and Maven should use a compatible JDK.

---

## Getting Started

### Step 1: Clone the repository

```bash
git clone https://github.com/tejasmohitkar22/java-version-lab-.git
```

### Step 2: Navigate to the project directory

```bash
cd java-version-lab-/java-version-lab
```

Run this command only if your cloned repository has the nested `java-version-lab` directory. Otherwise, navigate directly to the directory containing `pom.xml`.

### Step 3: Open the project

Open the directory containing `pom.xml` in IntelliJ IDEA.

Allow IntelliJ to import the Maven project and download dependencies.

### Step 4: Build the application

```bash
mvn clean compile
```

### Step 5: Run the tests

```bash
mvn test
```

### Step 6: Start the application

```bash
mvn spring-boot:run
```

The application will use the port configured in `application.properties`. If no port is specified, Spring Boot typically uses port `8080`.

---

## Project Structure

The following is the intended structure as the learning modules are developed. Some feature packages and API components may be added progressively.

```text
java-version-lab/
│
├── pom.xml
├── README.md
├── .gitignore
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── tejas/
    │   │           └── java_version_lab/
    │   │               │
    │   │               ├── JavaVersionLabApplication.java
    │   │               │
    │   │               ├── controller/
    │   │               │
    │   │               ├── service/
    │   │               │
    │   │               ├── dto/
    │   │               │
    │   │               ├── exception/
    │   │               │
    │   │               └── javafeatures/
    │   │                   ├── java8/
    │   │                   ├── java9/
    │   │                   ├── java10/
    │   │                   ├── java11/
    │   │                   ├── java12/
    │   │                   ├── java13/
    │   │                   ├── java14/
    │   │                   ├── java15/
    │   │                   ├── java16/
    │   │                   ├── java17/
    │   │                   ├── java18/
    │   │                   ├── java19/
    │   │                   ├── java20/
    │   │                   ├── java21/
    │   │                   ├── java22/
    │   │                   ├── java23/
    │   │                   ├── java24/
    │   │                   └── java25/
    │   │
    │   └── resources/
    │       └── application.properties
    │
    └── test/
        └── java/
            └── com/
                └── tejas/
                    └── java_version_lab/
```

### Package responsibilities

| Package                        | Responsibility                                 |
| ------------------------------ | ---------------------------------------------- |
| `controller`                   | REST endpoints for accessing learning examples |
| `service`                      | Business logic and feature execution           |
| `dto`                          | Request and response data transfer objects     |
| `exception`                    | Custom exceptions and error handling           |
| `javafeatures.java8`           | Examples of Java 8 features                    |
| `javafeatures.java9`           | Examples of Java 9 features                    |
| `javafeatures.java10`          | Examples of Java 10 features                   |
| `javafeatures.java11`–`java25` | Examples for the respective Java releases      |

The feature packages can be introduced gradually rather than creating every package at the beginning.

---

## Java Version-Wise Features

This section summarizes important language and platform features worth exploring. The listed topics are a learning roadmap, not a claim that every example is already implemented.

### Java 8 — Functional Programming

Java 8 introduced major changes to how developers write Java code.

Important features:

* Lambda expressions.
* Functional interfaces.
* Stream API.
* Method references.
* Default and static methods in interfaces.
* `Optional`.
* New Date and Time API.
* `CompletableFuture`.
* Collectors and parallel streams.

Example:

```java
List<String> names = List.of("Tejas", "Amit", "Rahul");

names.stream()
     .filter(name -> name.startsWith("T"))
     .forEach(System.out::println);
```

*Note: `List.of()` was introduced in Java 9. For a Java 8-compatible version of this example, use `Arrays.asList()`.*

Java 8-compatible example:

```java
List<String> names = Arrays.asList("Tejas", "Amit", "Rahul");

names.stream()
     .filter(name -> name.startsWith("T"))
     .forEach(System.out::println);
```

### Java 9 — Modules and Collection Improvements

Important features:

* Java Platform Module System.
* JShell.
* Factory methods for immutable collections.
* Private interface methods.
* Try-with-resources improvements.
* Stream API enhancements.

Example:

```java
List<String> names = List.of("Tejas", "Amit", "Rahul");
```

`List.of()` creates an unmodifiable list.

### Java 10 — Local Variable Type Inference

Important feature:

* `var` for local variable declarations.

Example:

```java
var name = "Tejas";
var count = 10;
```

The compiler infers the types. Java remains statically typed, and `var` is not a replacement for every explicit type declaration.

### Java 11 — Standard HTTP Client and String Utilities

Important features:

* Standard `HttpClient`.
* `var` in lambda parameters.
* New String methods such as `isBlank()`, `strip()`, `lines()`, and `repeat()`.
* Single-file source-code execution.
* Standardized APIs introduced in this release.

Example:

```java
String value = "   ";

System.out.println(value.isBlank());
```

### Java 12 — Switch Expressions Preview

Important feature:

* Switch expressions were introduced as a preview feature in Java 12.

Example:

```java
int result = switch (day) {
    case 1 -> 10;
    case 2 -> 20;
    default -> 0;
};
```

Switch expressions became a standard feature in Java 14.

### Java 13 — Text Blocks Preview

Important feature:

* Text blocks were introduced as a preview feature.

Example:

```java
String json = """
        {
          "name": "Tejas",
          "language": "Java"
        }
        """;
```

Text blocks became a standard feature in Java 15.

### Java 14 — Standard Switch Expressions

Important features:

* Standard switch expressions.
* Records introduced as a preview feature.
* Helpful NullPointerException messages.

Example:

```java
String type = switch (number) {
    case 1 -> "One";
    case 2 -> "Two";
    default -> "Other";
};
```

### Java 15 — Text Blocks and Sealed Classes Preview

Important features:

* Standard text blocks.
* Sealed classes introduced as a preview feature.
* Records continued through preview development.

Example:

```java
String sql = """
        SELECT id, name
        FROM employees
        WHERE active = true
        """;
```

### Java 16 — Records and Pattern Matching Preview

Important features:

* Records became standard.
* Pattern matching for `instanceof` became standard.
* Stream API `toList()` method.
* Stronger encapsulation of JDK internals.

Example:

```java
record Employee(long id, String name) {}
```

A record is useful for concise data-carrier classes.

### Java 17 — LTS, Sealed Classes, and Pattern Matching

Important features:

* Long-Term Support release.
* Sealed classes became standard.
* Pattern matching for `switch` continued through preview development.
* Strong encapsulation of JDK internals.

Example:

```java
public sealed interface Shape
        permits Circle, Rectangle {
}
```

### Java 18 — UTF-8 by Default

Important features:

* UTF-8 became the default charset.
* Simple web server.
* Code snippets in Java API documentation.
* Continued improvements to the JVM and runtime.

Example:

```java
String message = "Java Learning Lab";
System.out.println(message);
```

### Java 19 — Virtual Threads Preview

Important features:

* Virtual threads introduced as a preview feature.
* Record patterns introduced as a preview feature.
* Pattern matching for `switch` continued through preview development.

Example:

```java
Thread thread = Thread.ofVirtual()
        .start(() -> System.out.println("Running in a virtual thread"));

thread.join();
```

### Java 20 — Continued Preview Features

Important features:

* Virtual threads continued through preview development.
* Record patterns continued through preview development.
* Pattern matching for `switch` continued through preview development.
* Scoped values and structured concurrency were explored through preview APIs.

This release is useful for studying how major Java features evolve before becoming standard.

### Java 21 — LTS and Modern Concurrency

Important features:

* Virtual threads became standard.
* Pattern matching for `switch` became standard.
* Record patterns became standard.
* Sequenced collections.
* String templates introduced as a preview feature.
* Structured concurrency and scoped values introduced as preview APIs.

Example:

```java
Thread.startVirtualThread(
        () -> System.out.println("Hello from a virtual thread")
);
```

### Java 22 — Foreign Function and Memory API

Important features:

* Foreign Function and Memory API became standard.
* Unnamed variables and patterns.
* Stream Gatherers introduced as a preview feature.
* Continued improvements to pattern matching and concurrency APIs.

The Foreign Function and Memory API provides a supported way to interact with native code and memory outside the traditional JNI approach.

### Java 23 — Language and Runtime Improvements

Important features:

* Primitive types in patterns, `instanceof`, and `switch` continued through preview development.
* Module import declarations introduced as a preview feature.
* Stream Gatherers continued through preview development.
* Continued improvements to garbage collection and developer tooling.

### Java 24 — Performance and Language Improvements

Important features:

* Generational Shenandoah became a standard feature.
* Class-file API became standard.
* Stream Gatherers became standard.
* Flexible constructor bodies became standard.
* Several additional preview and runtime improvements.

### Java 25 — LTS and Modern Java

Important features:

* Long-Term Support release.
* Module import declarations became standard.
* Compact source files and instance `main` methods became standard.
* Flexible constructor bodies became standard.
* Primitive types in patterns, `instanceof`, and `switch` continued through preview development.
* Improvements to garbage collection, runtime performance, and developer productivity.

This repository uses Java 25 as its main development JDK.

**Version note:** Preview and incubator features may require special compiler flags and can change between releases. Verify the release in which each feature became standard before using it in production code.

---

## Java 8 Features in Detail

Java 8 is a foundational release for modern Java development. These topics should form the first complete learning module.

### 1. Lambda Expressions

A lambda expression provides a concise way to represent behavior.

Traditional anonymous class:

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task executed");
    }
};
```

Lambda expression:

```java
Runnable task = () ->
        System.out.println("Task executed");
```

### 2. Functional Interfaces

A functional interface has exactly one abstract method.

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

Usage:

```java
Calculator addition = (a, b) -> a + b;

System.out.println(addition.calculate(10, 20));
```

Output:

```text
30
```

Common functional interfaces:

* `Predicate<T>` — tests a condition.
* `Function<T, R>` — transforms a value.
* `Consumer<T>` — consumes a value.
* `Supplier<T>` — supplies a value.
* `UnaryOperator<T>` — transforms a value of the same type.
* `BinaryOperator<T>` — combines two values of the same type.

### 3. Stream API

The Stream API supports declarative data processing.

```java
List<Integer> numbers =
        Arrays.asList(10, 15, 20, 25, 30);

List<Integer> evenNumbers = numbers.stream()
        .filter(number -> number % 2 == 0)
        .collect(Collectors.toList());

System.out.println(evenNumbers);
```

Output:

```text
[10, 20, 30]
```

Important operations:

| Operation    | Purpose                                   |
| ------------ | ----------------------------------------- |
| `filter()`   | Selects elements that satisfy a condition |
| `map()`      | Transforms elements                       |
| `flatMap()`  | Flattens nested structures                |
| `sorted()`   | Sorts elements                            |
| `distinct()` | Removes duplicates                        |
| `limit()`    | Limits the number of elements             |
| `reduce()`   | Combines elements into a result           |
| `collect()`  | Accumulates elements into a result        |
| `forEach()`  | Performs an action on each element        |

### 4. Method References

Method references provide a concise alternative to certain lambda expressions.

Lambda:

```java
names.forEach(name -> System.out.println(name));
```

Method reference:

```java
names.forEach(System.out::println);
```

### 5. Optional

`Optional` represents a value that may or may not be present.

```java
Optional<String> name = Optional.ofNullable(null);

System.out.println(name.orElse("Unknown"));
```

Output:

```text
Unknown
```

### 6. Date and Time API

Java 8 introduced modern date and time classes.

```java
LocalDate today = LocalDate.now();

LocalDate nextWeek = today.plusWeeks(1);

System.out.println(today);
System.out.println(nextWeek);
```

Common classes:

* `LocalDate`
* `LocalTime`
* `LocalDateTime`
* `ZonedDateTime`
* `Instant`
* `Duration`
* `Period`
* `DateTimeFormatter`

---

## Spring Boot Application

The project uses Spring Boot to provide a backend application through which learning examples can be organized and exposed as REST endpoints.

### Application entry point

```java
@SpringBootApplication
public class JavaVersionLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                JavaVersionLabApplication.class,
                args
        );
    }
}
```

### Planned application responsibilities

* Expose Java feature examples through REST APIs.
* Organize feature logic by Java version.
* Return structured responses for learning examples.
* Provide a central place to explore language features.
* Add unit tests for feature implementations.
* Support future additions such as exercise endpoints and feature comparisons.

These capabilities can be implemented incrementally as the repository grows.

---

## Running the Application

### Start through Maven

```bash
mvn spring-boot:run
```

### Build a JAR

```bash
mvn clean package
```

### Run the packaged JAR

```bash
java -jar target/java-version-lab-0.0.1-SNAPSHOT.jar
```

The exact JAR filename may change with the artifact version.

### Run through IntelliJ IDEA

1. Open the project containing `pom.xml`.
2. Wait for Maven dependency import to finish.
3. Confirm the Project SDK is configured for Java 25.
4. Open `JavaVersionLabApplication.java`.
5. Click the run icon next to the `main` method.

---

## API Endpoints

The REST endpoints below are **proposed examples** for the learning application. They should be added to the code before being treated as available endpoints.

| Method | Example endpoint                                 | Purpose                            |
| ------ | ------------------------------------------------ | ---------------------------------- |
| GET    | `/api/java-versions`                             | List supported Java versions       |
| GET    | `/api/java-versions/8/features`                  | List Java 8 features               |
| GET    | `/api/java-versions/21/features`                 | List Java 21 features              |
| GET    | `/api/java-versions/25/features`                 | List Java 25 features              |
| GET    | `/api/java-versions/8/examples/streams`          | Explore Java 8 Stream API examples |
| GET    | `/api/java-versions/21/examples/virtual-threads` | Explore virtual-thread examples    |
| GET    | `/api/java-versions/compare?from=8&to=25`        | Compare features across releases   |

### Example response

A version-list endpoint could return:

```json
[
  {
    "version": 8,
    "feature": "Lambda Expressions",
    "category": "Language"
  },
  {
    "version": 8,
    "feature": "Stream API",
    "category": "Collections and Processing"
  }
]
```

The exact response format should match the DTOs implemented in the application.

---

## Coding Exercises

The repository should progressively include practical exercises.

### Beginner

* Find even and odd numbers in a list.
* Find duplicate elements in a collection.
* Convert a list of strings to uppercase.
* Count the frequency of words.
* Find the maximum and minimum numbers.
* Sort employees by salary.
* Group employees by department.

### Intermediate

* Find the second-highest salary.
* Group and partition data using collectors.
* Flatten nested lists with `flatMap()`.
* Find duplicate objects using `equals()` and `hashCode()`.
* Process nullable values with `Optional`.
* Combine asynchronous tasks using `CompletableFuture`.
* Compare records with traditional data classes.
* Write a custom collector or Stream Gatherer.

### Advanced

* Implement a producer-consumer workflow.
* Compare platform threads and virtual threads.
* Build a concurrent data-processing example.
* Explore garbage collection and JVM behavior.
* Benchmark alternative collection-processing strategies.
* Compare structured concurrency APIs across releases.
* Explore the Foreign Function and Memory API.

Each exercise should include the problem statement, example input, expected output, implementation, and tests.

---

## Java Version Comparison

This repository can be used to compare traditional approaches with newer Java features.

| Concept                 | Earlier approach                  | Modern approach                        |
| ----------------------- | --------------------------------- | -------------------------------------- |
| Behavior representation | Anonymous classes                 | Lambda expressions                     |
| Collection processing   | Explicit loops                    | Stream API                             |
| Method invocation       | Lambda forwarding                 | Method references                      |
| Nullable values         | Manual null checks                | `Optional` where appropriate           |
| Data carrier classes    | Traditional POJOs                 | Records                                |
| Type checks             | Explicit casts after `instanceof` | Pattern matching                       |
| Multi-line strings      | Escaped strings and concatenation | Text blocks                            |
| Concurrency             | Platform threads                  | Virtual threads for suitable workloads |
| Native interoperability | Traditional JNI approaches        | Foreign Function and Memory API        |

Not every newer feature replaces an older approach. The appropriate choice depends on readability, compatibility, performance, and application requirements.

---

## Interview Preparation

This repository can also serve as a Java interview preparation guide.

### Core Java

* Object-oriented programming.
* Inheritance, polymorphism, abstraction, and encapsulation.
* Collections Framework.
* `equals()` and `hashCode()`.
* Exception handling.
* Generics.
* Immutability.
* JVM, JDK, and JRE concepts.
* Garbage collection.

### Java 8

* Lambda expressions.
* Functional interfaces.
* Stream API.
* `map()` versus `flatMap()`.
* `filter()` versus `map()`.
* Intermediate versus terminal operations.
* Lazy evaluation.
* `findFirst()` versus `findAny()`.
* `Optional`.
* `CompletableFuture`.

### Modern Java

* `var` and type inference.
* Records.
* Sealed classes.
* Pattern matching.
* Switch expressions.
* Text blocks.
* Virtual threads.
* Sequenced collections.
* Module system.
* Foreign Function and Memory API.

### Backend development

* Spring Boot auto-configuration.
* Dependency injection.
* REST API design.
* Validation and exception handling.
* Unit testing.
* Maven dependency management.
* Concurrency and performance.
* Java version compatibility.

---

## Learning Roadmap

Follow this order for structured progress.

* [ ] Set up Java 25 and Maven.
* [ ] Build and run the Spring Boot application.
* [ ] Complete Java 8 lambda and functional-interface examples.
* [ ] Complete Stream API exercises.
* [ ] Explore Optional and the Date and Time API.
* [ ] Study Java 9–11 improvements.
* [ ] Study switch expressions, text blocks, and records.
* [ ] Explore sealed classes and pattern matching.
* [ ] Learn virtual threads and modern concurrency.
* [ ] Explore Java 22–25 language and runtime improvements.
* [ ] Add unit tests for examples.
* [ ] Add REST endpoints for implemented examples.
* [ ] Document interview questions and version comparisons.

---

## Build and Test

Compile the project:

```bash
mvn clean compile
```

Run tests:

```bash
mvn test
```

Create the packaged application:

```bash
mvn clean package
```

Run tests before committing significant changes.

---

## Git Workflow

Clone the repository:

```bash
git clone https://github.com/tejasmohitkar22/java-version-lab-.git
```

Check the current branch and working tree:

```bash
git status
```

Stage changes:

```bash
git add .
```

Commit changes:

```bash
git commit -m "Add Java feature examples"
```

Push to GitHub:

```bash
git push origin main
```

Use descriptive commit messages and avoid committing credentials, generated build artifacts, or machine-specific IDE files.

---

## Contributing

Contributions and suggestions are welcome.

To contribute:

1. Fork the repository or create a feature branch.
2. Add a focused Java example.
3. Include comments where they clarify important behavior.
4. Add tests where appropriate.
5. Document the Java version required by the example.
6. Run the build and tests.
7. Submit a pull request with a clear explanation.

### Suggested naming convention

Use descriptive class names such as:

```text
LambdaExpressionExample.java
StreamFilterExample.java
OptionalExample.java
RecordExample.java
VirtualThreadExample.java
```

Organize examples in the package corresponding to the Java release being studied.

---

## Resources

Use official documentation as the primary reference.

* [Java Documentation](https://docs.oracle.com/en/java/)
* [Java Language Changes](https://docs.oracle.com/en/java/javase/25/language/java-language-changes.html)
* [Java API Documentation](https://docs.oracle.com/en/java/javase/25/docs/api/)
* [OpenJDK](https://openjdk.org/)
* [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
* [Apache Maven Documentation](https://maven.apache.org/guides/)
* [JUnit Documentation](https://junit.org/junit5/docs/current/user-guide/)

Always verify the minimum Java version and whether a feature is standard, preview, or incubating before using it.

---

## License

A license has not yet been specified. Add a `LICENSE` file and update this section once the project license has been selected.

---

## Author

**Tejas Mohitkar**

GitHub: [@tejasmohitkar22](https://github.com/tejasmohitkar22)

---

## Final Goal

Build a practical, version-wise Java reference that demonstrates how Java evolved from Java 8 to Java 25 through executable examples, coding exercises, tests, and real-world backend use cases.

**Learn the feature. Understand the problem. Write the code. Test the result. Compare the versions.**
