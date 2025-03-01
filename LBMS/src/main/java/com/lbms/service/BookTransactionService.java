package com.lbms.service;

import java.util.List;
import java.util.Optional;

import com.lbms.entity.BookTransaction;

public interface BookTransactionService {

	public BookTransaction issueBook(int userId, Long bookId);

	BookTransaction returnBook(Long transactionId);

	List<BookTransaction> getUserTransactions(Long userId);

	Optional<BookTransaction> getTransactionById(Long transactionId);

}
