/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

/**
 * Simple in-memory implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that can be used for demos and testing.
 * <p/>
 * This implementation is obviously not suitable for production use, because it is not
 * persistent and it doesn't scale, but it is trivial to write and very useful for the
 * API testing and quick demos.
 */
@ApplicationScoped
@Priority(APPLICATION - 10)
@Traced
public class DefaultPaymentRepository implements PaymentRepository {
    protected Map<AuthorizationId, Authorization> payments;

    /**
     * Construct {@code DefaultPaymentRepository} with an empty storage map.
     */
    public DefaultPaymentRepository() {
        this(new ConcurrentHashMap<>());
    }

    /**
     * Construct {@code DefaultPaymentRepository} with the specified storage map.
     *
     * @param payments the storage map to use
     */
    protected DefaultPaymentRepository(Map<AuthorizationId, Authorization> payments) {
        this.payments = payments;
    }

    @Override
    public void saveAuthorization(Authorization auth) {
        payments.put(auth.getId(), auth);
    }

    @Override
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        return payments.values().stream()
                .filter(authorization -> authorization.getOrderId().equals(orderId))
                .sorted(Comparator.comparing(Authorization::getTime))
                .collect(Collectors.toList());
    }
}
