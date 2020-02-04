package io.helidon.examples.sockshop.payment.redis;

import io.helidon.examples.sockshop.payment.PaymentRepository;
import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;

import static io.helidon.examples.sockshop.payment.redis.RedisProducers.client;
import static io.helidon.examples.sockshop.payment.redis.RedisProducers.payments;

/**
 * Tests for Redis repository implementation.
 */
class RedisPaymentRepositoryIT extends PaymentRepositoryTest {
    public PaymentRepository getPaymentRepository() {
        String host = System.getProperty("db.host","localhost");
        int    port = Integer.parseInt(System.getProperty("db.port","6379"));

        return new RedisPaymentRepository(payments(client(host, port)));
    }

    @Override
    protected void clearRepository(PaymentRepository payment) {
        ((RedisPaymentRepository) payment).clear();
    }
}
