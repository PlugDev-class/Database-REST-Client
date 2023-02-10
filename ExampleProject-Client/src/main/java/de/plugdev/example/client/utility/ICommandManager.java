package de.plugdev.example.client.utility;

import de.plugdev.services.IService;

public interface ICommandManager extends IService {

    void addExecutor(CommandExecutor executor);

    void deleteExecutor(Class<? extends CommandExecutor> clazz);

    void execute(String[] args);

    void listen();

    void stopListening();
}
