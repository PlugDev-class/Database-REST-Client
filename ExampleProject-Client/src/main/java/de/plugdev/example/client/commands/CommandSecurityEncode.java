package de.plugdev.example.client.commands;

/*
 * Generated Class by PlugDev.
 * 09.02.2023 um 19:06.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.services.ISecurityManager;
import de.plugdev.example.client.utility.CommandExecutor;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;

import java.util.Arrays;

public class CommandSecurityEncode extends CommandExecutor {

    public CommandSecurityEncode() {
        super(new String[] { "security_encode" });
    }

    @Override
    protected void execute(String[] input) {
        final StringBuilder builder = new StringBuilder();
        Arrays.stream(input).toList().forEach((entry) -> {
            builder.append(entry);
            builder.append(" ");
        });
        final byte[] encode = ((ISecurityManager) ServiceManager.get(ISecurityManager.class)).encode(builder);
        ((ILog) ServiceManager.get(ILog.class)).log(String.format("Encoded string: \"%s\"", new String(encode)));
    }

}
