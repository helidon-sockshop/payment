package io.helidon.examples.sockshop.payment;

/**
 * A service that authorize a payment.
 */
public interface PaymentService {
    Authorization authorize(String orderId, String firstName, String lastName, Card card, Address address, float amount);
}