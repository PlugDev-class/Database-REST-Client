package de.plugdev.testCases;/*
 * Generated Class by PlugDev.
 * 01.02.2023 um 08:51.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.ClientInstance;
import de.plugdev.client.services.IRestManager;
import de.plugdev.services.ServiceManager;
import de.plugdev.web.RestResponse;

public class PingClientToServerRequestTestCase1 {

    public static void main(String[] args) {
        // Start the master/dev instance
        ClientInstance.preInitMasterBranchSynchronously();

        // Building the rest manager
        final IRestManager restManager = ServiceManager.get(IRestManager.class);
        restManager.setDefaultHost("http://localhost:8080");
        restManager.setRestVersion((byte) 1);
        restManager.setDefaultWebKey("");

        /**
         * Receive an answer with a GET-request.
         *
         * @see de.plugdev.restserver.controller.PingController
         */
        System.out.println(restManager.get("api/pingController/help")); // Success 200 OK Response
    }

}
