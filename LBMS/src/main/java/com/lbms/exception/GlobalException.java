package com.lbms.exception;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
	

	@ExceptionHandler(HttpSessionRequiredException.class)
    public String handleHttpSessionRequiredException(HttpSessionRequiredException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("type", "error");
        redirectAttributes.addFlashAttribute("msg", "Session expired or invalid session. Please log in again.");
        return "redirect:/login"; // Redirect to login page
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("type", "error");
        redirectAttributes.addFlashAttribute("msg", "You do not have permission to access this page.");
        return "redirect:/permission"; // Redirect to login or an error page
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("type", "error");
        redirectAttributes.addFlashAttribute("msg", "Invalid request method.");
        return "redirect:/login"; // Redirect to login or another page
    }
	


}
