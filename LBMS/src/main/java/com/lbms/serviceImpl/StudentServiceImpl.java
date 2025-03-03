package com.lbms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lbms.entity.Book;
import com.lbms.entity.UserInfo;
import com.lbms.repository.UserRepository;
import com.lbms.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String studentRegister(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode("lbms@1"));
//		userInfo.setPassword("lbms@1");
		userInfo.setRoles("ROLE_STUDENT");
		userInfo.setStatus(1);
		userRepository.save(userInfo);
		return "Register SuccesFully";
	}
	
	@Override
	public List allStudent() {
		List allstudents = userRepository.findByRole("ROLE_STUDENT");
		System.out.println(allstudents+"dsnj");
		return allstudents;
	}

	@Override
	public void deleteStudentbyId(int userid) {
		userRepository.deleteById(userid);
		
	}

	@Override
	public UserInfo updateStudentById(int userid, UserInfo user) {
	Optional<UserInfo> userdetails = userRepository.findById(userid);
	if(userdetails.isPresent()) {
		UserInfo userInfo = userdetails.get();
		userInfo.setFname(user.getFname());
		userInfo.setMname(user.getMname());
		userInfo.setLname(user.getLname());
		userInfo.setEmail(user.getEmail());
		userInfo.setMobile(user.getMobile());
		return userRepository.save(userInfo);
	}else {
		return null;
	}
		
	}

	@Override
	public Optional<UserInfo> editStudentById(int userid) {
		 Optional<UserInfo> studentDetails = userRepository.findById(userid);
		return studentDetails;
	}

}
