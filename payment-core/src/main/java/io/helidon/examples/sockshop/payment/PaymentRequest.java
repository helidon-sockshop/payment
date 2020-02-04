package io.helidon.examples.sockshop.payment;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Payment request that is received from Orders service for authorization.
 */
@Data
@NoArgsConstructor
public class PaymentRequest {
    /**
     * Order identifier.
     */
    private String orderId;

    /**
     * Customer information.
     */
    private Customer customer;

    /**
     * Billing address.
     */
    private Address address;

    /**
     * Payment card details.
     */
    private Card card;

    /**
     * Payment amount.
     */
    private float amount;

    @Builder
    PaymentRequest(String orderId, Customer customer, Address address, Card card, float amount) {
        this.orderId = orderId;
        this.customer = customer;
        this.address = address;
        this.card = card;
        this.amount = amount;
    }
}
