/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment.mongo;

import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.connection.ClusterDescription;
import com.mongodb.connection.ServerDescription;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

/**
 * MongoDB health check.
 */
@Readiness
@ApplicationScoped
public class MongoHealthCheck implements HealthCheck {

    @Inject
    private MongoClient client;

    @Override
    public HealthCheckResponse call() {
        try {
            ClusterDescription desc = client.getClusterDescription();
            ServerDescription server = desc.getServerDescriptions().get(0);
            return HealthCheckResponse.named("db")
                    .state(server.isOk())
                    .withData("server", "MongoDB")
                    .withData("type", desc.getType().name())
                    .withData("version", server.getVersion().getVersionList().stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(".")))
                    .build();
        } catch (Throwable t) {
            return HealthCheckResponse.named("db")
                    .down()
                    .withData("error", t.getMessage())
                    .build();
        }
    }
}
