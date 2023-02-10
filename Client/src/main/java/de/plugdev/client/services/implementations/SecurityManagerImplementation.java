package de.plugdev.client.services.implementations;

/*
 * Generated Class by PlugDev.
 * 30.01.2023 um 09:45.
 *
 * Alle Rechte vorbehalten. @2023
 */

import com.google.gson.Gson;
import de.plugdev.client.services.ISecurityManager;
import de.plugdev.services.ILog;
import de.plugdev.services.ServiceManager;
import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.print.attribute.standard.Severity;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;

public class SecurityManagerImplementation implements ISecurityManager {


    private final static Gson GSON = new Gson();

    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    private String passwordDecrypted;
    private byte[] hardwareAddress;

    @SneakyThrows
    @Override
    public byte[] encode(Object object) {
        final String jsonString = GSON.toJson(object);
        final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(jsonString.getBytes());
    }

    @SneakyThrows
    @Override
    public <T> T decode(Class<T> t, byte[] array) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        final byte[] bytes = cipher.doFinal(array);
        String jsonFormat = new String(bytes);
        return GSON.fromJson(jsonFormat, t);
    }

    @Override
    public void setOverrideMacAddress(byte[] array) {
        this.hardwareAddress = array;
        genKey(passwordDecrypted, hardwareAddress);
    }

    @Override
    public void setPassword(String password) {
        this.passwordDecrypted = password;
        genKey(passwordDecrypted, hardwareAddress);
    }

    @SneakyThrows
    @Override
    public void enable() {
        final InetAddress localhost = InetAddress.getLocalHost();
        final byte[] hardwareAddress1 = NetworkInterface.getByInetAddress(localhost).getHardwareAddress();
        if(hardwareAddress1 == null) {
            ServiceManager.disable(ISecurityManager.class);
            ((ILog) ServiceManager.get(ILog.class)).log("No active network card found! Terminating Client.jar.",
                    Severity.ERROR);
            ServiceManager.disableAll();
            System.exit(-4080);
        }
        hardwareAddress = hardwareAddress1; // TODO:  rename var
        passwordDecrypted = "CurrentlyNoPasswordGenerated";
        secretKey = genKey(passwordDecrypted, hardwareAddress);
    }

    @SneakyThrows
    private SecretKey genKey(String password, byte[] array) {
        ((ILog) ServiceManager.get(ILog.class)).log("PBKDF2WithHmacSHA256-Keys will be generated in a few seconds..");
        final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        final KeySpec spec = new PBEKeySpec(password.toCharArray(), array, 65536, 256);
        final SecretKeySpec aes = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        ((ILog) ServiceManager.get(ILog.class)).log("PBKDF2WithHmacSHA256-Keys generated");

        byte[] byteArray = new byte[16];
        new SecureRandom().nextBytes(byteArray);
        ivParameterSpec = new IvParameterSpec(byteArray);
        return aes;
    }

    @Override
    public void disable() {

    }
}
