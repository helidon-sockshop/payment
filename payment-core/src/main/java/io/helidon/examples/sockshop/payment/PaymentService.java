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