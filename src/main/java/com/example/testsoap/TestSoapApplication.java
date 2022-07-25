package com.example.testsoap;

import com.example.springsoap.gen.ClientServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class TestSoapApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TestSoapApplication.class, args);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/application.properties"));
        String address = properties.getProperty("soap.address");
        Endpoint.publish(address, new ClientServiceImpl());
    }

}
