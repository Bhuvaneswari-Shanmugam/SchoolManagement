package com.school.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Admin;
import com.school.management.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired 
	private AdminService adminService;

	@PostMapping("/create-admin")
	public ResponseDTO createAdmin(@RequestBody Admin admin) {
		return adminService.createAdmin(admin);
	}
	
	
	@GetMapping("/all-admin")
	public ResponseDTO getAllAdmin() {
		return adminService.getAllAdmin();
	}
	
	@GetMapping("/get-admin/{id}")
	public ResponseDTO getAdminById(@PathVariable Long id) {
		return adminService.getAdminById(id);
	}
}
