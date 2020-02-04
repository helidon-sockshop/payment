package io.helidon.examples.sockshop.payment;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Credit card information.
 */
@Data
@NoArgsConstructor
public class Card implements Serializable {
    /**
     * Credit card number.
     */
    private String longNum;

    /**
     * Expiration date.
     */
    private String expires;

    /**
     * CCV code.
     */
    private String ccv;

    @Builder
    Card(String longNum, String expires, String ccv) {
        this.longNum = longNum;
        this.expires = expires;
        this.ccv = ccv;
    }
}
