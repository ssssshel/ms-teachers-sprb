package com.tests.ms_teachers.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class EnvConfiguration {
  public EnvConfiguration() {
    Dotenv.configure().load().entries().forEach((entry) -> {
      System.setProperty(entry.getKey(), entry.getValue());
    });
  }
}
