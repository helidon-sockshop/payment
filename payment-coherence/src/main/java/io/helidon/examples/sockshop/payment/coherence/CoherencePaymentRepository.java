/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.coherence;

import com.oracle.coherence.inject.Name;
import com.tangosol.net.NamedMap;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.AuthorizationId;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import java.util.Collection;

import static com.tangosol.util.Filters.equal;
import static javax.interceptor.Interceptor.Priority.APPLICATION;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses Coherence as a backend data store.
 */
@ApplicationScoped
@Alternative
@Priority(APPLICATION)
@Traced
public class CoherencePaymentRepository extends DefaultPaymentRepository {
    protected final NamedMap<AuthorizationId, Authorization> payments;

    @Inject
    CoherencePaymentRepository(@Name("payments") NamedMap<AuthorizationId, Authorization> payments) {
        super(payments);
        this.payments = payments;
    }

    @PostConstruct
    void createIndexes() {
        payments.addIndex(Authorization::getOrderId, false, null);
    }

    @Override
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        return payments.values(equal(Authorization::getOrderId, orderId));
    }
}
