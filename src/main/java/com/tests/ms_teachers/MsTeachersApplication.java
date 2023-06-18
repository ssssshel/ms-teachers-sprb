package com.tests.ms_teachers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.tests.ms_teachers.client")
@ImportAutoConfiguration({ FeignAutoConfiguration.class })
public class MsTeachersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTeachersApplication.class, args);
	}

}
