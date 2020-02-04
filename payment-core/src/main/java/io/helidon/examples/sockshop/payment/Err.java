package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

import javax.persistence.Column;
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
public class Err implements Serializable {
    /**
     * Error description.
     */
    @Column(name = "error")
    private String message;
}
