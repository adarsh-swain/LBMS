package com.lbms.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.lbms.dto.BookTransactionDTO;
import com.lbms.entity.BookTransaction;

public interface BookTransactionService {

	public BookTransaction issueBook(int userId, Long bookId);

//	BookTransaction returnBook(Long transactionId,BookTransaction bookTransaction);

	List<BookTransactionDTO> getUserTransactions();
	
	public BookTransaction returnBook(Long transactionId, Date returnDate);

}
