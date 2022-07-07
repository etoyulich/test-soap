package com.example.test_soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClientEndpoint {

    private static final String namespaceUri = "http://localhost:8181/";

    @PayloadRoot(localPart = "", namespace = namespaceUri)
    @ResponsePayload
    public void getService(String request) {
        System.out.println(request);
        System.out.println("я тут");
    }

}
