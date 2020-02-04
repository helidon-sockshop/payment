package io.helidon.examples.sockshop.payment.mongo;

import io.helidon.examples.sockshop.payment.PaymentRepository;
import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;

import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.client;
import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.db;
import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.payments;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.mongo.MongoPaymentRepository}.
 */
class MongoPaymentRepositoryIT extends PaymentRepositoryTest {
    public PaymentRepository getPaymentRepository() {
        String host = System.getProperty("db.host","localhost");
        int    port = Integer.parseInt(System.getProperty("db.port","27017"));

        return new MongoPaymentRepository(payments(db(client(host, port))));
    }

    @Override
    protected void clearRepository(PaymentRepository payment) {
        ((MongoPaymentRepository) payment).clear();
    }
}
