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
import com.lbms.entity.UserInfo;
import com.lbms.service.StudentService;
import com.lbms.service.UserService;

import jakarta.transaction.Transactional;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	
	@GetMapping("/allstudent")
	public String allBooks(Model model) {
		List allstudents = studentService.allStudent();
		System.out.println(allstudents);
		model.addAttribute("allstudents", allstudents);
		return"student/studentlist";
	}
	
	@GetMapping("/newstudent")
	public String showRegistrationForm(Model model) {
		model.addAttribute("student", new UserInfo());
		return "student/addstudent";
	}


	@Transactional
	@PostMapping("/studentregister")
	public String registerStudent(UserInfo userInfo, Model model) {
		String result = studentService.studentRegister(userInfo);
		model.addAttribute("message", result);
		return "redirect:/allstudent";
	}
	
	
	@GetMapping("/deletestudent/{userId}")
    public String deleteBook(Model model,@PathVariable("userId") int userId) {
		studentService.deleteStudentbyId(userId);
		return"redirect:/allstudent";
    }
	
	@GetMapping("/studentdetails/{userId}")
    public String showUpdateForm(@PathVariable("userId") int userId, Model model) {
        Optional<UserInfo> userinfo = studentService.editStudentById(userId);
        if (userinfo != null) { 
        	model.addAttribute("userinfo", userinfo);
            return "student/updatestudent"; 
        } else {
            return "redirect:/books?error=notfound";
        }
    }
	
	@PutMapping("/updatestudent/{userId}")
    public String updateBook(Model model,@PathVariable("userId") int userId,@ModelAttribute UserInfo userInfo) {
		studentService.updateStudentById(userId, userInfo);
		    return "redirect:/allstudent";
    }

}
