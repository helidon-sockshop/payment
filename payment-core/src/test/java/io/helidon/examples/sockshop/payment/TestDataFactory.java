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

import java.time.LocalDateTime;

/**
 * Helper methods to create test data.
 */
class TestDataFactory {

    static PaymentRequest paymentRequest(String orderId, float amount) {
        return PaymentRequest.builder()
                .orderId(orderId)
                .customer(customer())
                .address(address())
                .card(card())
                .amount(amount)
                .build();
    }

    static Customer customer() {
        return Customer.builder()
                .firstName("Homer")
                .lastName("Simpson")
                .build();
    }

    static Address address() {
        return Address.builder()
                .number("123")
                .street("Main St")
                .city("Springfield")
                .postcode("55555")
                .country("USA")
                .build();
    }

    static Card card() {
        return Card.builder()
                .longNum("************1234")
                .expires("10/2025")
                .ccv("789")
                .build();
    }

    static Authorization auth(String orderId, LocalDateTime time, boolean fAuthorized, String message) {
        return Authorization.builder()
                .orderId(orderId)
                .time(time)
                .authorised(fAuthorized)
                .message(message)
                .build();
    }

    static Authorization auth(String orderId, LocalDateTime time, Err error) {
        return Authorization.builder()
                .orderId(orderId)
                .time(time)
                .authorised(false)
                .error(error)
                .build();
    }
}
