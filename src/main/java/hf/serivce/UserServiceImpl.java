package hf.serivce;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import hf.dao.UserRepository;
import hf.domain.Users;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepo;
	
	//Create
	public void insertUser(Users user) {
		userRepo.save(user);
	}

	//Read
	public Users getUser(Users user) {
		Optional<Users> findUser = userRepo.findById(user.getUserId());
		if (findUser.isPresent()) {
			return findUser.get();
		}else {
			return null;
		}
	}
	
	public Users signIn(String id, String pw) {
		Users user = userRepo.findByUserId(id);
		return user;
	}

	public Users findByUserId(String userId) {
		return userRepo.findByUserId(userId);
	}
	
	//Update
	public void updateUser(Users user) {
		Users findUser = userRepo.findById(user.getUserId()).get();
		findUser.setUserPw(user.getUserPw());
		userRepo.save(findUser);
	}
	
	//Delete
	public void deleteUser(Users user) {
		userRepo.deleteById(user.getUserId());
	}
}
