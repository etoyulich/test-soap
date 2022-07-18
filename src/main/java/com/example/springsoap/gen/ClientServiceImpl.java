package com.example.springsoap.gen;

import jakarta.jws.WebService;

@WebService(endpointInterface = "com.example.springsoap.gen.ClientService", targetNamespace = "http://soap.leonis.md/", serviceName = "serviceName", portName = "portName")
public class ClientServiceImpl implements ClientService{
    @Override
    public String getClientRequest(String request) {
        return "aboba";
    }
}
