package io.helidon.examples.sockshop.payment;

/**
 * Tests for default in memory repository implementation.
 */
public class DefaultPaymentRepositoryTest extends PaymentRepositoryTest {
    @Override
    protected TestPaymentRepository getPaymentRepository() {
        return new TestDefaultPaymentRepository();
    }
}
