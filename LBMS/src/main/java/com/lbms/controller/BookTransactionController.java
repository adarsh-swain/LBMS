package com.lbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lbms.entity.BookTransaction;
import com.lbms.service.BookTransactionService;

import ch.qos.logback.core.model.Model;

@Controller
public class BookTransactionController {
	
	
	 @Autowired
	 BookTransactionService bookTransactionService;

//	    @PostMapping("/issue")
//	    public BookTransaction issueBook(@RequestParam Long userId) {
//	        BookTransaction transaction = bookTransactionService.issueBook(bookId, userId);
//	        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
//	    }
	 
//	 @GetMapping("/issuebook")
//	 public String issuebook(Model model) {
//		
//		 return "books/issuebook";
//	 }
	
	 
	 @PostMapping("/issuebook")
	 public String issueBook(@RequestParam("studentId") int studentId,@RequestParam("bookId") Long bookId) {
	     bookTransactionService.issueBook(studentId, bookId);
	     return "redirect:/allstudent"; // Redirect to the issue book page
	 }


}
