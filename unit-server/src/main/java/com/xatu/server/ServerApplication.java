package com.xatu.server;

import com.xatu.server.service.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ServerApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ServerApplication.class, args);
    try {
      Server.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
