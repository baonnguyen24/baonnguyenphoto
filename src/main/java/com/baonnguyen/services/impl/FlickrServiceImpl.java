package com.baonnguyen.services.impl;

import com.baonnguyen.services.FlickrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlickrServiceImpl implements FlickrService {
    private static final String FLICKR_API_URL = "https://flickr.com/services/rest/";
    private static final String API_KEY = "e617b64d64cdd4025e6cd88463e567c8";
    private static final String API_SECRET = "e5d11c072107b9da";

    @Autowired
    private RestTemplate restTemplate;
}
