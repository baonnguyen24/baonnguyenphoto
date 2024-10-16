package com.baonnguyen.services;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface FlickrAuthService {
    public String getRequestToken(String callbackUrl) throws IOException, ExecutionException, InterruptedException;

    public void getAccessToken(String oauthVerifier) throws IOException, ExecutionException, InterruptedException;

    public String makeAuthenticatedRequest(String apiUrl) throws IOException, ExecutionException, InterruptedException;

}
