# Spring Boot Learning Topics (Summarized)

## Spring Data

- **Entity lifecycle events** — Spring can automatically run code at key moments (before save, after load, etc.) to decouple persistence concerns (like encryption) from business logic. Similar to what the team already does manually with the DynamoDB SDK.

- **Auditable fields** — Spring auto-populates `createdDate`, `modifiedDate`, `createdBy` on every save/update. The team already does this by hand in DynamoDB — Spring has it built in.

## Spring Kafka

- **Kafka interceptors** — Hooks that run on every message sent/received. Use cases: ensuring headers are present, compressing/decompressing message bodies, adding tracing IDs. Think of them as middleware for Kafka messages.

- **Dead letter topic handling** — When a message fails to process, Spring Kafka can automatically route it to a "dead letter" topic instead of crashing or retrying forever. Configure an error handler and Spring does the rest.

## Generic Spring Boot Concepts

- **Servlet Filters & HTTP interceptors** — Code that runs on every incoming request before it hits the controller (or on every response going out). Common uses: logging, adding headers, auth checks, measuring duration.

- **`@Async` handling** — Annotate a method with `@Async` and Spring runs it in a background thread. Useful for fire-and-forget work like sending notifications.

- **Spring error handlers** — A centralized place to catch exceptions from any controller and return consistent error responses instead of scattering try/catch everywhere.

## Advanced Patterns (from the team's common library)

- **REST client hook points** — Interceptors on outgoing HTTP calls (e.g., auto-attaching auth tokens to every request).

- **Programmatic bean generation at startup** — Dynamically creating Spring-managed objects in code (e.g., generating a configured REST client per downstream service from config) rather than declaring each one manually.

- **Global error handling** — One place that catches all unhandled exceptions across the app.

- **Thread-local data propagation** — Storing per-request context (request ID, user ID) in a thread-local so it's accessible anywhere in the call chain without passing it as a parameter.

