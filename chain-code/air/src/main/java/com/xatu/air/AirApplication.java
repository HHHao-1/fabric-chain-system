package com.xatu.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = {"com.xatu.air","com.xatu.common"})
public class AirApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(AirApplication.class, args);
  }
  
}
