/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.mongo;

import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.client;
import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.db;
import static io.helidon.examples.sockshop.payment.mongo.MongoProducers.payments;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.mongo.MongoPaymentRepository}.
 */
class MongoPaymentRepositoryIT extends PaymentRepositoryTest {
    public TestPaymentRepository getPaymentRepository() {
        String host = System.getProperty("db.host","localhost");
        int    port = Integer.parseInt(System.getProperty("db.port","27017"));

        return new TestMongoPaymentRepository(payments(db(client(host, port))));
    }
}
