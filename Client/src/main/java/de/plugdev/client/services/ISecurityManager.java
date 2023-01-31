package de.plugdev.client.services;

import de.plugdev.services.IService;
import lombok.SneakyThrows;

public interface ISecurityManager extends IService {

    byte[] encode(Object object);
    <T> T decode(Class<T> t, byte[] array);

    void setOverrideMacAddress(byte[] array);
    void setPassword(String password);

}
