package com.in28minutes.springboot.basics.springbootin10steps;

import java.util.List;

import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	
	@GetMapping("/books")
	public List<Book> retrieveAllBooks() {
		return Arrays.asList(new Book(1l, "Me", "Me"));
	}
}
