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

/**
 * Payment authorization to send back to the Order service.
 */
@Data
@NoArgsConstructor
@Entity
@IdClass(AuthorizationId.class)
public class Authorization implements Serializable {
    /**
     * Order identifier.
     */
    @Id
    private String orderId;

    /**
     * Time when this payment authorization was created.
     */
    @Id
    private LocalDateTime time;

    /**
     * Flag specifying whether the payment was authorized.
     */
    private boolean authorised;

    /**
     * Approval or rejection message.
     */
    private String  message;

    /**
     * Processing error, if any.
     */
    @Embedded
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
