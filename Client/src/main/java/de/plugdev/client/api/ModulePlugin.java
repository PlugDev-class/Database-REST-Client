package de.plugdev.client.api;

import de.plugdev.services.IService;
import de.plugdev.services.ServiceManager;

public interface ModulePlugin extends IService {

    void reload();

}
