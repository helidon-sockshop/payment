package io.helidon.examples.sockshop.payment.redis;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.AuthorizationId;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;
import io.helidon.examples.sockshop.payment.Authorization;

import org.redisson.api.RMap;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses Redis (via Redisson) as a backend data store.
 */
@ApplicationScoped
@Specializes
public class RedisPaymentRepository extends DefaultPaymentRepository {
    @Inject
    public RedisPaymentRepository(RMap<AuthorizationId, Authorization> payments) {
        super(payments);
    }
}
