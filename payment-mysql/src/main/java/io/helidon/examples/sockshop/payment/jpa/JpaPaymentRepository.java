/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.jpa;

import java.util.Collection;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.PaymentRepository;

import org.eclipse.microprofile.opentracing.Traced;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses relational database (via JPA) as a backend data store.
 */
@ApplicationScoped
@Alternative
@Priority(APPLICATION)
@Traced
public class JpaPaymentRepository implements PaymentRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public void saveAuthorization(Authorization authorization) {
        em.persist(authorization);
    }

    @Override
    @Transactional
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        String jql = "select a from Authorization as a where a.orderId = :orderId";
        TypedQuery<Authorization> query = em.createQuery(jql, Authorization.class);
        query.setParameter("orderId", orderId);

        return query.getResultList();
    }
}
