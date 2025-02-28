package com.lbms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.entity.Book;
import com.lbms.repository.BookRepository;
import com.lbms.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	public BookRepository bookRepository;
	
	@Override
	public void addnewBook(Book book) {
		bookRepository.save(book);
	}

	@Override
	public List allBooks() {
		List allbooks = bookRepository.findAll();
		allbooks.forEach(book -> System.out.println(book));
		return allbooks;
	}

	@Override
	public void deleteBookbyId(Long bookid) {
        bookRepository.deleteById(bookid);
	}

	@Override
	public Book updateBookById(Long bookid, Book book) {
		Optional<Book> bookIddata = bookRepository.findById(bookid);
		if(bookIddata.isPresent()) {
			 Book existingBook = bookIddata.get();
			 existingBook.setTitle(book.getTitle());
			 existingBook.setAuthor(book.getAuthor());
			 existingBook.setIsbn(book.getIsbn());
			 existingBook.setCategory(book.getCategory());
			 existingBook.setTotalCopies(book.getTotalCopies());
			 return bookRepository.save(existingBook);
		}else {
			return null;
		}
	}

	@Override
	public Optional<Book> editBookById(Long bookid) {
		 Optional<Book> bookDetails = bookRepository.findById(bookid);
		return bookDetails;
	}

}
