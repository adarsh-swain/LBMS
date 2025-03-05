package com.lbms.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lbms.entity.UserInfo;
import com.lbms.exception.UserNotFoundException;
import com.lbms.repository.UserRepository;
import com.lbms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String register(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setRoles("ROLE_ADMIN");
		userRepository.save(userInfo);
		return "Register SuccesFully";
	}

	@Override
	public String updateRole(int id, String roles) {
		UserInfo user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID \" + id + \" not found."));
		user.setRoles(roles);
		userRepository.save(user);
		return "Update Successfully";
	}

	@Override
	public List<UserInfo> allUser() {
		List<UserInfo> alldata = userRepository.findAll();
		return alldata.stream().filter(user -> {
			return !"ROLE_ADMIN".equals(user.getRoles());
		}).collect(Collectors.toList());
	}

	@Override
	public String updateLoginStatus(int id, int status) {
		UserInfo user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User with ID \" + id + \" not found."));
		user.setStatus(status);
		userRepository.save(user);
		System.out.println("hello1");
		return "Status updated";
	}

}
