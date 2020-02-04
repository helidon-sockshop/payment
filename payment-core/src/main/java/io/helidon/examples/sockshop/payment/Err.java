package io.helidon.examples.sockshop.payment;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an unexpected error.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Err {
    /**
     * Error description.
     */
    private String message;
}
