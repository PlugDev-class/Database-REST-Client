package de.plugdev.client.services.implementations;
/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 19:32.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.models.ExceptionSevernis;
import de.plugdev.client.models.ExceptionResult;
import de.plugdev.client.services.IExceptionManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ExceptionManagerImplementation implements Thread.UncaughtExceptionHandler, IExceptionManager {

    private final List<ExceptionResult> exceptions = new LinkedList<>();

    @Override
    public void addException(Exception exception, ExceptionSevernis exceptionSevernis) {
        exceptions.add(new ExceptionResult(UUID.randomUUID(), exception.getCause().getMessage(),
                exception, exceptionSevernis));
    }

    @Override
    public Exception[] getExceptions() {
        final List<Exception> list = new ArrayList<>();
        exceptions.forEach((exceptionResult) -> list.add(exceptionResult.getException()));
        return list.toArray(new Exception[0]);
    }

    @Override
    public void enable() {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // niu (= not in use)
    public void disable() {}

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        addException((Exception) e, ExceptionSevernis.MEDIUM);
    }
}
