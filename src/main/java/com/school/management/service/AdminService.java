package com.school.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Admin;
import com.school.management.repository.AdminRepository;
import com.school.management.util.Constants;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;

	public ResponseDTO createAdmin(Admin admin) {
		ResponseDTO responseDto=new ResponseDTO();
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(adminRepo.save(admin));
		responseDto.setStatusCode(200);
		return responseDto;
		}

	public ResponseDTO getAdminById(Long id) {
		ResponseDTO responseDto=new ResponseDTO();
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(adminRepo.findById(id));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO getAllAdmin() {

		ResponseDTO responseDto=new ResponseDTO();
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(adminRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
	}
}