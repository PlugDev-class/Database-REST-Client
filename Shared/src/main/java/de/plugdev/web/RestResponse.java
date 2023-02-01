package de.plugdev.web;
/*
 * Generated Class by PlugDev.
 * 25.01.2023 um 18:08.
 *
 * Alle Rechte vorbehalten. @2023
 */

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@ToString
@Data
public class RestResponse {

    private final UUID uniqueId;

    private final ResponseCode responseReturn;
    private final short responseCode;

    private final String responseBody;

}
