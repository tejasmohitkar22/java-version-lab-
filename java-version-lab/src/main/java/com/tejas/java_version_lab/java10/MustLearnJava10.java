package com.tejas.java_version_lab.java10;

import java.util.*;

public class MustLearnJava10 {
    public static void main(String[] args) {

        System.out.println("===== 1. var KEYWORD =====");
        varKeywordExample();

        System.out.println("\n===== 2. copyOf() COLLECTIONS =====");
        copyOfExample();

        System.out.println("\n===== 3. OPTIONAL.orElseThrow() =====");
        optionalOrElseThrowExample();
    }

    // =====================================================
    // 1. var KEYWORD
    // =====================================================

    /*
     * Java 10 introduced local variable type inference using var
     *
     * The compiler infers the variable's type from its initializer
     * Java remains statically typed; var does NOT make Java
     * dynamically typed
     */

    private static void varKeywordExample() {

        // Before Java 10:
        String name = "Tejas";
        int age = 28;
        List<String> skills = new ArrayList<>();

        // Java 10:
        var city = "Pune";               // Inferred as String
        var experience = 3.10;           // Inferred as double
        var numbers = List.of(10, 20, 30); // List<Integer>

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Experience value: " + experience);
        System.out.println("Numbers: " + numbers);

        /*
         * RULES FOR var:
         *
         * 1. It is allowed for local variables with an initializer.
         *
         *    var message = "Hello";  // Valid
         *
         * 2. It cannot be used for fields.
         *
         *    var value = 10;         // Invalid as a class field
         *
         * 3. It cannot be used for method parameters or return types.
         *
         * 4. It cannot be initialized without a value.
         *
         *    var count;              // Invalid
         *
         * 5. It cannot be initialized with only null because
         *    the compiler cannot infer the type.
         *
         *    var result = null;      // Invalid
         *
         * 6. The inferred type cannot change afterward.
         *
         *    var count = 10;          // int
         *    count = "Java";          // Invalid
         *
         * 7. Use var only when the initializer makes the type clear.
         */

        var message = "Java 10";
        System.out.println("Message: " + message);

        // The type is still String:
        // message = 100; // Compile-time error

        /*
         * NOTE:
         * var is not a reserved keyword in the traditional sense;
         * it is a reserved type name and cannot be used as a type
         * name for a class or interface.
         */
    }

    // =====================================================
    // 2. copyOf() COLLECTIONS
    // =====================================================

    /*
     * Java 10 introduced copyOf() methods for List, Set and Map.
     *
     * They create unmodifiable copies of the supplied collections.
     * The returned collection cannot be structurally modified.
     *
     * This is useful for defensive copying: callers cannot change
     * the returned collection through its modification methods.
     */

    private static void copyOfExample() {

        // ---------- List.copyOf() ----------

        List<String> originalList = new ArrayList<>();
        originalList.add("Java");
        originalList.add("Spring Boot");
        originalList.add("Kafka");

        List<String> copiedList = List.copyOf(originalList);

        System.out.println("Original list: " + originalList);
        System.out.println("Copied list: " + copiedList);

        // copiedList.add("React"); // Throws UnsupportedOperationException

        /*
         * Changing the original collection afterward does not
         * change the copied collection.
         */

        originalList.add("Docker");

        System.out.println("Modified original: " + originalList);
        System.out.println("Copied list remains: " + copiedList);


        // ---------- Set.copyOf() ----------

        Set<String> originalSet = Set.of("Java", "SQL", "Kafka");
        Set<String> copiedSet = Set.copyOf(originalSet);

        System.out.println("Copied set: " + copiedSet);

        // copiedSet.add("AWS"); // UnsupportedOperationException


        // ---------- Map.copyOf() ----------

        Map<Integer, String> originalMap =
                Map.of(
                        101, "Tejas",
                        102, "Rahul",
                        103, "Amit"
                );

        Map<Integer, String> copiedMap = Map.copyOf(originalMap);

        System.out.println("Copied map: " + copiedMap);

        // copiedMap.put(104, "Priya"); // UnsupportedOperationException

        /*
         * IMPORTANT:
         *
         * - List.copyOf() returns an unmodifiable list.
         * - Set.copyOf() returns an unmodifiable set.
         * - Map.copyOf() returns an unmodifiable map.
         * - These methods reject null elements, keys or values.
         * - Set and Map iteration order should not be relied upon.
         * - These are shallow copies: mutable objects inside the
         *   collection are not automatically deep-copied.
         */
    }

    // =====================================================
    // 3. Optional.orElseThrow()
    // =====================================================

    /*
     * Optional.orElseThrow() without arguments was introduced
     * in Java 10.
     *
     * It returns the value if present.
     * If the Optional is empty, it throws NoSuchElementException.
     *
     * Java 8 already supported orElseThrow(Supplier), which lets
     * you provide a specific exception.
     */

    private static void optionalOrElseThrowExample() {

        Optional<String> presentValue = Optional.of("Java 10");

        String value = presentValue.orElseThrow();

        System.out.println("Present value: " + value);


        Optional<String> emptyValue = Optional.empty();

        try {
            String result = emptyValue.orElseThrow();
            System.out.println(result);

        } catch (java.util.NoSuchElementException exception) {
            System.out.println(
                    "Value is missing: " + exception.getClass().getSimpleName()
            );
        }


        // A custom exception can also be supplied:
        Optional<String> username = Optional.empty();

        try {
            String user = username.orElseThrow(
                    () -> new IllegalArgumentException(
                            "Username must be provided"
                    )
            );

            System.out.println(user);

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        /*
         * REAL-WORLD EXAMPLE:
         *
         * Optional<User> user = userRepository.findById(id);
         *
         * User result = user.orElseThrow(
         *     () -> new UserNotFoundException("User not found")
         * );
         *
         * This is useful when the application requires a value
         * and cannot continue if it is missing.
         */
    }
}
