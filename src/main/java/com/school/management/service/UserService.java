package com.school.management.service;



import java.util.regex.Pattern;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.school.management.config.TokenProvider;
import com.school.management.dto.JwtDto;
import com.school.management.dto.RefreshTokenDTO;
import com.school.management.dto.ResponseDTO;
import com.school.management.dto.SignInDTO;
import com.school.management.dto.SignUpDTO;
import com.school.management.entity.User;
import com.school.management.exception.EmailNotFormatttedException;
import com.school.management.exception.InvaildPasswordException;
import com.school.management.repository.UserRepository;
import com.school.management.util.Constants;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	
	
	@Autowired
	private TokenProvider tokenProvider;
	private  AuthenticationManager authenticationManager;
	
	
	@Autowired
    @Lazy
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
//	@Autowired
//	public UserService(UserRepository userRepo, TokenProvider tokenProvider,
//			AuthenticationManager authenticationManager) {
//		this.userRepo = userRepo;
//		this.tokenProvider = tokenProvider;
//		this.authenticationManager = authenticationManager;
//	}
//	
	

	 
	

	public ResponseDTO SignUp(SignUpDTO signUp) {
	
		if(!emailValidation(signUp.getEmailId())) {
			throw new EmailNotFormatttedException("your mail is not formatted");
		}
			
			if(!passwordValidation(signUp.getPassword())) {
				throw new InvaildPasswordException("Your password is not formatted");
			}
		
		User user=new User();
 		user.setEmailId(signUp.getEmailId());
		user.setPassword(new BCryptPasswordEncoder().encode(signUp.getPassword()));
		user.setRole(signUp.getRole());
		
		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(userRepo.save(user));
		responseDto.setStatusCode(200);
		return responseDto;
	}
		

	
	public ResponseDTO SignIn(SignInDTO signIn) throws AuthenticationException  {
		

		UserDetails user=userRepo.findByEmailId(signIn.getEmailId());
		  
		var userNamePassword=new UsernamePasswordAuthenticationToken(signIn.getEmailId(), signIn.getPassword());
		var authorizedUser= authenticationManager.authenticate(userNamePassword);
		var accessToken=tokenProvider.generateAccessToken((User) authorizedUser.getPrincipal());
		var refreshToken=tokenProvider.generateRefreshToken((User) authorizedUser.getPrincipal());

		ResponseDTO responseDto = new ResponseDTO();
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(new JwtDto(accessToken,refreshToken));
		responseDto.setStatusCode(200);
		return responseDto;
		  	  
		  
      }
	
	
	public ResponseDTO refreshAccessToken(RefreshTokenDTO request) {
		try {
	       	String newAccessToken = tokenProvider.refreshAccessToken(request.getRefreshToken());
	       	String refreshToken = request.setRefreshToken(newAccessToken);
	       	ResponseDTO responseDto= new ResponseDTO();
	       	responseDto.setMessage(Constants.CREATED);
	       	responseDto.setData(refreshToken);
	       	responseDto.setStatusCode(200);
	       	return responseDto;
	       } catch (Exception e) {
	       	ResponseDTO responseDto= new ResponseDTO();
	       	responseDto.setMessage(Constants.NOT_FOUND);
	       	responseDto.setData("Invalid refresh token");
	       	responseDto.setStatusCode(401);
	       	return responseDto;
	       }
	}
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		User user = userRepo.findByEmailId(userName);
	    return user;
	}

	
	
	public boolean emailValidation(String email) {
		return Pattern.compile("^[a-z0-9+_.-]+@(gmail|yahoo|outlook|zoho)\\.com$").matcher(email).matches();
	}
	
	
	private boolean passwordValidation(String password) {
		String pass = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
		return Pattern.compile(pass).matcher(password).matches();
	}



	





	
}
