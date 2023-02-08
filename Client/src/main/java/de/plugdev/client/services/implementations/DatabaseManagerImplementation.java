package de.plugdev.client.services.implementations;

/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 18:41.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.services.IDatabaseManager;
import de.plugdev.client.services.ISettings;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;
import lombok.SneakyThrows;

import javax.print.attribute.standard.Severity;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManagerImplementation implements IDatabaseManager {

    private Connection connection;

    @SneakyThrows
    @Override
    public void connect(String username, String password, String url, String databaseTable) {
        this.connection = DriverManager.getConnection(url + "/" + databaseTable, username, password);
        ((ILog) ServiceManager.get(ILog.class)).log("Service SQL is now connected.");
    }

    @SneakyThrows
    @Override
    public void disconnect() {
        if(this.connection != null)
            this.connection.close();
        ((ILog) ServiceManager.get(ILog.class)).log("Service SQL is now disconnected.");
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @SneakyThrows
    @Override
    public void enable() {
        if(!ServiceManager.enabled(ISettings.class)) {
            ((ILog) ServiceManager.get(ILog.class)).log("Couldn't get ISettingsLoader-Instance in Cache.",
                    Severity.WARNING);
            return;
        }
        Class.forName(((ISettings) ServiceManager.get(ISettings.class)).getValue("databaseSqlDriver"));
    }

    @Override
    public void disable() {
        disconnect();
    }

}
