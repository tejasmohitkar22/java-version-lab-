package com.tejas.java_version_lab.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;

public class MustLearnJava11 {
    public static void main(String[] args) throws Exception {

        System.out.println("===== 1. HTTP CLIENT API =====");
        httpClientExample();

        System.out.println("\n===== 2. STRING METHODS =====");
        stringMethodsExample();

        System.out.println("\n===== 3. OPTIONAL.isEmpty() =====");
        optionalIsEmptyExample();
    }

    // =====================================================
    // 1. HTTP CLIENT, HTTPREQUEST AND HTTPRESPONSE
    // =====================================================

    /*
     * Java 11 standardized the modern HTTP Client API.
     *
     * HttpClient   -> Sends HTTP requests.
     * HttpRequest  -> Represents the request.
     * HttpResponse -> Represents the response.
     *
     * Supports:
     * - GET and POST requests
     * - Synchronous calls using send()
     * - Asynchronous calls using sendAsync()
     * - Request timeouts
     * - HTTP/1.1 and HTTP/2
     *
     * The example uses a public test API.
     * Internet access is required to execute it successfully.
     */

    private static void httpClientExample()
            throws IOException, InterruptedException {

        // Step 1: Create the HTTP client.
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .version(HttpClient.Version.HTTP_2)
                .build();

        // Step 2: Build the HTTP request.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://jsonplaceholder.typicode.com/posts/1"
                ))
                .timeout(Duration.ofSeconds(15))
                .header("Accept", "application/json")
                .GET()
                .build();

        // Step 3: Send the request synchronously.
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        // Step 4: Read the response.
        System.out.println(
                "HTTP status: " + response.statusCode()
        );

        System.out.println(
                "Response body: " + response.body()
        );

        /*
         * Example status:
         * HTTP status: 200
         *
         * The body is JSON returned by the test API.
         *
         * send() blocks the current thread until a response
         * is received or the call fails.
         */

        // -------------------------------------------------
        // ASYNCHRONOUS HTTP REQUEST
        // -------------------------------------------------

        /*
         * sendAsync() returns CompletableFuture.
         * It lets the caller continue without waiting
         * synchronously for the HTTP response.
         */

        HttpRequest asyncRequest = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://jsonplaceholder.typicode.com/posts/2"
                ))
                .GET()
                .build();

        String asyncBody = client.sendAsync(
                        asyncRequest,
                        HttpResponse.BodyHandlers.ofString()
                )
                .thenApply(HttpResponse::body)
                .join();

        System.out.println(
                "Async response body: " + asyncBody
        );

        /*
         * INTERVIEW ANSWER:
         *
         * "Java 11 introduced the standard HttpClient API.
         * HttpClient sends requests, HttpRequest defines the
         * request, and HttpResponse contains the result.
         * send() is synchronous, while sendAsync() returns a
         * CompletableFuture for asynchronous processing."
         *
         * In Spring applications, RestClient, WebClient or
         * other HTTP clients may be more appropriate depending
         * on the Spring version and application requirements.
         */
    }

    // =====================================================
    // 2. STRING METHODS
    // =====================================================

    /*
     * Java 11 added useful String methods:
     *
     * isBlank() -> Checks whether a string is empty or contains
     *              only whitespace according to Java's
     *              whitespace definition.
     *
     * lines()   -> Returns a stream of lines.
     *
     * strip()   -> Removes leading and trailing Unicode
     *              whitespace according to Character.isWhitespace.
     *
     * repeat()  -> Repeats a string a specified number of times.
     */

    private static void stringMethodsExample() {

        // ---------- isBlank() ----------

        String text1 = "";
        String text2 = "     ";
        String text3 = "Java";

        System.out.println(
                "Empty string is blank: " + text1.isBlank()
        );

        System.out.println(
                "Whitespace string is blank: " + text2.isBlank()
        );

        System.out.println(
                "Java is blank: " + text3.isBlank()
        );

        /*
         * Output:
         * true
         * true
         * false
         *
         * Difference:
         * isEmpty() checks length == 0.
         * isBlank() also returns true for whitespace-only strings.
         */


        // ---------- strip() ----------

        String input = "   Java 11   ";

        System.out.println(
                "Original: [" + input + "]"
        );

        System.out.println(
                "Stripped: [" + input.strip() + "]"
        );

        /*
         * Output:
         * Original: [   Java 11   ]
         * Stripped: [Java 11]
         *
         * strip() is Unicode-aware.
         * trim() uses a more limited character range.
         *
         * stripLeading() removes leading whitespace only.
         * stripTrailing() removes trailing whitespace only.
         */


        // ---------- lines() ----------

        String data = "Java\nSpring Boot\nKafka";

        data.lines().forEach(System.out::println);

        /*
         * Output:
         * Java
         * Spring Boot
         * Kafka
         *
         * lines() returns Stream<String>.
         * It splits the string at line terminators.
         */


        // ---------- repeat() ----------

        String symbol = "*";

        System.out.println(
                symbol.repeat(5)
        );

        System.out.println(
                "Java ".repeat(3)
        );

        /*
         * Output:
         * *****
         * Java Java Java
         *
         * repeat(0) returns an empty string.
         * A negative repetition count throws
         * IllegalArgumentException.
         */


        // ---------- Practical validation example ----------

        String username = "   ";

        if (username.isBlank()) {
            System.out.println(
                    "Username cannot be blank"
            );
        }

        String cleanedUsername = "  Tejas  ".strip();

        System.out.println(
                "Clean username: " + cleanedUsername
        );
    }

    // =====================================================
    // 3. OPTIONAL.isEmpty()
    // =====================================================

    /*
     * Optional.isEmpty() was introduced in Java 11.
     *
     * isPresent() -> true when a value exists.
     * isEmpty()   -> true when no value exists.
     *
     * It improves readability when checking for absence.
     */

    private static void optionalIsEmptyExample() {

        Optional<String> present =
                Optional.of("Java");

        Optional<String> empty =
                Optional.empty();

        System.out.println(
                "Present Optional is empty: " +
                        present.isEmpty()
        );

        System.out.println(
                "Empty Optional is empty: " +
                        empty.isEmpty()
        );

        /*
         * Output:
         * Present Optional is empty: false
         * Empty Optional is empty: true
         */


        // ---------- Practical example ----------

        Optional<String> username =
                findUsernameById(101);

        if (username.isEmpty()) {
            System.out.println("Username not found");
        } else {
            System.out.println(
                    "Username: " + username.orElseThrow()
            );
        }

        /*
         * In a real Spring Boot service:
         *
         * Optional<User> user = repository.findById(id);
         *
         * if (user.isEmpty()) {
         *     throw new UserNotFoundException("User not found");
         * }
         *
         * User result = user.orElseThrow();
         *
         * This is an example; the actual exception and response
         * should follow the application's error-handling design.
         */
    }

    private static Optional<String> findUsernameById(int id) {

        if (id == 101) {
            return Optional.of("Tejas");
        }

        return Optional.empty();
    }
}
