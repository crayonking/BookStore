package com.bookstore.model;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "bookstore")

public class Book {
	
	@Id
	private int bookId;
	
	
	
	
	private String title;
	 
	 
	private String author;
	
	
	private String publication;
	
	
	private long Year;
	

}
