/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.coherence;

import com.oracle.coherence.cdi.Name;
import com.tangosol.net.NamedMap;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.AuthorizationId;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Alternative
@Priority(APPLICATION + 5)
public class TestCoherencePaymentRepository extends CoherencePaymentRepository implements TestPaymentRepository {
    @Inject
    TestCoherencePaymentRepository(@Name("payments") NamedMap<AuthorizationId, Authorization> payments) {
        super(payments);
    }

    @Override
    public void clear() {
        payments.clear();
    }
}
