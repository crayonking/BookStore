package com.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bookstore.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{
	
	

}
