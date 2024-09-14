package com.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.exception.BookAlreadyExistsException;
import com.bookstore.exception.BookNotFoundException;
import com.bookstore.model.Book;
import com.bookstore.repository.BookDAOWrapper;
import com.bookstore.repository.BookRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookDAOWrapper bookDAOWrapper;

	@Autowired
	private BookRepository bookRepository;

	@PostMapping("/addBook")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String addBook(@RequestBody @Valid Book book) {
		
		Optional<Book> existingBook = bookRepository.findById(book.getBookId());
		
		if(existingBook.isPresent()) {
			throw new BookAlreadyExistsException(book.getBookId());
		}

		Book savedBook =  bookDAOWrapper.addBook(book);
		
		return "Book added successfully with id: "+savedBook.getBookId();
	
		
	}

	@GetMapping("/listBooks")
	public List<Book> ListBooks() {
		return bookDAOWrapper.getAllBooks();
	}

	@DeleteMapping("deletebook/{id}")
	public String deleteBook(@PathVariable("id") int id) {

		if (!bookRepository.existsById(id)) {
			throw new BookNotFoundException(id);
		}

		bookRepository.deleteById(id);

		return "Book Deleted Successfully : " + id;
	}

	@GetMapping("/getBook/{id}")
	public Book findBook(@PathVariable("id") int id) {

		return bookDAOWrapper.findBook(id).orElseThrow(() -> new BookNotFoundException(id));
	}
	
	@PutMapping("/updateBook/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		
		return bookRepository.findById(id).map((item) ->{
			
			item.setAuthor(book.getAuthor());
			item.setTitle(book.getTitle());
			item.setPublication(book.getPublication());
			item.setYear(book.getYear());
			
			return bookRepository.save(item);
			
		}).orElseThrow(() -> new BookNotFoundException(id));
	}

}
