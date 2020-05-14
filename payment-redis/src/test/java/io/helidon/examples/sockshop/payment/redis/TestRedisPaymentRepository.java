package io.helidon.examples.sockshop.payment.redis;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.AuthorizationId;
import io.helidon.examples.sockshop.payment.TestPaymentRepository;

import org.redisson.api.RMap;

import static javax.interceptor.Interceptor.Priority.APPLICATION;

@Alternative
@Priority(APPLICATION + 5)
public class TestRedisPaymentRepository extends RedisPaymentRepository implements TestPaymentRepository {
    @Inject
    TestRedisPaymentRepository(RMap<AuthorizationId, Authorization> payments) {
        super(payments);
    }

    @Override
    public void clear() {
        payments.clear();
    }
}
