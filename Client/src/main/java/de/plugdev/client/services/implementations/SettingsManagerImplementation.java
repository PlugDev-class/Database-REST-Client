package de.plugdev.client.services.implementations;

/*
 * Generated Class by PlugDev.
 * 27.01.2023 um 11:55.
 *
 * Alle Rechte vorbehalten. @2023
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.plugdev.client.services.ISettingsLoader;
import de.plugdev.client.services.ISettingsManager;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SettingsManagerImplementation implements ISettingsManager, ISettingsLoader {

    private boolean alreadyInitialized = false;


    private final File file = new File("settings.inf");
    private Map<String, Object> map = new HashMap<>();
    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public boolean fileExists() {
        return file.exists();
    }

    @SneakyThrows
    @Override
    public boolean createFile() {
        ((ILog) ServiceManager.get(ILog.class)).log("\"settings.json\" file not found! New file will be created.");
        file.createNewFile();
        Map<String, Object> map = new HashMap<>();
        map.put("uniqueId", UUID.randomUUID());
        map.put("author", "PlugDev");
        map.put("databaseUrl", "jdbc:mysql://localhost:3306");
        map.put("databaseUsername", "admin");
        map.put("databasePassword", "yourPassword");
        map.put("databaseTable", "yourTable");
        map.put("databaseSqlDriver", "com.mysql.cj.jdbc.Driver");
        map.put("restBaseUrl", "https://localhost/restServer");
        map.put("restVersion", "v1");
        map.put("restCredentialsWebApiKey", "yourRestApiKey");
        map.put("securityEncryptionUsername", "yourUserOrPassword");
        map.put("adminBackupUsername", "admin");
        map.put("adminBackupPassword", "veryImportantPassword");
        map.put("adminBackupAccountEnabled", false);
        final String string = GSON.toJson(map);
        Files.write(file.toPath(), string.getBytes(StandardCharsets.UTF_8));
        ((ILog) ServiceManager.get(ILog.class)).log("\"settings.json\" file created! Please change any information in " +
                "this file immediately. The functionality of this program is not guaranteed without the sql, rest or/" +
                "and core-files.");
        return file.exists();
    }

    @SneakyThrows
    @Override
    public void loadFile() {
        map = (Map<String, Object>) GSON.fromJson(new String(Files.readAllBytes(file.toPath())), Map.class);
    }

    @SneakyThrows
    @Override
    public <T> T getValue(String key) {
        final Object object = map.get(key);
        assert object != null : "Object is null";
        return (T) object;
    }

    @SneakyThrows
    @Override
    public void setValue(String key, Object object) {
        map.remove(key);
        map.put(key, object);
        Files.write(file.toPath(), GSON.toJson(map).getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override // niu (= not in use)
    public void enable() {
        if(alreadyInitialized)
            return;
        if (!fileExists())
            createFile();
        loadFile();
        alreadyInitialized = true;
    }

    @Override // niu (= not in use)
    public void disable() {}
}
