package com.lbms.service;

import java.util.List;

import com.lbms.entity.UserInfo;

public interface UserService {

	public String register(UserInfo userInfo);

	public String updateRole(int id, String roles);

	public List<UserInfo> allUser();

	public String updateLoginStatus(int id, int status);
	
//	public boolean updateUserPassword(String email, String newPassword);

}
