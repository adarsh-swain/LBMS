package com.lbms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbms.entity.UserInfo;
import com.lbms.repository.UserRepository;
import com.lbms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	UserRepository userRepository;

	@Override
	public String studentRegister(UserInfo userInfo) {
//		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setPassword("lbms@1");
		userInfo.setRoles("ROLE_STUDENT");
		userInfo.setStatus(1);
		userRepository.save(userInfo);
		return "Register SuccesFully";
	}
}
