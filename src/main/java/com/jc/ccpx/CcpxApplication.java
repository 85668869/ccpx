package com.jc.ccpx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jc.ccpx"})
@SpringBootApplication
public class CcpxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CcpxApplication.class, args);
	}

}
