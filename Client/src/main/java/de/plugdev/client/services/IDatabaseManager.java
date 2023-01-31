package de.plugdev.client.services;

import de.plugdev.services.IService;

import java.sql.Connection;

public interface IDatabaseManager extends IService {

    void connect(String username, String password, String url, String databaseTable);
    void disconnect();

    Connection getConnection();

}
