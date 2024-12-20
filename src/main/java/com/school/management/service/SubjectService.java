package com.school.management.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Subject;
import com.school.management.repository.SubjectRepository;
import com.school.management.util.Constants;

@Service
public class SubjectService {
	
	
	@Autowired
	public SubjectRepository subjectRepo;
	
	@Autowired
	private ResponseDTO responseDto;

	public ResponseDTO createSubject(Subject subject) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(subjectRepo.save(subject));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO getAllSubject() {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(subjectRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO updateSubject(Long id, Subject subject) {
		Subject sub=subjectRepo.findById(id).orElseThrow(null);
		sub.setName(subject.getName());
		responseDto.setData(subjectRepo.save(sub));
		responseDto.setMessage(Constants.MODIFIED);
		responseDto.setStatusCode(200);
		return responseDto;
	}

	public ResponseDTO dropSubject(Long id) {
		subjectRepo.deleteById(id);
		responseDto.setMessage(Constants.REMOVED);
		responseDto.setStatusCode(200);
		return responseDto;
	}
}


