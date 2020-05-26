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

package io.helidon.examples.sockshop.payment;

import java.util.Collection;

import javax.validation.constraints.NotNull;

/**
 * A repository interface that should be implemented by
 * the various data store integrations.
 */
public interface PaymentRepository {
    /**
     * Save payment authorization details.
     *
     * @param auth payment authorization details
     */
    void saveAuthorization(Authorization auth);

    /**
     * Find all authorizations for the specified order.
     *
     * @param orderId the order identifier to find the authorizations for
     *
     * @return all authorizations for the specified order; never {@code null}
     */
    @NotNull Collection<? extends Authorization> findAuthorizationsByOrder(String orderId);
}
