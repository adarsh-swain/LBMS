package com.lbms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.lbms.entity.Book;
import com.lbms.service.BookService;

import jakarta.transaction.Transactional;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/allbook")
	public String allBooks(Model model) {
		List allBooks = bookService.allBooks();
		 model.addAttribute("books", allBooks);
		return"books/booklist";
	}
	
	@GetMapping("/newbook")
    public String showSaveBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/addbook";
    }
	
	@Transactional
	@PostMapping("/addnewbook")
	public String addBook(@ModelAttribute Book book) {
		bookService.addnewBook(book);
		return"redirect:/allbook";
	}
	

	@GetMapping("/deletebook/{bookId}")
    public String deleteBook(Model model,@PathVariable("bookId") Long bookId) {
		bookService.deleteBookbyId(bookId);
		return"redirect:/allbook";
    }
	
	@GetMapping("/bookdetails/{bookId}")
    public String showUpdateForm(@PathVariable("bookId") Long bookId, Model model) {
        Optional<Book> book = bookService.editBookById(bookId);
        if (book != null) { 
        	model.addAttribute("book", book);
            return "books/updatebook"; 
        } else {
            return "redirect:/books?error=notfound";
        }
    }
	
	@PutMapping("/updatebook/{bookId}")
    public String updateBook(Model model,@PathVariable("bookId") Long bookId,@ModelAttribute Book book) {
		 bookService.updateBookById(bookId, book);
		    return "redirect:/allbook";
    }
	
}
