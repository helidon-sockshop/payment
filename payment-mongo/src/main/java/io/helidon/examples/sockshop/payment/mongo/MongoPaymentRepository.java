package io.helidon.examples.sockshop.payment.mongo;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;

import com.mongodb.client.MongoCollection;

/**
 * @author Aleksandar Seovic  2020.01.21
 */
@ApplicationScoped
@Specializes
public class MongoPaymentRepository extends DefaultPaymentRepository {
    @Inject
    private MongoCollection<MongoAuthorization> payments;

    @Override
    public void addAuthorization(Authorization auth) {
        payments.insertOne(new MongoAuthorization(auth));
    }
}
