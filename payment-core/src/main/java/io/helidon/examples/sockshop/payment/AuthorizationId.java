package io.helidon.examples.sockshop.payment;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Composite JPA key for {@link Authorization} class.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Composite JPA key for Authorization class")
public class AuthorizationId implements Serializable {
    /**
     * Order identifier.
     */
    @Schema(description = "Order identifier")
    private String orderId;

    /**
     * Time when this payment authorization was created.
     */
    @Schema(description = "Time when this payment authorization was created")
    private LocalDateTime time;
}
