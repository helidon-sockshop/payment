/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.jpa;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;

import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Alternative
@Priority(APPLICATION + 5)
public class TestJpaPaymentRepository extends JpaPaymentRepository implements TestPaymentRepository {
    @Override
    @Transactional
    public void clear() {
        em.createQuery("delete from Authorization").executeUpdate();
    }
}
