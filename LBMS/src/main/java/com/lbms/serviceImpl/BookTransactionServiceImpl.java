package com.lbms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.entity.BookTransaction;
import com.lbms.repository.BookTransactionRepository;
import com.lbms.service.BookTransactionService;

@Service
public class BookTransactionServiceImpl implements BookTransactionService{
	
	@Autowired
	BookTransactionRepository bookTransactionRepository;

	@Override
	public BookTransaction issueBook(Long bookId, Long userId) {
		
		return null;
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
