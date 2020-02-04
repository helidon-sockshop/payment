package io.helidon.examples.sockshop.payment.redis;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import org.redisson.api.Node;
import org.redisson.api.RedissonClient;
import org.redisson.connection.ConnectionListener;

/**
 * Redis health check.
 */
@Readiness
@ApplicationScoped
public class RedisHealthCheck implements HealthCheck, ConnectionListener {

    @Inject
    private RedissonClient client;

    private AtomicBoolean fConnected;
    private volatile int listenerId;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public HealthCheckResponse call() {
        try {
            if (listenerId == 0) {
                listenerId = client.getNodesGroup().addConnectionListener(this);
                fConnected = new AtomicBoolean(client.getNodesGroup().pingAll());
            }

            if (fConnected.get()) {
                Node server = client.getNodesGroup().getNodes().stream().findFirst().get();
                Map<String, String> info = server.info(Node.InfoSection.SERVER);
                return HealthCheckResponse.named("db")
                        .state(server.ping())
                        .withData("server", "Redis")
                        .withData("address", server.getAddr().toString())
                        .withData("version", info.get("redis_version"))
                        .withData("mode", info.get("redis_mode"))
                        .build();
            }
            else {
                return HealthCheckResponse.named("db")
                        .down()
                        .withData("error", "Unable to connect to Redis server")
                        .build();
            }
        }
        catch (Throwable t) {
            fConnected = new AtomicBoolean(false);
            return HealthCheckResponse.named("db")
                    .down()
                    .withData("error", t.getMessage())
                    .build();
        }
    }

    @Override
    public void onConnect(InetSocketAddress addr) {
        fConnected.set(true);
    }

    @Override
    public void onDisconnect(InetSocketAddress addr) {
        fConnected.set(false);
    }
}
