package com.school.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.dto.ResponseDTO;
import com.school.management.entity.School;
import com.school.management.repository.SchoolRepository;
import com.school.management.util.Constants;

@Service
public class SchoolService {
	
	@Autowired
	private SchoolRepository schoolRepo;
	
	@Autowired
	private ResponseDTO responseDto;

	public ResponseDTO getAllSchool() {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(schoolRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
	}
	
	public ResponseDTO getSchoolById(Long id) {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(schoolRepo.findById(id));
		responseDto.setStatusCode(200);
		return responseDto;
	}
	
	

	public ResponseDTO createSchool(School school) {
		 responseDto.setMessage(Constants.CREATED);
		 responseDto.setData(schoolRepo.save(school));
		 responseDto.setStatusCode(200);
		 return responseDto;
	}

	
	public ResponseDTO updateSchool(Long id, School school) {
		School sc=schoolRepo.findById(id).orElseThrow(null);
		sc.setName(school.getName());
		sc.setAddress(school.getAddress());
		responseDto.setMessage(Constants.MODIFIED);
		responseDto.setData(schoolRepo.save(sc));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO dropSchool(Long id) {
		schoolRepo.deleteById(id);
		responseDto.setMessage(Constants.REMOVED);
		responseDto.setStatusCode(200);
		return responseDto;
	}
}
	