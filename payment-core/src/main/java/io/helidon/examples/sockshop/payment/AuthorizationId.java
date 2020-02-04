package io.helidon.examples.sockshop.payment;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Composite JPA key for {@link Authorization} class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationId implements Serializable {
    /**
     * Order identifier.
     */
    private String orderId;

    /**
     * Time when this payment authorization was created.
     */
    private LocalDateTime time;
}
