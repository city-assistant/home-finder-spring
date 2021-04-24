package hf.util;

import hf.dao.UserInterestRepository;
import hf.domain.UserInterest;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hf.dao.UserRepository;
import hf.domain.Users;

@Aspect
@Configuration
public class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	@Bean
	CommandLineRunner initDatabase1(UserRepository userRepo, UserInterestRepository userInterestRepo) {

		return args -> {
			log.info("Preloading " + userRepo.save(new Users("user1","11")));
			log.info("Preloading " + userInterestRepo.save(UserInterest.builder().userId(Users.builder().userId("user1").build()).city("서울특별시 강서구 마곡동").build()));
			log.info("Preloading " + userInterestRepo.save(UserInterest.builder().userId(Users.builder().userId("user1").build()).city("서울특별시 관악구 봉천동").build()));
		};
	}
}