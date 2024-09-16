package com.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.bookstore.dto.BookDTO;
import com.bookstore.exception.BookAlreadyExistsException;
import com.bookstore.model.Book;

@Repository
public class BookDAOWrapper {
	
	Logger logger = LoggerFactory.getLogger(BookDAOWrapper.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	public Book addBook(BookDTO bookdto) {
		
		Book book = new Book(bookdto.getBookId(),bookdto.getTitle(),bookdto.getAuthor(),bookdto.getPublication(),bookdto.getYear());
		
		return bookRepository.save(book);
	}
	
//	@Cacheable(value = "cache")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
		
	}
	
	@Cacheable(value = "cache")
	public Optional<Book> findBook(Integer id) {
		Optional<Book> entity = bookRepository.findById(id);
		return entity;
	}
	
	
//	public Book updateBook(Book book) {
//		
//		Optional<Book> existingBook = bookRepository.findById(book.getBookId());
//		logger.info(existingBook);
//		
//		
//	}

}
