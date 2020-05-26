/*
 *  Copyright (c) 2020 Oracle and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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
