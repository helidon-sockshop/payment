/*
 *  Copyright (c) 2020 Oracle and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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
