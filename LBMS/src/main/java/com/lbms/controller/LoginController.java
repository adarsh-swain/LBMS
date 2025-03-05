package com.lbms.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lbms.dto.LoginRequest;
import com.lbms.entity.UserInfo;
import com.lbms.jwt.JwtService;
import com.lbms.repository.UserRepository;
import com.lbms.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
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
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("user", new UserInfo());
		return "login/login";
	}

	@PostMapping("/loginreq")
	public String authenticationAndGetToken(@ModelAttribute("user") LoginRequest authRequest, Model model,
			HttpSession session) {
		System.out.println("Authenticated Roles: ");
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			if (authentication.isAuthenticated()) {
				List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList());
				System.out.println("Authenticated Roles: " + roles);
				String token = jwtService.generateToken(authRequest.getUsername(), roles);
				model.addAttribute("token", token);
				session.setAttribute("token", token);
				session.setAttribute("username", authRequest.getUsername());
                session.setAttribute("roles", roles); 
				System.out.println("ROLES: " + roles);

				Optional<UserInfo> userInfoOpt = userRepository.findByEmail(authRequest.getUsername());
				if (userInfoOpt.isPresent()) {
					UserInfo userInfo = userInfoOpt.get();
					int status = userInfo.getStatus();

					if (roles.contains("ROLE_STUDENT")) {
						if (status == 1) {
							return "redirect:/dashboard";
						} else {
							return "redirect:/permission";
						}
					}else if(roles.contains("ROLE_LIBRARIAN")) {
						if (status == 1) {
							return "redirect:/allbook";
						} else {
							return "redirect:/permission";
						}
					}else if (roles.contains("ROLE_ADMIN")) {
						return "redirect:/allbook";
					} else {
						return "redirect:/login";
					}
				}
			}
			model.addAttribute("error", "Invalid credentials");
			return "login";

		} catch (Exception e) {
			model.addAttribute("error", "Authentication failed: " + e.getMessage());
			return "login";
		}
	}
	
	@GetMapping("/permission")
	public String permission(Model model) {
		model.addAttribute("user", new UserInfo());
		return "login/permission";
	}
	
	 @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate();
	        return "redirect:/login?logout";
	    }

	@GetMapping("/dashboard")
	public String showDash(Model model) {
		return "login/studentdashboard";
	}

	@GetMapping("/allUser")
	public String showAllUsers(@ModelAttribute("token") String token, Model model) {
		List<UserInfo> users = userService.allUser();
		model.addAttribute("users", users);
		return "login/alluser";
	}

	@PutMapping("/updateRole")
	public String updateType(@RequestParam String roles, @RequestParam int id, Model model) {
		System.out.println("Received id: " + id + ", roles: " + roles);
		userService.updateRole(id, roles);
		return "redirect:/allUser";
	}

	@PutMapping("/updateStatus/{id}/{status}")
	public String updateLoginStatus(@PathVariable("id") int id, @PathVariable ("status") int status, Model model) {
		System.out.println("hello");
		userService.updateLoginStatus(id, status);
		return "redirect:/alluser";
	}
}
