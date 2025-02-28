package com.lbms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lbms.entity.UserInfo;
import com.lbms.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
//	@GetMapping("/index")
//	public String index(Model model) {
//		return "menu/index";
//	}
	
	@GetMapping("/index")
	public String showRegistrationForm(Model model) {
		return "login/register";
	}

	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UserInfo userInfo, Model model) {
		String result = userService.register(userInfo);
		model.addAttribute("message", result);
		return "redirect:/login";
	}


}
