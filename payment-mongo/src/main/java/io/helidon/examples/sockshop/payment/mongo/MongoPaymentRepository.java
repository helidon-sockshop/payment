package io.helidon.examples.sockshop.payment.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;

import io.helidon.examples.sockshop.payment.Authorization;
import io.helidon.examples.sockshop.payment.DefaultPaymentRepository;

import com.mongodb.client.MongoCollection;
import org.bson.BsonDocument;

import static com.mongodb.client.model.Filters.eq;

/**
 * An implementation of {@link io.helidon.examples.sockshop.payment.PaymentRepository}
 * that that uses MongoDB as a backend data store.
 */
@ApplicationScoped
@Specializes
public class MongoPaymentRepository extends DefaultPaymentRepository {
    /**
     * Mongo collection used to store payment authorizations.
     */
    private MongoCollection<Authorization> payments;

    /**
     * Construct {@code MongoPaymentRepository} instance.
     *
     * @param payments Mongo collection used to store payment authorizations
     */
    @Inject
    MongoPaymentRepository(MongoCollection<Authorization> payments) {
        this.payments = payments;
    }

    @Override
    public void saveAuthorization(Authorization auth) {
        payments.insertOne(auth);
    }

    @Override
    public Collection<? extends Authorization> findAuthorizationsByOrder(String orderId) {
        ArrayList<Authorization> results = new ArrayList<>();

        payments.find(eq("orderId", orderId))
                .forEach((Consumer<? super Authorization>) results::add);

        return results;
    }

    // ---- helpers ---------------------------------------------------------

    @Override
    public void clear() {
        payments.deleteMany(new BsonDocument());
    }

}
