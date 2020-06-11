/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.coherence;

import io.helidon.examples.sockshop.payment.PaymentRepositoryTest;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import com.tangosol.net.CacheFactory;

/**
 * Tests for Coherence repository implementation.
 */
class CoherencePaymentRepositoryIT extends PaymentRepositoryTest {
    public TestPaymentRepository getPaymentRepository() {
        return new TestCoherencePaymentRepository(CacheFactory.getCache("payments"));
    }
}
