package com.lbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lbms.entity.UserInfo;
import com.lbms.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/newstudent")
	public String showRegistrationForm(Model model) {
		model.addAttribute("student", new UserInfo());
		return "student/addstudent";
	}


	@PostMapping("/studentregister")
	public String registerStudent(@ModelAttribute("user") UserInfo userInfo, Model model) {
		String result = studentService.studentRegister(userInfo);
		model.addAttribute("message", result);
		return "redirect:/login";
	}
	

}
