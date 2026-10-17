
package com.tejas.java_version_lab.java9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * IMPORTANT JAVA 9 FEATURES
 *
 * This single file demonstrates:
 *
 * 1. Private methods in interfaces
 * 2. CompletableFuture improvements
 * 3. JPMS (Java Platform Module System)
 * 4. Reactive Streams / Flow API
 *
 * Run this class from IntelliJ IDEA using Java 9 or later.
 */
public class ImportantJava9 {

    public static void main(String[] args) throws Exception {

        System.out.println("====================================");
        System.out.println("1. PRIVATE METHODS IN INTERFACES");
        System.out.println("====================================");

        privateInterfaceMethods();

        System.out.println("\n====================================");
        System.out.println("2. COMPLETABLE FUTURE IMPROVEMENTS");
        System.out.println("====================================");

        completableFutureImprovements();

        System.out.println("\n====================================");
        System.out.println("3. JPMS MODULE SYSTEM");
        System.out.println("====================================");

        jpmsExplanation();

        System.out.println("\n====================================");
        System.out.println("4. REACTIVE STREAMS / FLOW API");
        System.out.println("====================================");

        flowApiExample();

        System.out.println("\nAll Java 9 demonstrations completed.");
    }

    // ============================================================
    // 1. PRIVATE METHODS IN INTERFACES
    // ============================================================

    /*
     * BEFORE JAVA 9:
     *
     * Interfaces could contain:
     * - Abstract methods
     * - Default methods (Java 8)
     * - Static methods (Java 8)
     *
     * JAVA 9:
     *
     * Interfaces can also contain private instance methods
     * and private static methods.
     *
     * WHY?
     *
     * Multiple default methods may need the same implementation.
     * Instead of duplicating code in each default method,
     * we can place the shared logic in a private method.
     *
     * Private interface methods cannot be called directly by
     * implementing classes. They are helpers for the interface.
     */

    private static void privateInterfaceMethods() {

        NotificationService service = new EmailNotificationService();

        service.sendSuccess("Order processed successfully");
        service.sendWarning("Payment is still pending");

        /*
         * Output:
         *
         * [INFO] SUCCESS: Order processed successfully
         * [INFO] WARNING: Payment is still pending
         *
         * Both default methods reuse the same private helper.
         */
    }

    interface NotificationService {

        /*
         * Default methods have an implementation.
         * Implementing classes can use or override them.
         */

        default void sendSuccess(String message) {
            log("SUCCESS: " + message);
        }

        default void sendWarning(String message) {
            log("WARNING: " + message);
        }

        /*
         * Java 9 private instance method.
         *
         * It contains shared implementation used by the
         * interface's default methods.
         */
        private void log(String message) {
            System.out.println(formatMessage(message));
        }

        /*
         * Java 9 private static method.
         *
         * Static interface methods can also reuse private
         * static helper methods.
         */
        private static String formatMessage(String message) {
            return "[INFO] " + message;
        }

        static void printInterfaceName() {
            System.out.println(
                    "Interface: NotificationService"
            );
        }
    }

    static class EmailNotificationService
            implements NotificationService {
        /*
         * No methods need to be implemented here because
         * the interface provides default implementations.
         */
    }

    /*
     * INTERVIEW ANSWER:
     *
     * "Java 9 introduced private methods in interfaces.
     * They allow default and static methods to share common
     * logic without duplicating code. Private interface methods
     * cannot be accessed directly by implementing classes.
     * This improves code reuse and maintainability."
     */


    // ============================================================
    // 2. COMPLETABLE FUTURE IMPROVEMENTS
    // ============================================================

    /*
     * CompletableFuture was introduced in Java 8.
     * Java 9 added useful methods for asynchronous programming,
     * including:
     *
     * - orTimeout()
     * - completeOnTimeout()
     * - failedFuture()
     * - completedStage()
     *
     * These help with timeouts, failure handling and async flows.
     *
     * IMPORTANT:
     *
     * An asynchronous task may run on another thread.
     * supplyAsync() uses the common ForkJoinPool by default,
     * unless an Executor is provided.
     *
     * Do not use Thread.sleep() as a production timeout strategy.
     */

