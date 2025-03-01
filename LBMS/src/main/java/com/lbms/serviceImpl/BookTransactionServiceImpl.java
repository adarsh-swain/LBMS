package com.lbms.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.entity.Book;
import com.lbms.entity.BookTransaction;
import com.lbms.entity.UserInfo;
import com.lbms.enums.TransactionStatus;
import com.lbms.repository.BookRepository;
import com.lbms.repository.BookTransactionRepository;
import com.lbms.repository.UserRepository;
import com.lbms.service.BookTransactionService;

import jakarta.transaction.Transactional;

@Service
public class BookTransactionServiceImpl implements BookTransactionService {

	@Autowired
	BookTransactionRepository bookTransactionRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	UserRepository userRepository;

//	@Override
//	public BookTransaction issueBook(int userId) {
//
////		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
//		UserInfo user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//		BookTransaction bts = new BookTransaction();
//		Book book=new Book();
//		
////		bts.setBook(book.getBookId());
//		bts.setUser(user);
//		Date issueDate = new Date();
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(issueDate);
//		calendar.add(Calendar.DAY_OF_MONTH, 14);
//
//		bts.setIssueDate(issueDate);
//		bts.setDueDate(calendar.getTime());
//		bts.setStatus(TransactionStatus.ISSUED);
//		return bookTransactionRepository.save(bts);
//	}

	@Transactional
	@Override
	public BookTransaction issueBook(int userId, Long bookId) {
	    // Fetch user
	    UserInfo user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    // Fetch book
	    Book book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new RuntimeException("Book not found"));

	    // Check if there are available copies
	    if (book.getAvailableCopies() <= 0) {
	        throw new RuntimeException("No copies available for this book.");
	    }

	    // Create book transaction
	    BookTransaction bts = new BookTransaction();
	    bts.setUser(user);
	    bts.setBook(book);

	    // Set issue and due date
	    Date issueDate = new Date();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(issueDate);
	    calendar.add(Calendar.DAY_OF_MONTH, 14);
	    Date dueDate = calendar.getTime();

	    bts.setIssueDate(issueDate);
	    bts.setDueDate(dueDate);
	    bts.setStatus(TransactionStatus.ISSUED);

	    // Decrease available copies
	    book.setAvailableCopies(book.getAvailableCopies() - 1);
	    bookRepository.save(book);

	    // Save transaction
	    return bookTransactionRepository.save(bts);
	}



	@Override
	public BookTransaction returnBook(Long transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookTransaction> getUserTransactions(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BookTransaction> getTransactionById(Long transactionId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
