package com.lbms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lbms.dto.BookTransactionDTO;
import com.lbms.entity.BookTransaction;
import com.lbms.service.BookTransactionService;

import ch.qos.logback.core.model.Model;

@Controller
public class BookTransactionController {

	@Autowired
	BookTransactionService bookTransactionService;



//	@PostMapping("/issuebook")
//	public String issueBook(@RequestParam("studentId") int studentId, @RequestParam("bookId") Long bookId) {
//		bookTransactionService.issueBook(studentId, bookId);
//		return "redirect:/allstudent"; // Redirect to the issue book page
//	}
	
	@PostMapping("/issuebook")
	public String issueBook(@RequestParam("studentId") int studentId, 
	                        @RequestParam("bookId") Long bookId,
	                        RedirectAttributes redirectAttributes) {
	    try {
	        bookTransactionService.issueBook(studentId, bookId);
	        redirectAttributes.addFlashAttribute("successMessage", "Book issued successfully!");
	    } catch (RuntimeException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	    }
	    return "redirect:/allstudent"; 
	}


	@GetMapping("/issued")
	public String getIssuedTransactions(ModelMap model) {
		List<BookTransactionDTO> transactions = bookTransactionService.getUserTransactions();
		model.addAttribute("transactions", transactions);
		return "books/transactionlist";
	}

//	 @PostMapping("/return")
//	    public String returnBook(@RequestParam Long transactionId, @RequestParam Date returnDate) {
//	        bookTransactionService.returnBook(transactionId, returnDate);
//	        return "redirect:/transactions/issued";
//	    }

	@PostMapping("/return")
	public String returnBook(@RequestParam("transactionId") Long transactionId,
			@RequestParam("returnDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate) {
		bookTransactionService.returnBook(transactionId, returnDate);
		return "redirect:/issued";
	}

}
