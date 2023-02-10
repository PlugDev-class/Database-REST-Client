package de.plugdev.example.client.commands;

/*
 * Generated Class by PlugDev.
 * 09.02.2023 um 18:57.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.example.client.utility.CommandExecutor;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;

import java.util.Arrays;

public class CommandSimpleReply extends CommandExecutor {

    public CommandSimpleReply() {
        super(new String[] { "echo", "reply" });
    }

    @Override
    protected void execute(String[] input) {
        final StringBuilder builder = new StringBuilder();
        Arrays.stream(input).toList().forEach((entry) -> {
            builder.append(entry);
            builder.append(" ");
        });
        ((ILog) ServiceManager.get(ILog.class)).log(String.format("Echo: %s", builder));
    }

}
