package hf.service;

import hf.domain.UserInterest;
import hf.domain.Users;

import java.util.List;

public interface UserInterestService {
	List<UserInterest> getUserInterestListByUserId(Users UserId);

	void insertUserInterest(UserInterest userInterest);
	void deleteUserInterestsByUserIdAndCity(Users userId, String city);
}
