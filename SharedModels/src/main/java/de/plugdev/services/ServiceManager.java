package de.plugdev.services;

/*
 * Generated Class by PlugDev.
 * 23.01.2023 um 12:44.
 *
 * Alle Rechte vorbehalten. @2023
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceManager {

    private final static Map<Class<? extends IService>, IService> MAP = new HashMap<>();
    private final static List<Class<? extends IService>> CLASSES = new ArrayList<>();


    public static boolean exists(Class<? extends IService> clazz) {
        return MAP.containsKey(clazz);
    }

    public static void enable(Class<? extends IService> clazz) {
        if(clazz != ILog.class)
            if(exists(ILog.class))
                ((ILog) get(ILog.class)).log("Enabling service " + clazz.getName());
        MAP.get(clazz).enable();
        CLASSES.add(clazz);
    }

    public static void disable(Class<? extends IService> clazz) {
        if(exists(ILog.class))
            ((ILog) get(ILog.class)).log("Disabling service " + clazz.getName());
        MAP.get(clazz).disable();
        CLASSES.remove(clazz);
    }

    public static void enableAll() {
        MAP.forEach((clazz, service) -> enable(clazz));
    }

    public static void disableAll() {
        MAP.forEach((clazz, service) -> disable(clazz));
    }

    public static void add(Class<? extends IService> clazz, IService service) {
        if(clazz != ILog.class)
            if(exists(ILog.class))
                ((ILog) get(ILog.class)).log("Adding service to queue " + clazz.getName());
        MAP.put(clazz, service);
    }

    public static <T extends IService> T get(Class<? extends IService> clazz) {
        return (T) MAP.get(clazz);
    }

    public static boolean enabled(Class<? extends IService> clazz) {
        return CLASSES.contains(clazz);
    }
}
