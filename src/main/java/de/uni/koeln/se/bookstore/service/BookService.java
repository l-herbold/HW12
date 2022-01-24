package de.uni.koeln.se.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.uni.koeln.se.bookstore.datamodel.Book;
import de.uni.koeln.se.bookstore.repository.BookRepo;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> findBooks() {
		
		return bookRepo.findAll();
		
	}
	
	public Optional<Book> fetchBook(int id) {
		
		return bookRepo.findById(id);
		
	}
	
	public Book addBook(Book book) {
		
		return bookRepo.save(book);
		
	}
	
	public boolean deleteBook(int id) {
		
		boolean status;
		try {
			bookRepo.deleteById(id);
			status = true;
		} catch (Exception e) {
			status = false; }	
		return status;
	
	}
	
	public Book findOldest() {
		List<Book> booklist = bookRepo.findAll();
		Book oldestBook = new Book(null, null, Integer.MAX_VALUE);
		for(Book b : booklist) {
			if(b.getYear() <= oldestBook.getYear())
				oldestBook = b;
		}
		return oldestBook;
	}
	
	public Book findLatest() {
		List<Book> booklist = bookRepo.findAll();
		Book latestBook = new Book(null, null, 0);
		for(Book b : booklist) {
			if(b.getYear() >= latestBook.getYear())
				latestBook = b;
		}
		return latestBook;
	}
}
