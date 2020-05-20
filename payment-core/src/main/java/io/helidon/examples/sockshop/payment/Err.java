package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Represents an unexpected error.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Schema(description = "Represents an unexpected error")
public class Err implements Serializable {
    /**
     * Error description.
     */
    @Schema(description = "Error description")
    @Column(name = "error")
    private String message;
}
