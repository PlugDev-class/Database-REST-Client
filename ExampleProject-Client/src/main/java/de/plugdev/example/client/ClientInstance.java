package de.plugdev.example.client;

/*
 * Generated Class by PlugDev.
 * 09.02.2023 um 08:41.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.LibraryManager;
import de.plugdev.example.client.commands.CommandSecurityEncode;
import de.plugdev.example.client.commands.CommandSimpleReply;
import de.plugdev.example.client.utility.CommandManager;
import de.plugdev.example.client.utility.ICommandManager;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;

public class ClientInstance {

    public static void main(String[] args) {
        // Load LibraryManager
        LibraryManager.preInitMasterBranchSynchronously();

        // Create current object-instance and set shutdown-hook (optional)
        ClientInstance clientInstance = new ClientInstance();
        clientInstance.onEnable();
        Runtime.getRuntime().addShutdownHook(new Thread(clientInstance::onDisable));
    }

    public void onEnable() {
        ((ILog) ServiceManager.get(ILog.class)).log("ClientInstance started with all the libraries above.");
        ServiceManager.add(ICommandManager.class, new CommandManager());
        ServiceManager.enable(ICommandManager.class);

        ((ICommandManager) ServiceManager.get(ICommandManager.class)).addExecutor(new CommandSimpleReply());
        ((ICommandManager) ServiceManager.get(ICommandManager.class)).addExecutor(new CommandSecurityEncode());
    }

    public void onDisable() {
        ServiceManager.disableAll();
        ((ILog) ServiceManager.get(ILog.class)).log("ClientInstance stopped.");
    }

}
