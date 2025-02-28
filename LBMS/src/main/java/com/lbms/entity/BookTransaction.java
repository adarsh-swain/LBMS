package com.lbms.entity;

import java.util.Date;

import com.lbms.enums.TransactionStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserInfo user;

    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;  // ISSUED, RETURNED, OVERDUE
}