package io.helidon.examples.sockshop.payment;

/**
 * Tests for default in memory repository implementation.
 */
public class DefaultPaymentRepositoryTest extends PaymentRepositoryTest {
    @Override
    protected PaymentRepository getPaymentRepository() {
        return new DefaultPaymentRepository();
    }

    @Override
    protected void clearRepository(PaymentRepository repository) {
        ((DefaultPaymentRepository) repository).clear();
    }
}
