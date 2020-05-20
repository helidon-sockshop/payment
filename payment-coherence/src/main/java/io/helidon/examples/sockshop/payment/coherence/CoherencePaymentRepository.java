/*
 *  Copyright (c) 2020 Oracle and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.helidon.examples.sockshop.payment.coherence;

import com.tangosol.util.Filters;
import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.AuthorizationId;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;
import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import javax.inject.Inject;

import com.oracle.coherence.cdi.Cache;
import com.tangosol.net.NamedCache;
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
    protected final NamedCache<AuthorizationId, Authorization> payments;

    @Inject
    CoherencePaymentRepository(@Cache("payments") NamedCache<AuthorizationId, Authorization> payments) {
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