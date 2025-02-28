package com.lbms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lbms.entity.BookTransaction;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long>{

}
