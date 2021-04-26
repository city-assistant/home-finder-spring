package hf.controller;

import hf.domain.UserInterest;
import hf.domain.Users;
import hf.service.JWTDecoderService;
import hf.service.JWTService;
import hf.service.UserInterestService;
import hf.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@Aspect
public class UserInterestController {

	@Autowired
	private UserInterestService userInterestService;

	@Autowired
	private UserService userService;

	@Autowired
	private JWTService jwtService;
	@Autowired
	private JWTDecoderService jwtDecoderService;

	@ModelAttribute("userInterest")
	public UserInterest setUserInterest() {
		return new UserInterest();
	}


	@PostMapping("/getUserInterestListByUserId")
	public List<UserInterest> getUserInterestListByUserId(@RequestBody HashMap body) throws Exception {
		List<UserInterest> response = null;
		String decodedUserId = jwtDecoderService.decode(body);
		Users userId = userService.findByUserId(decodedUserId);
		if (userId == null) {
			return null;
		} else {
			response = userInterestService.getUserInterestListByUserId(userId);
		}
		return response;
	}

	@PostMapping("/insertUserInterest")
	public String insertUserInterest(@RequestBody HashMap body) throws Exception {
		System.out.println(body);
		String response = null;
		String decodedUserId = jwtDecoderService.decode(body);
		Users userId = userService.findByUserId(decodedUserId);
		if (userId.getUserId() == null) {
			return "redirect:login";
		} else {
			UserInterest insertUserInterest = UserInterest.builder().userId(userId).city(body.get("city").toString()).build();
			userInterestService.insertUserInterest(insertUserInterest);
			response = "save complete";
		}
		return response;
	}

	@PostMapping("/deleteUserInterest")
	public String deleteUserInterestsByUserIdAndCity(@RequestBody HashMap body) throws Exception {
		String decodedUserId = jwtDecoderService.decode(body);
		Users userId = userService.findByUserId(decodedUserId);
		String response = null;

		if (userId.getUserId() == null) {
			return "redirect:login";
		} else {
			userInterestService.deleteUserInterestsByUserIdAndCity(userId, body.get("city").toString());
			response = "delete complete";
		}
		return response;
	}
}
