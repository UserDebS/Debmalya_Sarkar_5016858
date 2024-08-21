package com.bookstoreapi.bookstoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bookstoreapi.bookstoreapi")
public class BookstoreapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreapiApplication.class, args);
	}

}
