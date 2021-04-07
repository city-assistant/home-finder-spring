package hf.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hf.domain.Users;

public interface UserRepository extends JpaRepository<Users, String>{
	Users findByUserId(String userId);
}
