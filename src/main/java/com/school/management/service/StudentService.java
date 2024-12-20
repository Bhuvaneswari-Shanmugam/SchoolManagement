package com.school.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Student;
import com.school.management.repository.StudentRepository;
import com.school.management.util.Constants;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private ResponseDTO responseDto;

	public ResponseDTO createStudent(Student student) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(studentRepo.save(student));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO getAllStudent() {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(studentRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
		
	}

	public ResponseDTO updateStudent(Long id, Student student) {
		
		Student obj=studentRepo.findById(id).orElseThrow(null);
		
		obj.setName(student.getName());
		obj.setEmailId(student.getEmailId());
		obj.setStandard(student.getStandard());
		obj.setFee(student.getFee());
		obj.setUserId(student.getUserId());
		obj.setSchool(student.getSchool());
		obj.setRole(student.getRole());
		
		responseDto.setMessage(Constants.MODIFIED);
		responseDto.setData(studentRepo.save(obj));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO dropStudent(Long id) {
		studentRepo.deleteById(id);
		responseDto.setMessage(Constants.REMOVED);
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO getAllStudentByStandard(String standard) {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(studentRepo.findAllByStandard(standard));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//paging
	public Page<Student> getStudentByPage(int page, int size) {
		Pageable pageable=PageRequest.of(page,size);
		
		return studentRepo.findAllBy(pageable);
	}

	
	//searching
	public ResponseDTO SearchStudent(String keyword) {
		if(keyword!=null) {
			List<Student> student=studentRepo.Search(keyword);
			responseDto.setMessage(Constants.RETRIEVED);
			responseDto.setData(student);
			responseDto.setStatusCode(200);
			return responseDto;
		}
		else {
			List<Student> allStudents= studentRepo.findAll();
			responseDto.setMessage(Constants.RETRIEVED);
			responseDto.setData(allStudents);
			responseDto.setStatusCode(200);
			return responseDto;
		}
		
	
	}

	
}
