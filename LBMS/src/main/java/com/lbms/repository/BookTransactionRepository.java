package com.lbms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lbms.entity.Book;
import com.lbms.entity.BookTransaction;
import com.lbms.enums.TransactionStatus;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long>{
	
	Optional<BookTransaction> findByBookAndStatus(Book book, TransactionStatus status);
	
//	@Query(value = "SELECT CONCAT(u.fname, ' ', COALESCE(u.mname, ''), ' ', u.lname) AS full_name, u.email, u.mobile, b.title, b.isbn, bt.due_date, bt.issue_date "
//			+ "FROM book_transactions bt " + "JOIN users u ON u.id = bt.user_id "
//			+ "JOIN books b ON bt.book_id = b.book_id " + "WHERE bt.status = 'ISSUED'", nativeQuery = true)
//	List<Object[]> findIssuedBooksNative();
	
	@Query(value = "SELECT bt.id,CONCAT(u.fname, ' ', COALESCE(u.mname, ''), ' ', u.lname) AS full_name, "
	        + "u.email, u.mobile, b.title, b.isbn, bt.due_date, bt.issue_date "
	        + "FROM book_transactions bt "
	        + "JOIN users u ON u.id = bt.user_id "
	        + "JOIN books b ON bt.book_id = b.book_id "
	        + "WHERE bt.status = 'ISSUED'", nativeQuery = true)
	List<Object[]> findIssuedBooksNative();



}
