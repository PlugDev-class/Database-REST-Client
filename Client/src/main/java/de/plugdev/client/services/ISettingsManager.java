package de.plugdev.client.services;

import de.plugdev.services.IService;

public interface ISettingsManager extends IService {

    <T> T getValue(String key);
    void setValue(String key, Object object);

}
