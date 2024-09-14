package com.bookstore.exception;

//import lombok.experimental.SuperBuilder;

public class BookNotFoundException extends RuntimeException {
	
	public BookNotFoundException(int id) {
		super("Book doesn't exist with id: "+id);
	}

}
