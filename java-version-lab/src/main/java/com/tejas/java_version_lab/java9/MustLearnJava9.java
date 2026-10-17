package com.tejas.java_version_lab.java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class MustLearnJava9 {

    public static void main(String[] args) throws IOException {

        collectionFactoryMethods();
        streamImprovements();
        optionalImprovements();
        tryWithResourcesImprovement();
    }

    // 1. List.of(), Set.of(), Map.of()
    // These collections are unmodifiable.
    // names.add("Priya"); // Throws UnsupportedOperationException
    // skills.add("React"); // Throws UnsupportedOperationException
    // employees.put(104, "Priya"); // Throws UnsupportedOperationException
    private static void collectionFactoryMethods() {

        System.out.println("\n--- Collection Factory Methods ---");

        List<String> names = List.of("Tejas", "Rahul", "Amit");
        System.out.println("List: " + names);

        Set<String> skills = Set.of("Java", "Spring Boot", "Kafka");
        System.out.println("Set: " + skills);

        Map<Integer, String> employees = Map.of(
                101, "Tejas",
                102, "Rahul",
                103, "Amit"
        );
        System.out.println("Map: " + employees);
    }

    // 2. takeWhile(), dropWhile(), iterate()
    private static void streamImprovements() {

        System.out.println("\n--- Stream Improvements ---");

        List<Integer> numbers =
                List.of(2, 4, 6, 7, 8, 10);

        // Take elements while the condition remains true.
        System.out.println("takeWhile:");
        numbers.stream()
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);

        // Drop the longest initial sequence matching the condition.
        System.out.println("dropWhile:");
        numbers.stream()
                .dropWhile(n -> n % 2 == 0)
                .forEach(System.out::println);

        // Java 9 iterate(seed, predicate, nextValue)
        System.out.println("iterate:");
        Stream.iterate(1, n -> n <= 5, n -> n + 1)
                .forEach(System.out::println);
    }

    // 3. Optional improvements
    private static void optionalImprovements() {

        System.out.println("\n--- Optional Improvements ---");

        Optional<String> username = Optional.of("Tejas");

        username.ifPresentOrElse(
                name -> System.out.println("Welcome " + name),
                () -> System.out.println("Username not available")
        );

        Optional<String> emptyUsername = Optional.empty();

        String result = emptyUsername
                .or(() -> Optional.of("Guest"))
                .get();

        System.out.println("Fallback username: " + result);

        List<Optional<String>> values = List.of(
                Optional.of("Java"),
                Optional.empty(),
                Optional.of("Spring Boot")
        );

        List<String> technologies = values.stream()
                .flatMap(Optional::stream)
                .toList();

        System.out.println("Technologies: " + technologies);
    }

    // 4. Try-with-resources improvement
    private static void tryWithResourcesImprovement()
            throws IOException {

        System.out.println("\n--- Try-with-Resources Improvement ---");

        BufferedReader reader = new BufferedReader(
                new StringReader("Java 9\nTry-with-resources")
        );

        // Java 9: reuse an existing effectively final resource.
        try (reader) {
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
        }
    }
}
