package io.helidon.examples.sockshop.payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

/**
 */
@ApplicationScoped
public class DefaultPaymentRepository implements PaymentRepository {
    private Map<String, Authorization> payments = new ConcurrentHashMap<>();

    @Override
    public void addAuthorization(Authorization auth) {
        payments.put(auth.getOrderId(), auth);
    }
}
