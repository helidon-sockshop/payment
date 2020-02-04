package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Shipping or billing address.
 */
@Data
@NoArgsConstructor
public class Address implements Serializable {
    /**
     * Street number.
     */
    private String number;

    /**
     * Street name.
     */
    private String street;

    /**
     * City name.
     */
    private String city;

    /**
     * Postal code.
     */
    private String postcode;

    /**
     * Country name.
     */
    private String country;

    @Builder
    Address(String number, String street, String city, String postcode, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
    }
}
