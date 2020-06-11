/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.redis;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.AuthorizationId;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;
import io.helidon.examples.sockshop.payment.Authorization;

import org.eclipse.microprofile.opentracing.Traced;
import org.redisson.api.RMap;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses Redis (via Redisson) as a backend data store.
 */
@ApplicationScoped
@Alternative
@Priority(APPLICATION)
@Traced
public class RedisPaymentRepository extends DefaultPaymentRepository {
    @Inject
    public RedisPaymentRepository(RMap<AuthorizationId, Authorization> payments) {
        super(payments);
    }
}
