package hf.controller;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hf.domain.Users;
import hf.service.UserService;
@CrossOrigin
@RestController
@Aspect
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public Users setUser() {
		return new Users();
	}

	@PostMapping("/insertUser")
	public String insertUser(@RequestBody Users user) throws Exception {
		if (user.getUserId() == null) {
			return "redirect:login";
		}
		userService.insertUser(user);

		String response = null;
		try {
//			String token = jwtService.create(user.getUserId(), user, "user");
//			response = token;
		} catch (Exception e) {
			throw new Exception();
		}
		return response;
	}

	@PostMapping("/updateUserToken")
	public void updateUserToken(@RequestBody Users user) {
		Users updateUser = userService.findByUserId(user.getUserId());
//		updateUser.setKakaoToken(user.getKakaoToken());
		System.out.println(updateUser);
//		userService.updateUserToken(updateUser);
	}
}
