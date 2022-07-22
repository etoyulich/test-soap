package com.example.testsoap;

import com.example.springsoap.gen.ClientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class TestSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSoapApplication.class, args);
        Endpoint.publish("http://localhost:8181/ws", new ClientServiceImpl());
    }

}
