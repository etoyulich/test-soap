package com.example.test_soap;

import com.example.test_soap.client.gen.GetClientResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Endpoint
public class ClientEndpoint {

    private static final String namespaceUri = "http://localhost:8181/";

    @PayloadRoot(localPart = "getClientRequest", namespace = namespaceUri)
    @ResponsePayload
    public String getClientResponse(@RequestPayload String request) {
        GetClientResponse response = new GetClientResponse();
        System.out.println(request);
        System.out.println("я тут");
        return "aaaa";
    }

}
