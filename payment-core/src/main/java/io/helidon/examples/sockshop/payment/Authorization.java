/*
 * Copyright (c) 2020 Oracle and/or its affiliates.
 *
 * Licensed under the Universal Permissive License v 1.0 as shown at
 * http://oss.oracle.com/licenses/upl.
 */

package io.helidon.examples.sockshop.payment;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Payment authorization to send back to the Order service.
 */
@Data
@NoArgsConstructor
@Entity
@IdClass(AuthorizationId.class)
@Schema(description = "Payment authorization to send back to the Order service")
public class Authorization implements Serializable {
    /**
     * Order identifier.
     */
    @Id
    @Schema(description = "Order identifier")
    private String orderId;

    /**
     * Time when this payment authorization was created.
     */
    @Id
    @Schema(description = "Time when this payment authorization was created")
    private LocalDateTime time;

    /**
     * Flag specifying whether the payment was authorized.
     */
    @Schema(description = "Flag specifying whether the payment was authorized")
    private boolean authorised;

    /**
     * Approval or rejection message.
     */
    @Schema(description = "Approval or rejection message")
    private String  message;

    /**
     * Processing error, if any.
     */
    @Embedded
    @Schema(description = "Processing error, if any")
    private Err error;

    @Builder
    Authorization(String orderId, LocalDateTime time, boolean authorised, String message, Err error) {
        this.orderId = orderId;
        this.time = time;
        this.authorised = authorised;
        this.message = message;
        this.error = error;
    }

    @JsonbTransient
    @BsonIgnore
    public AuthorizationId getId() {
        return new AuthorizationId(orderId, time);
    }
}
