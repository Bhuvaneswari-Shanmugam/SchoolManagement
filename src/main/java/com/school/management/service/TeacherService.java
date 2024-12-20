package com.school.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Teacher;
import com.school.management.repository.TeacherRepository;
import com.school.management.util.Constants;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepo;
	
	
	@Autowired
	private ResponseDTO responseDto;

	public ResponseDTO createTeacher(Teacher teacher) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(teacherRepo.save(teacher));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	//get all teacher
	public ResponseDTO getAllTeacher() {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(teacherRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//put
	public ResponseDTO updateTeacher(long id, Teacher teacher) {
		Teacher t=teacherRepo.findById(id).orElseThrow(null);
		t.setName(teacher.getName());
		t.setEmailId(teacher.getEmailId());
		t.setSalary(teacher.getSalary());
		t.setSchool(teacher.getSchool());
		t.setRole(teacher.getRole());
		t.setSubject(teacher.getSubject());

		responseDto.setMessage(Constants.MODIFIED);
		responseDto.setData(teacherRepo.save(t));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//delete
	public ResponseDTO dropTeacher(long id) {
		teacherRepo.deleteById(id);
		responseDto.setMessage(Constants.REMOVED);
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//get All teacherBy schoolID
	public ResponseDTO getAllTeacherBySchool(Long schoolId) {
	
		 responseDto.setMessage(Constants.RETRIEVED);
		 responseDto.setData(teacherRepo.findAllBySchoolId(schoolId));
		 responseDto.setStatusCode(200);
		 return responseDto;
	}

	
	//getTeacherById
	public ResponseDTO getTeacherById(long id) {
		 responseDto.setMessage(Constants.RETRIEVED);
		 responseDto.setData(teacherRepo.findById(id));
		 responseDto.setStatusCode(200);
		 return responseDto;
		
	}

	public ResponseDTO SearchTeacher(String keyword) {
		if(keyword!=null) {
			List<Teacher> teacher= teacherRepo.search(keyword);
			responseDto.setMessage(Constants.RETRIEVED);
			responseDto.setData(teacher);
		    responseDto.setStatusCode(200);
			return responseDto;
		}
		else {
			List<Teacher> teacher= teacherRepo.findAll();
			responseDto.setMessage("no keyword found so i give all data");
			responseDto.setData(teacher);
		    responseDto.setStatusCode(200);
			return responseDto;
			
		}
		
	}

	//paging
	public Page<Teacher> getTeacherByPaging(int page, int size, String sortBy, String sortDirection) {
		 Sort.Direction direction = Sort.Direction.fromOptionalString(sortDirection).orElse(Sort.Direction.ASC);
	     Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
	     return teacherRepo.findAll(pageable);
	}

//	//get teacherDetails By userId
//	public ResponseDTO getTeacherDetailsByUserId(Long userId) {
//		
//		User user=userRepo.findById(userId).orElseThrow(null);
//		Long signUpUserId=user.getId();
//		Teacher teacher=teacherRepo.findByUserId(userId);
//		Long teacherUserId=teacher.getUserId();
//		if() {
//			responseDto.setMessage(Constants.RETRIEVED);
//			 responseDto.setData(teacherRepo.findAll());
//			 responseDto.setStatusCode(200);
//			 return responseDto;
//		}
//		responseDto.setStatusCode(500);
//		return responseDto;
//	}




	
}
