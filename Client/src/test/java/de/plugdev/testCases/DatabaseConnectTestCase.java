package de.plugdev.testCases;

/*
 * Generated Class by PlugDev.
 * 01.02.2023 um 18:39.
 *
 * Alle Rechte vorbehalten. @2023
 */

import de.plugdev.client.ClientInstance;
import de.plugdev.client.services.IDatabaseManager;
import de.plugdev.services.ServiceManager;
import lombok.SneakyThrows;

import java.sql.Statement;

public class DatabaseConnectTestCase {

    @SneakyThrows
    public static void main(String[] args) {
        // Initialize the main library
        ClientInstance.preInitMasterBranchSynchronously();

        // Connect to database and check if it's connected
        final IDatabaseManager database = ServiceManager.get(IDatabaseManager.class);
        database.connect("root", "", "jdbc:mysql://localhost:3306", "test");
        assert database.getConnection() != null;
        assert !database.getConnection().isClosed();

        // Execute a simple insert
        final Statement statement = database.getConnection().createStatement();
        statement.execute("INSERT INTO `usercredentials`(`USERNAME`, `PASSWORD`) VALUES ('" +
                System.nanoTime() + "','test')");
        statement.closeOnCompletion();

        // It works!
    }

}
