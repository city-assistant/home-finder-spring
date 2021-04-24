package hf.dao;

import hf.domain.UserInterest;
import hf.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInterestRepository extends JpaRepository<UserInterest, String>{
    List<UserInterest> getUserInterestListByUserId(Users userId);

//    @Query(nativeQuery = true, value = "SELECT * FROM userInterest WHERE CITY")
}
