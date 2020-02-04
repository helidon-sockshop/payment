package io.helidon.examples.sockshop.payment;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;

/**
 * Trivial {@link PaymentService} implementation for demo and testing purposes.
 * <p/>
 * It approves all payment requests with the total amount lower or equal
 * to the {@code payment.limit} configuration property (100 by default),
 * and declines all requests above that amount.
 */
@ApplicationScoped
@Traced
public class DefaultPaymentService implements PaymentService {
    /**
     * Payment limit
     */
    private float paymentLimit;

    /**
     * Construct {@code DefaultPaymentService} instance.
     */
    public DefaultPaymentService() {
    }

    /**
     * Construct {@code DefaultPaymentService} instance.
     *
     * @param paymentLimit payment limit
     */
    @Inject
    public DefaultPaymentService(@ConfigProperty(name = "payment.limit", defaultValue = "100") float paymentLimit) {
        this.paymentLimit = paymentLimit;
    }

    @Override
    public Authorization authorize(String orderId, String firstName, String lastName, Card card, Address address, float amount) {
        boolean fAuthorized = amount > 0 && amount <= paymentLimit;

        String message = fAuthorized ? "Payment authorized." :
                amount <= 0 ? "Invalid payment amount." :
                        "Payment declined: amount exceeds " + String.format("%.2f", paymentLimit);

        return Authorization.builder()
                .orderId(orderId)
                .time(LocalDateTime.now())
                .authorised(fAuthorized)
                .message(message)
                .build();
    }
}
