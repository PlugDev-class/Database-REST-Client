package de.plugdev.client.services;

import de.plugdev.services.IService;

public interface ISettingsLoader extends IService {

    boolean fileExists();
    boolean createFile();
    void loadFile();

}
