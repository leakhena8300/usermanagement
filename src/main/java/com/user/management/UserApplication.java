/**
 * Entry point and core configuration of a Spring Boot application
 * 
 * @author Leakhena SUON
 * @version 1.0
 * @since 2024-04-30
 */
package com.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.user.management")
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
