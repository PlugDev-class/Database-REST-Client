package de.plugdev.client;

/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 12:43.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.services.*;
import de.plugdev.client.services.implementations.*;
import de.plugdev.services.ILog;
import de.plugdev.services.LogManagerImplementation;
import de.plugdev.services.ServiceManager;

public class ClientInstance {
    @Deprecated
    public static void main(String[] args) {
        preInitMasterBranchSynchronously();
    }

    public static void preInitMasterBranchSynchronously() {
        ServiceManager.add(ILog.class, new LogManagerImplementation());
        ServiceManager.enable(ILog.class);

        final SettingsManagerImplementation service = new SettingsManagerImplementation();
        ServiceManager.add(ISettingsLoader.class, service);
        ServiceManager.add(ISettingsManager.class, service);

        ServiceManager.add(IDatabaseManager.class, new DatabaseManagerImplementation());
        ServiceManager.add(IRestManager.class, new RestManagerImplementation());
        ServiceManager.add(ISecurityManager.class, new SecurityManagerImplementation());

        ServiceManager.enableAll();
    }

}
