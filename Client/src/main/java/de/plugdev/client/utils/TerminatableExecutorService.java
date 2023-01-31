package de.plugdev.client.utils;/*
 * Generated Class by PlugDev.
 * 24.01.2023 um 19:12.
 *
 * Alle Rechte vorbehalten. @2023
 */

import java.util.concurrent.*;

public class TerminatableExecutorService {

    private final ExecutorService executorService = new ThreadPoolExecutor(2, 2,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<Runnable>(2*12));

    public void addToExecutorService() {

    }

}
