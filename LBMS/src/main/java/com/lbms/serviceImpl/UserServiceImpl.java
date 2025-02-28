package com.lbms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.entity.UserInfo;
import com.lbms.repository.UserRepository;
import com.lbms.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String register(UserInfo userInfo) {
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setRoles("ROLE_ADMIN");
		userRepository.save(userInfo);
		return "Register SuccesFully";
	}
	

}
