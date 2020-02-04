package io.helidon.examples.sockshop.payment;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.helidon.examples.sockshop.payment.TestDataFactory.auth;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * Abstract base class containing tests for all
 * {@link io.helidon.examples.sockshop.payment.PaymentRepository} implementations.
 */
public abstract class PaymentRepositoryTest {

    private PaymentRepository payments = getPaymentRepository();

    protected abstract PaymentRepository getPaymentRepository();
    protected abstract void clearRepository(PaymentRepository payments);

    @BeforeEach
    void setup() {
        clearRepository(payments);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    void testSaveAuthorization() {
        LocalDateTime time = LocalDateTime.now();
        payments.saveAuthorization(auth("A123", time,true, "Payment processed"));

        Collection<? extends Authorization> auths = payments.findAuthorizationsByOrder("A123");
        assertThat(auths.size(), is(1));

        Authorization auth = auths.stream().findFirst().get();
        assertThat(auth.getOrderId(), is("A123"));
        assertThat(auth.getTime(), is(time));
        assertThat(auth.isAuthorised(), is(true));
        assertThat(auth.getMessage(), is("Payment processed"));
        assertThat(auth.getError(), nullValue());
    }


    @Test
    void testFindAuthorizationsByOrder() {
        LocalDateTime time = LocalDateTime.now();
        payments.saveAuthorization(auth("A123", time, new Err("Payment service unavailable")));
        payments.saveAuthorization(auth("A123", time.plusSeconds(5), false, "Payment declined"));
        payments.saveAuthorization(auth("A123", time.plusSeconds(10), true, "Payment processed"));
        payments.saveAuthorization(auth("B456", time, true, "Payment processed"));

        assertThat(payments.findAuthorizationsByOrder("A123").size(), is(3));
        assertThat(payments.findAuthorizationsByOrder("B456").size(), is(1));
        assertThat(payments.findAuthorizationsByOrder("C789").size(), is(0));
    }
}
