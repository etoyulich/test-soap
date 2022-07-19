package com.example.springsoap.gen;

import com.example.springsoap.gen.newgen.ClientInterface;
import jakarta.jws.WebService;

@WebService(endpointInterface = "com.example.springsoap.gen.ClientInterface", targetNamespace = "http://www.example.com/springsoap/gen", serviceName = "ClientWSService", portName = "portName")
public class ClientServiceImpl implements ClientInterface {
    @Override
    public String getClientRequest(String request) {
        return "aboba";
    }
}
