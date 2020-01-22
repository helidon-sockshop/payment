package io.helidon.examples.sockshop.payment.mongo;

import io.helidon.examples.sockshop.payment.Authorization;

import org.bson.types.ObjectId;

/**
 * @author Aleksandar Seovic  2020.01.22
 */
public class MongoAuthorization extends Authorization {
    public ObjectId id;

    public MongoAuthorization() {
    }

    public MongoAuthorization(Authorization auth) {
        super(auth.getOrderId(), auth.isAuthorised(), auth.getMessage(), auth.getError());
    }
}
