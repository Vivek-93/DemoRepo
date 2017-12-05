package com.bitplay.restpos.async;

/**
 * Created by vivek on 5/11/15.
 */
public interface IAsyncInteractor {
    //   void validateCredentialsAsync(OnRequestListener listener, String url);

    void validateCredentialsAsync(OnRequestListener listener, String url);
    void validateCredentialsAsync(OnRequestListener listener, int pid, String url);
    void validateCredentialsAsync(OnRequestListener listener, String url, int pid);

}
