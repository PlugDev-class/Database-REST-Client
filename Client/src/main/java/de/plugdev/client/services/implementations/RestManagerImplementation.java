package de.plugdev.client.services.implementations;

/*
 * Generated Class by PlugDev.
 * 25.01.2023 um 12:49.
 *
 * Alle Rechte vorbehalten. @2023
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.plugdev.client.services.IRestManager;
import de.plugdev.web.ResponseCode;
import de.plugdev.web.RestResponse;
import lombok.SneakyThrows;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.ConnectException;
import java.util.UUID;

public class RestManagerImplementation implements IRestManager {
    
    private String host;
    private String version;
    private String baseURL;
    private String webApiKey;

    public static final OkHttpClient CLIENT = new OkHttpClient();
    public final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void setDefaultHost(String string) {
        this.host = string;
        this.baseURL = host + "/v" + version + "/";
    }

    @Override
    public void setRestVersion(byte version) {
        this.version = "" + version;
        this.baseURL = host + "/v" + version + "/";
    }

    @Override
    public void setDefaultWebKey(String string) {
        this.webApiKey = string;
    }

    @SneakyThrows
    @Override
    public RestResponse get(String path) {
        final Call authorization = CLIENT.newCall(new Request.Builder()
                .url(baseURL + path)
                .header("Authorization", webApiKey != null ? webApiKey : "")
                .get().build());

        try {
            return getRestResponse(authorization.execute());
        } catch (ConnectException nameless) {
            return new RestResponse(UUID.randomUUID(), ResponseCode.CLIENT_FAILURE, (short) 408, "Client " +
                    "Timeout");
        }

    }

    @SneakyThrows
    @Override
    public RestResponse post(String path, FormBody.Builder builder) {
        final Call authorization = CLIENT.newCall(new Request.Builder()
                .url(baseURL + path)
                .addHeader("Authorization", webApiKey != null ? webApiKey : "")
                .post(builder.build()).build());
        try {
            return getRestResponse(authorization.execute());
        } catch (ConnectException nameless) {
            return new RestResponse(UUID.randomUUID(), ResponseCode.CLIENT_FAILURE, (short) 408, "Client " +
                    "Timeout");
        }
    }

    @SneakyThrows
    @Override
    public RestResponse patch(String path, FormBody.Builder builder) {
        final Call authorization = CLIENT.newCall(new Request.Builder()
                .url(baseURL + path)
                .addHeader("Authorization", webApiKey != null ? webApiKey : "")
                .patch(builder.build()).build());
        try {
            return getRestResponse(authorization.execute());
        } catch (ConnectException nameless) {
            return new RestResponse(UUID.randomUUID(), ResponseCode.CLIENT_FAILURE, (short) 408, "Client " +
                    "Timeout");
        }
    }

    @SneakyThrows
    @Override
    public RestResponse delete(String path, FormBody.Builder builder) {
        final Request.Builder authorization = new Request.Builder()
                .url(baseURL + path)
                .addHeader("Authorization", webApiKey != null ? webApiKey : "")
                .delete(builder.build());
        try {
            return getRestResponse(CLIENT.newCall(authorization.build()).execute());
        } catch (ConnectException nameless) {
            return new RestResponse(UUID.randomUUID(), ResponseCode.CLIENT_FAILURE, (short) 408, "Client " +
                    "Timeout");
        }
    }

    @NotNull
    private RestResponse getRestResponse(Response execute) throws IOException {
        return new RestResponse(
                UUID.randomUUID(),
                execute.code() >= 200 && execute.code() < 300 ? ResponseCode.CLIENT_SUCCESS :
                execute.code() >= 300 && execute.code() < 400 ? ResponseCode.UNKNOWN_RESPONSE :
                execute.code() >= 400 && execute.code() < 500 ? ResponseCode.CLIENT_FAILURE :
                execute.code() >= 500 && execute.code() < 600 ? ResponseCode.SERVER_FAILURE :
                ResponseCode.UNKNOWN_RESPONSE,
                (short) execute.code(),
                execute.body() != null ? execute.code()
                > 200 && execute.code() < 300 ? execute.body().string() : execute.body()
                        .string().trim().replaceAll("\n", "") : null
        );
    }

    @Override // niu (= not in use)
    public void enable() {}

    @Override // niu (= not in use)
    public void disable() {}
}
