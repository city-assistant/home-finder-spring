package hf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hf.domain.UserInterest;
import hf.domain.Users;
import hf.service.JWTDecoderService;
import hf.service.JWTService;
import hf.service.UserInterestService;
import hf.service.UserService;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.catalina.User;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
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
	public String insertUserInterest(@RequestBody UserInterest userInterest) throws Exception {
		String response = null;
		if (userInterest.getUserId() == null) {
			return "redirect:login";
		} else {
			userInterestService.insertUserInterest(userInterest);
			response = "save complete";
		}
		return response;
	}

	@PostMapping("/deleteUserInterest")
	public String deleteUserInterest(@RequestBody HashMap body) throws Exception {
		String decodedUserId = jwtDecoderService.decode(body);
		Users userId = userService.findByUserId(decodedUserId);
		UserInterest userInterest = UserInterest.builder().userId(userId).city(body.get("city").toString()).build();
		String response = null;

		if (userInterest.getUserId() == null) {
			return "redirect:login";
		} else {
			userInterestService.deleteUserInterest(userInterest);
			response = "delete complete";
		}
		return response;
	}
}
