package io.helidon.examples.sockshop.payment.jpa;

import io.helidon.examples.sockshop.payment.PaymentRepository;
import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.microprofile.server.Server;

import org.junit.jupiter.api.AfterAll;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.jpa.JpaPaymentRepository}.
 */
public class JpaPaymentRepositoryIT extends PaymentRepositoryTest {
    /**
     * Starting server on ephemeral port in order to be able to get the
     * fully configured repository from the CDI container.
     */
    private static final Server SERVER = Server.builder().port(0).build().start();

    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @Override
    protected PaymentRepository getPaymentRepository() {
        return SERVER.cdiContainer().select(PaymentRepository.class).get();
    }

    @Override
    protected void clearRepository(PaymentRepository payment) {
        ((JpaPaymentRepository) payment).clear();
    }
}
