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

/**
 * A service that authorizes a payment request.
 * <p/>
 * This would be an integration point for external payment processors,
 * such as Stripe, etc.
 */
public interface PaymentService {
    /**
     * Authorize specified payment amount.
     * 
     * @param orderId   order identifier
     * @param firstName customer's first name
     * @param lastName  customer's last name
     * @param card      credit card to use
     * @param address   billing address
     * @param amount    the payment amount to authorize
     *
     * @return authorization details
     */
    Authorization authorize(String orderId, String firstName, String lastName, Card card, Address address, float amount);
}