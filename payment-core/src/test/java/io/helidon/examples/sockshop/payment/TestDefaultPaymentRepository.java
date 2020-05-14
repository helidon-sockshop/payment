package io.helidon.examples.sockshop.payment;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Alternative
@Priority(APPLICATION - 5)
public class TestDefaultPaymentRepository extends DefaultPaymentRepository implements TestPaymentRepository {
    @Override
    public void clear() {
        payments.clear();
    }
}
