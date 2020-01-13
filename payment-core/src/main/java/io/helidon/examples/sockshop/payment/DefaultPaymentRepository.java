package io.helidon.examples.sockshop.payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 */
@ApplicationScoped
@Alternative
public class DefaultPaymentRepository implements PaymentRepository {
    private Map<String, Authorization> payments = new ConcurrentHashMap<>();

    @Override
    public void addAuthorization(String orderId, Authorization auth) {
        payments.put(orderId, auth);
    }
}
