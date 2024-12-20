package com.school.management.dto;

import com.school.management.enums.Role;

import lombok.Data;

@Data
public class SignUpDTO {
	
	private String emailId;
	private String password;
	private Role role;

}
