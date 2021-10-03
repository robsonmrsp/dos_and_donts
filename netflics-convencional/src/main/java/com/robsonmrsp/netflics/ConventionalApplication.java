package com.robsonmrsp.netflics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ConventionalApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConventionalApplication.class, args);
  }
}