    private static void completableFutureImprovements()
            throws Exception {

        /*
         * EXAMPLE 1: Run independent tasks asynchronously.
         *
         * Imagine two independent external API calls:
         * - Fetch customer details
         * - Fetch order details
         *
         * These calls can run concurrently.
         */

        CompletableFuture<String> customerFuture =
                CompletableFuture.supplyAsync(
                        () -> fetchCustomer()
                );

        CompletableFuture<String> orderFuture =
                CompletableFuture.supplyAsync(
                        () -> fetchOrder()
                );

        /*
         * thenCombine() combines the results of two futures
         * after both complete successfully.
         */

        CompletableFuture<String> combinedFuture =
                customerFuture.thenCombine(
                        orderFuture,
                        (customer, order) ->
                                customer + " | " + order
                );

        System.out.println(
                "Combined result: " + combinedFuture.get()
        );

        /*
         * Possible output:
         *
         * Combined result: Customer: Tejas | Order: Java Book
         *
         * The actual completion order of the two tasks
         * is not guaranteed.
         */


        // --------------------------------------------------------
        // Java 9 improvement: orTimeout()
        // --------------------------------------------------------

        /*
         * orTimeout() completes the future exceptionally with
         * TimeoutException if it does not complete within the
         * specified time.
         *
         * It does not automatically stop the underlying work.
         *
         * Here the task is deliberately given a very short
         * timeout to demonstrate timeout handling.
         */

        CompletableFuture<String> timeoutFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    return "Slow API response";
                });

        try {
            String result = timeoutFuture
                    .orTimeout(100, TimeUnit.MILLISECONDS)
                    .get();

            System.out.println("Timeout result: " + result);

        } catch (Exception e) {
            System.out.println(
                    "Timeout example: " +
                            e.getClass().getSimpleName()
            );
        }


        // --------------------------------------------------------
        // Java 9 improvement: completeOnTimeout()
        // --------------------------------------------------------

        /*
         * completeOnTimeout() completes the future with a
         * fallback value if the original result does not arrive
         * before the timeout.
         *
         * Unlike orTimeout(), it supplies a normal fallback result.
         */

        CompletableFuture<String> fallbackFuture =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }

                    return "Real API response";
                });

        String fallbackResult = fallbackFuture
                .completeOnTimeout(
                        "Fallback response",
                        100,
                        TimeUnit.MILLISECONDS
                )
                .get();

        System.out.println(
                "Fallback result: " + fallbackResult
        );


        // --------------------------------------------------------
        // Java 9 improvement: failedFuture()
        // --------------------------------------------------------

        /*
         * failedFuture() creates a CompletableFuture that is
         * already completed exceptionally.
         *
         * Useful when a method needs to return a failed future
         * instead of throwing an exception immediately.
         */

        CompletableFuture<String> failedFuture =
                CompletableFuture.failedFuture(
                        new IllegalStateException(
                                "Customer service unavailable"
                        )
                );

        try {
            failedFuture.get();
        } catch (Exception e) {
            System.out.println(
                    "Failed future handled: " +
                            e.getCause().getMessage()
            );
        }


        // --------------------------------------------------------
        // Java 9 improvement: completedStage()
        // --------------------------------------------------------

        /*
         * completedStage() returns a CompletionStage that is
         * already completed with the given value.
         *
         * CompletionStage exposes stage-based composition without
         * exposing the full CompletableFuture API.
         */

        String stageResult =
                CompletableFuture.completedStage("Completed")
                        .toCompletableFuture()
                        .get();

        System.out.println(
                "Completed stage result: " + stageResult
        );
    }

    /*
     * Simulated external API calls.
     *
     * In a real application these might call a remote service,
     * a database, or another backend system.
     */

    private static String fetchCustomer() {
        return "Customer: Tejas";
    }

    private static String fetchOrder() {
        return "Order: Java Book";
    }

    /*
     * INTERVIEW ANSWER:
     *
     * "CompletableFuture supports asynchronous computation and
     * composition of results. Java 9 added methods such as
     * orTimeout(), completeOnTimeout(), failedFuture() and
     * completedStage(). These help implement timeouts, fallback
     * results, error flows and asynchronous API orchestration."
     *
     * REAL-WORLD NOTE:
     *
     * For blocking network calls, consider using a dedicated
     * Executor rather than exhausting the common ForkJoinPool.
     * For production HTTP calls, also configure connection and
     * request timeouts in the HTTP client itself.
     */


    // ============================================================
    // 3. JPMS: JAVA PLATFORM MODULE SYSTEM
    // ============================================================

    /*
     * JPMS was introduced in Java 9.
     *
     * Before JPMS:
     * Applications commonly used the classpath to locate classes
     * and JAR files.
     *
     * With JPMS:
     * Applications can be organized into named modules.
     * A module explicitly declares:
     *
     * - Which other modules it requires
     * - Which packages it exports
     * - Which packages it opens for reflection
     *
     * A real named module normally needs a separate file:
     *
     * module-info.java
     *
     * Since this demonstration must remain in one Java file,
     * the module descriptor is shown as a comment below.
     */

    private static void jpmsExplanation() {

        System.out.println(
                "JPMS = Java Platform Module System"
        );

        System.out.println(
                "It provides explicit dependencies and " +
                        "stronger encapsulation."
        );

        System.out.println(
                "A real module descriptor is named module-info.java."
        );

        /*
         * EXAMPLE OF A SEPARATE module-info.java FILE:
         *
         * module com.tejas.application {
         *
         *     requires java.sql;
         *
         *     exports com.tejas.java_version_lab.java9;
         *
         * }
         *
         * IMPORTANT:
         *
         * The snippet above is illustrative. It is not Java
         * code to place inside this class.
         *
         * "module" gives the module its name.
         *
         * "requires java.sql" declares a dependency on the
         * java.sql module.
         *
         * "exports" makes a package accessible to other modules.
         *
         * "opens" can permit reflective access to a package,
         * including access needed by certain frameworks.
         *
         * "requires transitive" can expose a dependency to
         * downstream modules.
         *
         * "requires static" declares a dependency needed at
         * compile time but optional at runtime.
         *
         * Common directives:
         *
         * requires
         * exports
         * opens
         * uses
         * provides ... with ...
         */

        /*
         * Example:
         *
         * module com.tejas.application {
         *     requires java.sql;
         *     exports com.tejas.api;
         *     opens com.tejas.model to some.framework;
         * }
         *
         * "exports" and "opens" are different:
         *
         * exports:
         * Makes public types in a package accessible to
         * other modules under normal Java access rules.
         *
         * opens:
         * Allows deep reflection into a package, subject
         * to the module declaration and runtime configuration.
         *
         * JPMS is separate from Spring's dependency injection.
         * Spring Boot applications often run on the classpath.
         * Named modules require additional configuration, and
         * reflective frameworks may need appropriate opens.
         */

        System.out.println(
                "For most Spring Boot applications, " +
                        "understand classpath vs module path first."
        );
    }

    /*
     * INTERVIEW ANSWER:
     *
     * "JPMS was introduced in Java 9. It organizes applications
     * into modules with explicit dependencies and package
     * encapsulation. The module-info.java descriptor uses
     * directives such as requires, exports and opens. Many
     * Spring Boot applications still run on the classpath,
     * but JPMS is useful to understand for modular applications."
     */


    // ============================================================
    // 4. REACTIVE STREAMS / FLOW API
    // ============================================================

    /*
     * Java 9 introduced java.util.concurrent.Flow.
     *
     * The API follows the Reactive Streams model.
     *
     * Its main interfaces are:
     *
     * Flow.Publisher<T>
     *     Produces items.
     *
     * Flow.Subscriber<T>
     *     Receives items.
     *
     * Flow.Subscription
     *     Controls demand and cancellation.
     *
     * Flow.Processor<T, R>
     *     Both a Subscriber and a Publisher.
     *
     * A key concept is backpressure:
     *
     * The subscriber requests how many items it is ready
     * to receive. The publisher should not send more items
     * than the requested demand.
     *
     * SubmissionPublisher is a built-in implementation
     * that supports asynchronous delivery.
     *
     * Spring WebFlux commonly uses Project Reactor's
     * Mono and Flux, rather than Flow types directly.
     */

    private static void flowApiExample()
            throws InterruptedException {

        /*
         * SubmissionPublisher is a Publisher implementation.
         * It uses an executor for asynchronous delivery.
         */

        try (SubmissionPublisher<String> publisher =
                     new SubmissionPublisher<>()) {

            CountDownLatch completionLatch =
                    new CountDownLatch(1);

            /*
             * Subscribe to the publisher.
             *
             * onSubscribe() is called first.
             * The subscriber requests two items.
             */

            publisher.subscribe(
                    new Flow.Subscriber<String>() {

                        private Flow.Subscription subscription;

                        @Override
                        public void onSubscribe(
                                Flow.Subscription subscription) {

                            this.subscription = subscription;

                            System.out.println(
                                    "Subscriber subscribed"
                            );

                            /*
                             * Request two items.
                             * This is the demand signal.
                             */
                            subscription.request(2);
                        }

                        @Override
                        public void onNext(String item) {

                            System.out.println(
                                    "Received: " + item
                            );

                            /*
                             * Requesting another item here
                             * would be appropriate if more
                             * items were expected.
                             *
                             * This example publishes exactly
                             * two items.
                             */
                        }

                        @Override
                        public void onError(Throwable throwable) {

                            System.out.println(
                                    "Subscriber error: " +
                                            throwable.getMessage()
                            );

                            completionLatch.countDown();
                        }

                        @Override
                        public void onComplete() {

                            System.out.println(
                                    "Publisher completed"
                            );

                            completionLatch.countDown();
                        }
                    }
            );

            /*
             * submit() publishes items.
             *
             * The subscriber receives them asynchronously.
             */

            publisher.submit("Java event");
            publisher.submit("Spring Boot event");

            /*
             * close() signals that no more items will be sent.
             * Previously submitted items can still be delivered.
             */
            publisher.close();

            /*
             * Wait for onComplete() or onError().
             * This prevents main() from finishing before the
             * asynchronous subscriber finishes processing.
             */
            boolean completed = completionLatch.await(
                    5,
                    TimeUnit.SECONDS
            );

            if (!completed) {
                System.out.println(
                        "Subscriber did not finish before timeout"
                );
            }
        }
    }

    /*
     * EXPECTED FLOW OUTPUT:
     *
     * Subscriber subscribed
     * Received: Java event
     * Received: Spring Boot event
     * Publisher completed
     *
     * Subscriber callback timing is asynchronous, so output
     * ordering relative to other threads is not guaranteed.
     *
     * INTERVIEW ANSWER:
     *
     * "Java 9 introduced the Flow API, based on the Reactive
     * Streams model. It defines Publisher, Subscriber,
     * Subscription and Processor. Backpressure allows a
     * subscriber to control demand by requesting items.
     * Spring WebFlux commonly uses Reactor's Mono and Flux
     * to build reactive pipelines."
     */
}
