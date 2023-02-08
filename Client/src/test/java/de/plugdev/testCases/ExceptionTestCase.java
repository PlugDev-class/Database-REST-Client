package de.plugdev.testCases;

/*
 * Generated Class by PlugDev.
 * 08.02.2023 um 12:48.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.ClientInstance;
import de.plugdev.client.services.IExceptionManager;
import de.plugdev.services.ServiceManager;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionTestCase {

    public static void main(String[] args) {
        ClientInstance.preInitMasterBranchSynchronously();

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                Thread.sleep(500);
                System.out.println("Any Testcasing");
                Arrays.stream(((IExceptionManager) ServiceManager.get(IExceptionManager.class))
                        .getExceptions()).forEach(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        String test = null;
        System.out.println(test.charAt(5)); // provoke a NPE || but it should be caught with the exception-manager
    }

}
