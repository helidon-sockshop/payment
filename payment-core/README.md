# payment-core

This module contains the bulk of the Payment service implementation, including the 
[domain model](./src/main/java/io/helidon/examples/sockshop/payment/Authorization.java), 
[REST API](./src/main/java/io/helidon/examples/sockshop/payment/PaymentResource.java), as well as the
[data repository abstraction](./src/main/java/io/helidon/examples/sockshop/payment/PaymentRepository.java) 
and its [in-memory implementation](./src/main/java/io/helidon/examples/sockshop/payment/DefaultPaymentRepository.java).

## Building the Service

See [main documentation page](../README.md#building-the-service) for instructions.

## Running the Service

Because this implementation uses in-memory data store, it is trivial to run.

Once you've built the Docker image per instructions above, you can simply run it by executing:

```bash
$ docker run -p 7001:7001 helidon/sockshop/payment-core
``` 

Once the container is up and running, you should be able to access [service API](../README.md#api) 
by navigating to http://localhost:7001/payment/.

As a basic test, you should be able to perform an HTTP GET against `/health` endpoint:

```bash
$ curl -i http://localhost:7001/health
``` 
which should return HTTP status code 200 and a JSON response with health check details.

## License

The Universal Permissive License (UPL), Version 1.0
