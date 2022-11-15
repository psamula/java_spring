package com.example.demo;

import com.example.demo.generate.GenerateSQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		try {
			//GenerateSQL.generateSQLFile();
		}
		catch (Exception io) {
			io.printStackTrace();
		}

		SpringApplication.run(DemoApplication.class, args);
	}

}
