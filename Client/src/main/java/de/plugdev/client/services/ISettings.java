package de.plugdev.client.services;

import de.plugdev.services.IService;

public interface ISettings extends IService {

    <T> T getValue(String key);
    void setValue(String key, Object object);

    boolean fileExists();
    boolean createFile();
    void loadFile();

}
