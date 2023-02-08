package de.plugdev.models;

/*
 * Generated Class by PlugDev.
 * 01.02.2023 um 09:02.
 *
 * Alle Rechte vorbehalten. @2023
 */

import lombok.Data;

import java.security.cert.Certificate;
import java.util.UUID;

@Data
public class Credentials {

    private final UUID uniqueId;
    private final Certificate certificate;

    private final String username;
    private final String encryptedPassword;

}
