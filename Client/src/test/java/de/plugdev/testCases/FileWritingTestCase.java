package de.plugdev.testCases;

/*
 * Generated Class by PlugDev.
 * 31.01.2023 um 09:01.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.LibraryManager;
import de.plugdev.client.services.ISettings;
import de.plugdev.services.ServiceManager;

public class FileWritingTestCase {

    public static void main(String[] args) {
        /*
         * Init default and master instance on this test case.
         * Never use the default main method to enable/start the project, because there are
         * some unpredictable bugs cursing around this.
         */

        // Part 1 | default master init  < this is equivalent with the default execution.
        LibraryManager.preInitMasterBranchSynchronously();

        // Part 2 | read default values from standardized keys
        final ISettings settingsManager = ServiceManager.get(ISettings.class);
        final String databaseUrl = settingsManager.getValue("databaseUrl");
        System.out.println(databaseUrl);


        // Part 3 | set custom key with custom value
        settingsManager.setValue("Testkey", "ImATestValue");

        // Part 4 | Disable SettingsManager and SettingsLoader < this is equivalent with the default termination
        ServiceManager.disable(ISettings.class);


        // Part 5 | Enabling SettingsManager and SettingsLoader < this is equivalent with the default init by master
        ServiceManager.enable(ISettings.class);

        // Part 6 | read customized key from file directly
        System.out.println((String)
                ((ISettings) ServiceManager.get(ISettings.class)).getValue("Testkey"));
    }

}
