package com.zbj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.zbj.dao")
@SpringBootApplication
public class A {
	public static void main(String[] args) {
		SpringApplication.run(A.class, args);
	}

}
