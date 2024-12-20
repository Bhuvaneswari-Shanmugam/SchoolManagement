package com.school.management.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.RefreshTokenDTO;
import com.school.management.dto.ResponseDTO;
import com.school.management.dto.SignInDTO;
import com.school.management.dto.SignUpDTO;
import com.school.management.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/sign-up")
	public ResponseDTO SignUp(@RequestBody SignUpDTO signUp) {
		return userService.SignUp(signUp);
	}
	
	
	
	@PostMapping("/sign-in")
	public ResponseDTO SignIn(@RequestBody  SignInDTO signIn) throws AuthenticationException {
		return userService.SignIn(signIn);
	}

	
	@PostMapping("/refresh")
	 public ResponseDTO refreshAccessToken(@RequestBody RefreshTokenDTO request) {
		return userService.refreshAccessToken(request);
	}
	
	
	
	
}



