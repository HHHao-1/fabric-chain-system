package com.xatu.army;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = {"com.xatu.army","com.xatu.common"})
public class ArmyApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(ArmyApplication.class, args);
  }
  
}
