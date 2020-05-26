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

package io.helidon.examples.sockshop.payment.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.PaymentRepository;

import com.mongodb.client.MongoCollection;

import org.eclipse.microprofile.opentracing.Traced;

import static com.mongodb.client.model.Filters.eq;
import static javax.interceptor.Interceptor.Priority.APPLICATION;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses MongoDB as a backend data store.
 */
@ApplicationScoped
@Alternative
@Priority(APPLICATION)
@Traced
public class MongoPaymentRepository implements PaymentRepository {
    /**
     * Mongo collection used to store payment authorizations.
     */
    protected MongoCollection<Authorization> payments;

    /**
     * Construct {@code MongoPaymentRepository} instance.
     *
     * @param payments Mongo collection used to store payment authorizations
     */
    @Inject
    MongoPaymentRepository(MongoCollection<Authorization> payments) {
        this.payments = payments;
    }

    @Override
    public void saveAuthorization(Authorization auth) {
        payments.insertOne(auth);
    }

    @Override
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        ArrayList<Authorization> results = new ArrayList<>();

        payments.find(eq("orderId", orderId))
                .forEach((Consumer<? super Authorization>) results::add);

        return results;
    }
}
