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

import org.junit.jupiter.api.Test;

import static io.helidon.examples.sockshop.payment.TestDataFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * Unit tests for {@link io.helidon.examples.sockshop.payment.DefaultPaymentService}.
 */
public class DefaultPaymentServiceTest {
    @Test
    void testSuccessfulAuthorization() {
        PaymentService service = new DefaultPaymentService(100);
        Authorization auth = service.authorize("A123", "Homer", "Simpson", card(), address(), 50);

        assertThat(auth.getOrderId(), is("A123"));
        assertThat(auth.getTime(), lessThanOrEqualTo(LocalDateTime.now()));
        assertThat(auth.isAuthorised(), is(true));
        assertThat(auth.getMessage(), is("Payment authorized."));
        assertThat(auth.getError(), nullValue());
    }

    @Test
    void testDeclinedAuthorization() {
        PaymentService service = new DefaultPaymentService(100);
        Authorization auth = service.authorize("A123", "Homer", "Simpson", card(), address(), 150);

        assertThat(auth.getOrderId(), is("A123"));
        assertThat(auth.getTime(), lessThanOrEqualTo(LocalDateTime.now()));
        assertThat(auth.isAuthorised(), is(false));
        assertThat(auth.getMessage(), is("Payment declined: amount exceeds 100.00"));
        assertThat(auth.getError(), nullValue());
    }

    @Test
    void testInvalidAmount() {
        PaymentService service = new DefaultPaymentService(100);
        Authorization auth = service.authorize("A123", "Homer", "Simpson", card(), address(), -25);

        assertThat(auth.getOrderId(), is("A123"));
        assertThat(auth.getTime(), lessThanOrEqualTo(LocalDateTime.now()));
        assertThat(auth.isAuthorised(), is(false));
        assertThat(auth.getMessage(), is("Invalid payment amount."));
        assertThat(auth.getError(), nullValue());
    }
}
