package io.helidon.examples.sockshop.payment.jpa;

import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;
import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.jpa.JpaPaymentRepository}.
 */
public class JpaPaymentRepositoryIT extends PaymentRepositoryTest {
    protected static Server SERVER;

    /**
     * This will start the application on ephemeral port to avoid port conflicts.
     * We can discover the actual port by calling {@link io.helidon.microprofile.server.Server#port()} method afterwards.
     */
    @BeforeAll
    static void startServer() {
        // disable global tracing so we can start server in multiple test suites
        System.setProperty("tracing.global", "false");
        SERVER = Server.builder().port(0).build().start();
    }

    /**
     * Stop the server, as we cannot have multiple servers started at the same time.
     */
    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @Override
    protected TestPaymentRepository getPaymentRepository() {
        return SERVER.cdiContainer().select(TestPaymentRepository.class).get();
    }
}
