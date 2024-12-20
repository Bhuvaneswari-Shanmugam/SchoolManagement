package com.school.management.enums;


public enum Role {

	ADMIN("admin"),TEACHER("teacher"),STUDENT("student");
	
	private String role;

	private Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
