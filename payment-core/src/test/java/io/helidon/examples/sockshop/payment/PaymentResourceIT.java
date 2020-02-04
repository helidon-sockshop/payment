package io.helidon.examples.sockshop.payment;

import java.time.LocalDateTime;

import io.helidon.microprofile.server.Server;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.jboss.weld.proxy.WeldClientProxy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.helidon.examples.sockshop.payment.TestDataFactory.auth;
import static io.helidon.examples.sockshop.payment.TestDataFactory.paymentRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

/**
 * Integration tests for {@link io.helidon.examples.sockshop.payment.PaymentResource}.
 */
public class PaymentResourceIT {
    /**
     * This will start the application on ephemeral port to avoid port conflicts.
     * We can discover the actual port by calling {@link io.helidon.microprofile.server.Server#port()} method afterwards.
     */
    private static final Server SERVER = Server.builder().port(0).build().start();
    private PaymentRepository payments;

    @AfterAll
    static void stopServer() {
        SERVER.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        // Configure RestAssured to run tests against our application
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = SERVER.port();

        payments = SERVER.cdiContainer().select(PaymentRepository.class).get();

        // oh, boy... not pretty, but probably the best we can do
        // without adding clear() to public interface
        WeldClientProxy proxy = (WeldClientProxy) payments;
        Object o = proxy.getMetadata().getContextualInstance();
        o.getClass().getMethod("clear").invoke(o);
    }

    @Test
    void testSuccessfulAuthorization() {
        given().
                body(paymentRequest("A123", 50)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                post("/payments").
        then().
                statusCode(OK.getStatusCode()).
                body("authorised", is(true),
                     "message", is("Payment authorized.")
                );
    }

    @Test
    void testDeclinedAuthorization() {
        given().
                body(paymentRequest("A123", 150)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                post("/payments").
        then().
                statusCode(OK.getStatusCode()).
                body("authorised", is(false),
                     "message", is("Payment declined: amount exceeds 100.00")
                );
    }

    @Test
    void testInvalidPaymentAmount() {
        given().
                body(paymentRequest("A123", -50)).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
        when().
                post("/payments").
        then().
                statusCode(OK.getStatusCode()).
                body("authorised", is(false),
                     "message", is("Invalid payment amount.")
                );
    }

    @Test
    void testFindPaymentsByOrder() {
        LocalDateTime time = LocalDateTime.now();
        payments.saveAuthorization(auth("A123", time, new Err("Payment service unavailable")));
        payments.saveAuthorization(auth("A123", time.plusSeconds(5), false, "Payment declined"));
        payments.saveAuthorization(auth("A123", time.plusSeconds(10), true, "Payment processed"));
        payments.saveAuthorization(auth("B456", time, true, "Payment processed"));

        when().
                get("/payments/{orderId}", "A123").
        then().
                statusCode(OK.getStatusCode()).
                body("$", hasSize(3));

        when().
                get("/payments/{orderId}", "B456").
        then().
                statusCode(OK.getStatusCode()).
                body("$", hasSize(1));

        when().
                get("/payments/{orderId}", "C789").
        then().
                statusCode(OK.getStatusCode()).
                body("$", hasSize(0));
    }
}
