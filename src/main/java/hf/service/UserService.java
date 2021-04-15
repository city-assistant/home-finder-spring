package hf.service;

import java.util.List;

import hf.domain.Users;

public interface UserService {
	
	Users getUser(Users user);
	
	Users signIn(String id, String pw);
	
	Users findByUserId(String userId);
	
	List<Users> getUserList(Users user);
	
	void insertUser(Users user);

	void updateUser(Users user);

}
