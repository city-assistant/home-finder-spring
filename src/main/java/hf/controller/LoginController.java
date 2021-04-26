package hf.controller;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hf.domain.Users;
import hf.service.JWTService;
import hf.service.UserService;

import java.util.HashMap;
@Aspect
@CrossOrigin
@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTService jwtService;
	
	@PostMapping(path = "/loginUser")
    public HashMap loginMember(@RequestBody Users user) throws Exception {
		HashMap response = new HashMap<String, String>();
        try {
        	Users loginUser = userService.findByUserId(user.getUserId());
        	if(loginUser != null && loginUser.getUserPw().equals(user.getUserPw())){
        		String token = jwtService.create("userId", loginUser.getUserId(), "user");
        		response.put("userToken", token);
        		        	}
        } catch(Exception e) {
        	throw new Exception();
        }
		return response;
    }
}
