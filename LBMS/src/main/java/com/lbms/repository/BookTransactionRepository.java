package com.lbms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lbms.entity.Book;
import com.lbms.entity.BookTransaction;
import com.lbms.enums.TransactionStatus;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long>{
	
	Optional<BookTransaction> findByBookAndStatus(Book book, TransactionStatus status);

}
