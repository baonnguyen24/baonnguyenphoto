package com.baonnguyen.services.impl;
import com.baonnguyen.services.FlickrAuthService;

import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth10aService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public class FlickrAuthServiceImpl implements FlickrAuthService {
    private static final String API_KEY = "e617b64d64cdd4025e6cd88463e567c8"; // Consumer Key & Secret
    private static final String API_SECRET = "e5d11c072107b9da";

    private static final String CALLBACK_URL = "http://yourapp.com/api/flickr/callback"; // Replace with your callback URL

    private OAuth10aService service;
    private OAuth1RequestToken requestToken;
    private OAuth1AccessToken accessToken;

    public FlickrAuthServiceImpl(){
        service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .callback(CALLBACK_URL)
                .build(FlickrApi.instance());
    }

    // Step 1: Get unauthorized request token and return the authorization URL
    @Override
    public String getRequestToken(String callbackUrl) throws IOException, ExecutionException, InterruptedException {
        requestToken = service.getRequestToken();
        return service.getAuthorizationUrl(requestToken);
    }

    // Step 2: User authorizes request is managed on web

    // Step 3: Exchange request token and verifier for access token
    @Override
    public void getAccessToken(String oauthVerifier) throws IOException, ExecutionException, InterruptedException {
        accessToken  = service.getAccessToken(requestToken, oauthVerifier);
    }

    // Step 4: Make authenticated requests using the access token
    @Override
    public String makeAuthenticatedRequest(String apiUrl) throws IOException, ExecutionException, InterruptedException{
        OAuthRequest oauthRequest = new OAuthRequest(com.github.scribejava.core.model.Verb.GET, apiUrl);
        service.signRequest(accessToken, oauthRequest);

        try(Response response = service.execute(oauthRequest)) {
            return response.getBody();
        }
    }
}
