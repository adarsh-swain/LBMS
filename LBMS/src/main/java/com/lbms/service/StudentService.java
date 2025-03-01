package com.lbms.service;

import java.util.List;
import java.util.Optional;

import com.lbms.entity.Book;
import com.lbms.entity.UserInfo;

public interface StudentService {

	public String studentRegister(UserInfo userInfo);
	
	public List allStudent();
	
	public void deleteStudentbyId(int userid);
	
	public UserInfo updateStudentById(int userid, UserInfo user);

	public Optional<UserInfo> editStudentById(int userid);
}
