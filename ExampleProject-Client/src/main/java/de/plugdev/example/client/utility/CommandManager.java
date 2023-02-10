package de.plugdev.example.client.utility;

/*
 * Generated Class by PlugDev.
 * 09.02.2023 um 08:47.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;

import javax.print.attribute.standard.Severity;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommandManager implements ICommandManager {

    private final List<CommandExecutor> commandExecutors = new LinkedList<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void addExecutor(CommandExecutor executor) {
        commandExecutors.add(executor);
        ((ILog) ServiceManager.get(ILog.class)).log(String.format("CommandExecutor \"%s\" registered!",
                executor.getClass().getName()));
    }

    @Override
    public void deleteExecutor(Class<? extends CommandExecutor> clazz) {
        commandExecutors.removeIf((predicate) -> predicate.getClass().hashCode() == clazz.hashCode());
    }

    @Override
    public void execute(String[] args) {
        final List<CommandExecutor> availableExecutors = commandExecutors.stream().filter((pred) -> {
            final List<String> aliases = Arrays.stream(pred.getAliases()).toList();
            return aliases.contains(args[0]);
        }).toList();
        if(availableExecutors.isEmpty()) {
            ((ILog) ServiceManager.get(ILog.class)).log("Command not found!", Severity.ERROR);
            return;
        }
        availableExecutors.forEach((orderedObject) -> orderedObject.execute(args));
    }

    @Override
    public void listen() {
        executorService.submit(() -> {
            final Scanner scanner = new Scanner(System.in);
            String tempLine;
            while((tempLine = scanner.nextLine()) != null) {
                execute(tempLine.split(" "));
            }
            scanner.close();
        });
    }

    @Override
    public void stopListening() {
        executorService.shutdown();
    }

    @Override
    public void enable() {
        listen();
    }

    @Override
    public void disable() {
        stopListening();
    }
}
