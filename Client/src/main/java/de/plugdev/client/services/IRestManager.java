package de.plugdev.client.services;

import de.plugdev.services.IService;
import de.plugdev.web.RestResponse;
import okhttp3.FormBody;

public interface IRestManager extends IService {

    void setDefaultHost(String string);
    void setRestVersion(byte version);
    void setDefaultWebKey(String string);
    RestResponse get(String path);

    RestResponse post(String path, FormBody.Builder builder);


    RestResponse patch(String path, FormBody.Builder builder);

    RestResponse delete(String path, FormBody.Builder builder);
}
