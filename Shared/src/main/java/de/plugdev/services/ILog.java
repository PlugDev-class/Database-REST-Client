package de.plugdev.services;

import javax.print.attribute.standard.Severity;

public interface ILog extends IService {

    void log(String message);
    void log(String message, Severity severity);

}
