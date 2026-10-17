package com.tejas.java_version_lab.java10;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ImportantJava10 {
    public static void main(String[] args) {

        System.out.println("===== 1. toUnmodifiableList() =====");
        unmodifiableListExample();

        System.out.println("\n===== 2. toUnmodifiableSet() =====");
        unmodifiableSetExample();

        System.out.println("\n===== 3. toUnmodifiableMap() =====");
        unmodifiableMapExample();
    }

    // =====================================================
    // 1. Collectors.toUnmodifiableList()
    // =====================================================

    /*
     * Java 10 introduced Collectors.toUnmodifiableList().
     *
     * It collects Stream elements into an unmodifiable List.
     * Attempts to add, remove or replace elements throw
     * UnsupportedOperationException.
     */

    private static void unmodifiableListExample() {

        List<String> names = List.of(
                "Tejas", "Rahul", "Amit"
        );

        List<String> result = names.stream()
                .filter(name -> !name.equals("Rahul"))
                .collect(Collectors.toUnmodifiableList());

        System.out.println("Result: " + result);

        // result.add("Priya"); // UnsupportedOperationException
        // result.remove("Tejas"); // UnsupportedOperationException

        /*
         * Output:
         * Result: [Tejas, Amit]
         *
         * Use case:
         * Returning a list of users or configuration values
         * that callers should not be able to modify.
         */
    }

    // =====================================================
    // 2. Collectors.toUnmodifiableSet()
    // =====================================================

    /*
     * Collects Stream elements into an unmodifiable Set.
     *
     * Sets do not contain duplicate elements.
     * The iteration order is not guaranteed.
     */

    private static void unmodifiableSetExample() {

        List<String> technologies = List.of(
                "Java", "Spring Boot", "Java", "Kafka"
        );

        Set<String> result = technologies.stream()
                .collect(Collectors.toUnmodifiableSet());

        System.out.println("Result: " + result);

        // result.add("Docker"); // UnsupportedOperationException

        /*
         * Output contains:
         * Java, Spring Boot and Kafka
         *
         * The order may differ.
         *
         * Use case:
         * Returning a set of unique roles, permissions,
         * feature names or supported technologies.
         */
    }

    // =====================================================
    // 3. Collectors.toUnmodifiableMap()
    // =====================================================

    /*
     * Collects stream elements into an unmodifiable Map.
     *
     * We provide:
     * - A function to generate each key
     * - A function to generate each value
     *
     * Duplicate keys cause IllegalStateException unless
     * a merge strategy is supplied to an appropriate collector.
     *
     * Null keys and values are not supported.
     */

    private static void unmodifiableMapExample() {

        List<String> names = List.of(
                "Tejas", "Rahul", "Amit"
        );

        Map<Integer, String> result = names.stream()
                .collect(Collectors.toUnmodifiableMap(
                        String::length,
                        name -> name
                ));

        System.out.println("Result: " + result);

        // result.put(5, "Priya"); // UnsupportedOperationException

        /*
         * Example result:
         * {3=Amit, 4=Tejas, 5=Rahul}
         *
         * Each name length is used as a key.
         * The chosen names have distinct lengths.
         *
         * If two names have the same length, duplicate keys
         * cause IllegalStateException in this example.
         *
         * Use case:
         * Creating an immutable lookup map for configuration,
         * reference data or application rules.
         */
    }
}
