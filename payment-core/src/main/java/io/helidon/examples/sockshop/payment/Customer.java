package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer information.
 */
@Data
@NoArgsConstructor
public class Customer implements Serializable {
    /**
     * First name.
     */
    private String firstName;

    /**
     * Last name.
     */
    private String lastName;

    @Builder
    Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
