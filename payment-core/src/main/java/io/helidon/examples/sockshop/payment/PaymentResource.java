package io.helidon.examples.sockshop.payment;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Implementation of the Payment Service REST API.
 */
@ApplicationScoped
@Path("/payments")
public class PaymentResource {
    /**
     * Payment repository to use.
     */
    @Inject
    private PaymentRepository payments;

    /**
     * Payment service to use.
     */
    @Inject
    private PaymentService paymentService;

    @GET
    @Path("{orderId}")
    @Produces(APPLICATION_JSON)
    public Response getOrderAuthorizations(@PathParam("orderId") String orderId) {
        return Response.ok(payments.findAuthorizationsByOrder(orderId)).build();
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Authorization authorize(PaymentRequest paymentRequest) {

        String firstName = paymentRequest.getCustomer().getFirstName();
        String lastName  = paymentRequest.getCustomer().getLastName();

        Authorization auth = paymentService.authorize(
                paymentRequest.getOrderId(),
                firstName,
                lastName,
                paymentRequest.getCard(),
                paymentRequest.getAddress(),
                paymentRequest.getAmount());

        payments.saveAuthorization(auth);

        return auth;
    }


}
