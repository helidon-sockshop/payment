package io.helidon.examples.sockshop.payment;

import io.helidon.microprofile.grpc.core.GrpcMarshaller;
import io.helidon.microprofile.grpc.core.RpcService;
import io.helidon.microprofile.grpc.core.Unary;

import java.util.Collection;

@RpcService(name = "PaymentGrpc")
@GrpcMarshaller("jsonb")
public interface PaymentClient {
    @Unary
    Authorization authorize(PaymentRequest request);

    @Unary
    Collection<? extends Authorization> getOrderAuthorizations(String orderId);
}
