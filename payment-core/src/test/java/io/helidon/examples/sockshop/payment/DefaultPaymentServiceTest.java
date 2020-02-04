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
