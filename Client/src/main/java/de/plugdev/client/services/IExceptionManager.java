package de.plugdev.client.services;

import de.plugdev.client.modality.ExceptionSevernis;
import de.plugdev.services.IService;

public interface IExceptionManager extends IService {

    void addException(Exception exception, ExceptionSevernis exceptionSevernis);
    Exception[] getExceptions();

}
