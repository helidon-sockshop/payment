package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

public class Authorization implements Serializable {
    private boolean authorised = false;
    private String message;
    private Object error;

    // For jackson
    public Authorization() {
    }

    public Authorization(boolean authorised, String message, Object error) {
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

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getError() {return error;}
}
