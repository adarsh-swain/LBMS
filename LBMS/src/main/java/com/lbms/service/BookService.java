package com.lbms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.lbms.entity.Book;

public interface BookService {
	
	public void addnewBook(Book book);
	
	public List allBooks();
	
	public void deleteBookbyId(Long bookid);
	
	public Book updateBookById(Long bookid, Book book);

	public Optional<Book> editBookById(Long bookid);
	
	 public List<Map<String, Object>> getBookIdAndName();
}
