package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

public class Authorization implements Serializable {
    private String orderId;
    private boolean authorised = false;
    private String message;
    private Object error;

    // For jackson
    public Authorization() {
    }

    public Authorization(String orderId, boolean authorised, String message, Object error) {
        this.orderId = orderId;
        this.authorised = authorised;
        this.message = message;
        this.error = error;
    }

    @Override
    public String toString() {
        return "Authorization{" +
                "authorised=" + authorised +
                ", message=" + message +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {return error;}
}
