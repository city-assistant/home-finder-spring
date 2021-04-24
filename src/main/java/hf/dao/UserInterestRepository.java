package hf.dao;

import hf.domain.UserInterest;
import hf.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, String>{
    List<UserInterest> getUserInterestListByUserId(Users userId);
    @Transactional
    void deleteUserInterestsByUserIdAndCity(Users userId, String city);
}
