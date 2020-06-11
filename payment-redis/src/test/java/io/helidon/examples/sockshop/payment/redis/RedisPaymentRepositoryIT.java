/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.redis;

import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import static io.helidon.examples.sockshop.payment.redis.RedisProducers.client;
import static io.helidon.examples.sockshop.payment.redis.RedisProducers.payments;

/**
 * Tests for Redis repository implementation.
 */
class RedisPaymentRepositoryIT extends PaymentRepositoryTest {
    public TestPaymentRepository getPaymentRepository() {
        String host = System.getProperty("db.host","localhost");
        int    port = Integer.parseInt(System.getProperty("db.port","6379"));

        return new TestRedisPaymentRepository(payments(client(host, port)));
    }
}
