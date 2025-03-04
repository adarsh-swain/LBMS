package com.lbms.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.dto.BookTransactionDTO;
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

	@Transactional
	@Override
	public BookTransaction issueBook(int userId, Long bookId) {
		// Fetch user
		UserInfo user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Fetch book
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		// Check if there are available copies
		if (book.getTotalCopies() <= 0) {
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
		book.setTotalCopies(book.getTotalCopies() - 1);
		bookRepository.save(book);
		return bookTransactionRepository.save(bts);
	}

	@Transactional
	@Override
	public BookTransaction returnBook(Long transactionId, Date returnDate) {
		BookTransaction transaction = bookTransactionRepository.findById(transactionId)
				.orElseThrow(() -> new RuntimeException("Transaction not found"));

		if (transaction.getStatus() != TransactionStatus.ISSUED) {
			throw new RuntimeException("Book is not currently issued.");
		}

		Book book = transaction.getBook();
		transaction.setReturnDate(returnDate);

		if (returnDate.after(transaction.getDueDate())) {
			transaction.setStatus(TransactionStatus.OVERDUE);
		} else {
			transaction.setStatus(TransactionStatus.RETURNED);
		}

		book.setTotalCopies(book.getTotalCopies() + 1);
		bookRepository.save(book);
		return bookTransactionRepository.save(transaction);
	}

	@Override
	public List<BookTransactionDTO> getUserTransactions() {
		List<Object[]> results = bookTransactionRepository.findIssuedBooksNative();
		List<BookTransactionDTO> transactions = new ArrayList<>();

		for (Object[] row : results) {
			BookTransactionDTO dto = new BookTransactionDTO();
			dto.setTransId(((Number) row[0]).intValue());
			dto.setFullName((String) row[1]);
			dto.setEmail((String) row[2]);
			dto.setMobile((String) row[3]);
			dto.setTitle((String) row[4]);
			dto.setIsbn((String) row[5]);
			dto.setDueDate((Date) row[6]);
			dto.setIssueDate((Date) row[7]);
			transactions.add(dto);
		}
		return transactions;
	}

}
