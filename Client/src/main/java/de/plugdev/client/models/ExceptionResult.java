package de.plugdev.client.models;

/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 19:33.
 *
 * Alle Rechte vorbehalten. @2023
 */

import lombok.Data;

import java.util.UUID;

@Data
public class ExceptionResult {

    private final UUID uniqueId;
    private final String exceptionCause;
    private final Exception exception;
    private final ExceptionSevernis exceptionSevernis;

}
