package io.helidon.examples.sockshop.payment;

/**
 */
public interface PaymentRepository {
    void addAuthorization(String orderId, Authorization auth);
}
