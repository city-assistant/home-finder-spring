package hf.service;

import hf.dao.UserInterestRepository;
import hf.dao.UserRepository;
import hf.domain.UserInterest;
import hf.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInterestServiceImpl implements UserInterestService{
	@Autowired
	UserInterestRepository userInterestRepos;

	@Override
	public List<UserInterest> getUserInterestListByUserId(Users userId) {
		Optional<List<UserInterest>> findUserInterest = Optional.ofNullable(userInterestRepos.getUserInterestListByUserId(userId));
		if (findUserInterest.isPresent()) {
			return findUserInterest.get();
		} else {
			return null;
		}
	}

	@Override
	public void insertUserInterest(UserInterest userInterest) {
		userInterestRepos.save(userInterest);
	}

	@Override
	public void deleteUserInterestsByUserIdAndCity(Users userId, String city) {
		userInterestRepos.deleteUserInterestsByUserIdAndCity(userId, city);
	}
}
