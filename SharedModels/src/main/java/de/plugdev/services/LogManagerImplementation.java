package de.plugdev.services;

/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 18:47.
 *
 * Alle Rechte vorbehalten. @2023
 */

import javax.print.attribute.standard.Severity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogManagerImplementation implements ILog {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public void log(String message) {
        log(message, Severity.REPORT);
    }

    @Override
    public void log(String message, Severity severity) {
        System.out.println(simpleDateFormat.format(new Date()) + " | " + severity.toString() + " - " + message);
    }

    @Override // niu (not in use)
    public void enable() {}

    @Override // niu (not in use)
    public void disable() {}
}
