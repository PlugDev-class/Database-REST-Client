package de.plugdev.testCases;

/*
 * Generated Class by PlugDev.
 * 31.01.2023 um 12:41.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.LibraryManager;
import de.plugdev.client.services.IRestManager;
import de.plugdev.services.ServiceManager;
import de.plugdev.web.RestResponse;

public class RESTfulTestCase {

    public static void main(String[] args) {

        // Pre init master by method is way more secure than starting by executing the default main method.
        // The main method will be deprecated later.
        LibraryManager.preInitMasterBranchSynchronously();

        // Preset the master REST-Server with valid authentication.
        final IRestManager restManager = ServiceManager.get(IRestManager.class);
        restManager.setDefaultHost("https://api.coindesk.com/");
        restManager.setRestVersion((byte) 2);
        restManager.setDefaultWebKey("");

        // Request two different endpoints
        final RestResponse firstResponse = restManager.get("bpi/currentprice.json");
        final RestResponse secondResponse = restManager.get("bpi/imAWeirdo.json");

        // Displaying the response in the console
        System.out.println(firstResponse); // 200 OK
        System.out.println(secondResponse); // 404 Not Found WITHOUT AN EXCEPTION!

    }

}
