package de.plugdev.testCases;

/*
 * Generated Class by PlugDev.
 * 30.01.2023 um 18:51.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.ClientInstance;
import de.plugdev.client.services.ISecurityManager;
import de.plugdev.services.ServiceManager;

public class SecurityTest1 {

    public static void main(String[] args) {
        /*
         * Init default and master instance on this test case.
         * Never use the default main method to enable/start the project, because there are
         * some unpredictable bugs cursing around this
         */

        ClientInstance.preInitMasterBranchSynchronously();


        long start = System.currentTimeMillis();
        String input = "Hello!";
        final byte[] encode = ((ISecurityManager) ServiceManager.get(ISecurityManager.class)).encode(input);
        System.out.println(new String(encode));

        final String endProduct = ((ISecurityManager) ServiceManager.get(ISecurityManager.class)).decode(String.class, encode);
        System.out.println(endProduct);
        long end = System.currentTimeMillis();

        System.out.println("It took " + (end-start) + " milliseconds to encrypt and decrypt \"" + input + "\"");
        System.out.println(input + " and " + endProduct + " are" + (input.equals(endProduct) ? "" : "n't") + " the same");

    }

}
