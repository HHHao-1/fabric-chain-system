package com.xatu.navy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication( scanBasePackages = {"com.xatu.navy","com.xatu.common"})
public class NavyApplication extends SpringBootServletInitializer {
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(NavyApplication.class);
  }
  
  public static void main(String[] args) {
    SpringApplication.run(NavyApplication.class, args);
  }
  
}
