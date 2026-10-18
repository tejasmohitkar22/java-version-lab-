package com.tejas.java_version_lab.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java 11 features and platform changes:
 * 1. HTTP/2 and asynchronous HTTP requests
 * 2. Files.readString() and Files.writeString()
 * 3. Predicate.not()
 * 4. var in lambda parameters
 * 5. TLS 1.3
 * 6. Java Flight Recorder (JFR)
 * 7. Java EE and CORBA module removal
 *
 * Run with JDK 11 or later.
 */
public class ImportantJava11 {

    public static void main(String[] args) throws Exception {
        httpClientHttp2AndAsync();
        filesReadStringAndWriteString();
        predicateNot();
        varInLambdaParameters();
        tls13Information();
        flightRecorderInformation();
        javaEeAndCorbaRemovalInformation();
    }

    /**
     * HttpClient was introduced in Java 11.
     * It supports HTTP/1.1 and HTTP/2, synchronous send(), and asynchronous sendAsync().
     *
     * This example calls a public test endpoint and therefore requires internet access.
     * HTTP_2 is a preferred protocol version; the server/connection may negotiate another version.
     */
    private static void httpClientHttp2AndAsync() throws Exception {
        System.out.println("\n1. HTTP/2 and asynchronous HTTP requests");

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos/1"))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        // Synchronous request: the current thread waits for the response.
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Synchronous status: " + response.statusCode());
        System.out.println("Negotiated HTTP version: " + response.version());
        System.out.println("Response body: " + response.body());

        // Asynchronous request: sendAsync() returns immediately with a CompletableFuture.
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenAccept(status -> System.out.println("Asynchronous status: " + status))
                .exceptionally(error -> {
                    System.err.println("Async HTTP request failed: " + error.getMessage());
                    return null;
                })
                .join(); // Wait here only so this small demo can finish before main exits.
    }

    /**
     * Files.readString() and Files.writeString() were added in Java 11.
     * They are convenient for small text files such as configuration and test fixtures.
     * The default overloads use UTF-8; the overloads below specify the charset explicitly.
     */
    private static void filesReadStringAndWriteString() throws IOException {
        System.out.println("\n2. Files.readString() and Files.writeString()");

        Path file = Path.of("java11-demo.txt");
        String content = "Hello from Java 11!\nFiles.readString() and writeString() are convenient.";

        Files.writeString(file, content, StandardCharsets.UTF_8);
        String readContent = Files.readString(file, StandardCharsets.UTF_8);

        System.out.println("File content:\n" + readContent);

        // Remove the demo file after reading it.
        Files.deleteIfExists(file);
    }

    /**
     * Predicate.not() was introduced in Java 11.
     * It returns the negation of a Predicate and is useful with method references.
     */
    private static void predicateNot() {
        System.out.println("\n3. Predicate.not()");

        List<String> names = Arrays.asList("Tejas", "", "Asha", " ", "Rahul");

        List<String> nonBlankNames = names.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());

        System.out.println("Non-blank names: " + nonBlankNames);
    }

    /**
     * Java 11 allows 'var' in lambda parameter lists.
     * Use 'var' for all lambda parameters when using this syntax.
     * It is particularly useful when adding annotations to lambda parameters.
     */
    private static void varInLambdaParameters() {
        System.out.println("\n4. var in lambda parameters");

        List<String> names = Arrays.asList("tejas", "asha", "rahul");

        names.forEach((var name) -> System.out.println(name.toUpperCase()));

        // The 'var' type is inferred by the compiler; Java remains statically typed.
        // Valid annotated example:
        // names.forEach((@SuppressWarnings("unused") var name) -> System.out.println(name));
    }

    /**
     * TLS 1.3 support was added in Java 11
     * TLS is a protocol used to encrypt and authenticate network communication
     *
     * This method reports the default enabled TLS protocols for the current JDK
     * It does not establish a TLS connection
     */
    private static void tls13Information() throws java.security.NoSuchAlgorithmException {
        System.out.println("\n5. TLS 1.3");

        String[] supportedProtocols =
                javax.net.ssl.SSLContext.getDefault()
                        .getSupportedSSLParameters()
                        .getProtocols();

        System.out.println("Supported TLS/SSL protocols:");
        Arrays.stream(supportedProtocols)
                .filter(protocol -> protocol.startsWith("TLS"))
                .sorted()
                .forEach(System.out::println);

        // For production, use secure defaults and keep the JDK updated.
        // Avoid enabling obsolete protocols such as SSLv3 or TLS 1.0/1.1.
    }

    /**
     * Java Flight Recorder (JFR) is a low-overhead event recording and diagnostics tool.
     * JFR was available in earlier Oracle JDK releases under different licensing;
     * in OpenJDK it became generally available in JDK 11.
     *
     * Start a recording from the command line, for example:
     *   java -XX:StartFlightRecording=filename=app.jfr,duration=60s \
     *        com.tejas.java_version_lab.java11.ImportantJava11
     *
     * Inspect recordings with JDK Mission Control (JMC) or the jfr command-line tool.
     */
    private static void flightRecorderInformation() {
        System.out.println("\n6. Java Flight Recorder (JFR)");
        System.out.println("JFR records diagnostic events such as CPU, allocation, GC and thread activity.");
        System.out.println("Example JVM option: -XX:StartFlightRecording=filename=app.jfr,duration=60s");
        System.out.println("Inspect the recording using JDK Mission Control or the jfr command.");
    }

    /**
     * Java 11 removed Java EE and CORBA modules from the JDK.
     * Legacy applications that relied on APIs bundled with older JDKs may need
     * explicit dependencies from external libraries or Jakarta EE-compatible replacements.
     *
     * Examples of removed modules include java.xml.bind (JAXB),
     * java.xml.ws (JAX-WS), java.activation, java.corba and java.transaction.
     */
    private static void javaEeAndCorbaRemovalInformation() {
        System.out.println("\n7. Java EE and CORBA module removal");
        System.out.println("Java 11 no longer bundles several Java EE and CORBA modules.");
        System.out.println("Legacy applications may need explicit dependencies or API migration.");
        System.out.println("Check your build configuration when migrating from Java 8 to Java 11.");
    }
}
