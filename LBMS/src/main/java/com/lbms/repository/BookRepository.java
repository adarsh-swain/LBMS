package com.lbms.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lbms.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
//	 @Query("SELECT b.bookId AS bookId, b.title AS title FROM Book b")
//	    List<Book> findAllBooks();
	
	 @Query(value = "SELECT book_id AS bookId, title FROM books", nativeQuery = true)
	    List<Map<String, Object>> getBookIdAndName();

}
