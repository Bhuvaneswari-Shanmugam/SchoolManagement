package com.school.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Subject;
import com.school.management.service.SubjectService;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	
	
	@PostMapping("/create-subject")
	public ResponseDTO createSubject(@RequestBody Subject subject) {
		 return subjectService.createSubject(subject);
		 
	}

	@GetMapping("/all-subject")
	public ResponseDTO getAllSubject(){
		return subjectService.getAllSubject();
	}
	
	@PutMapping("/update-subject/{id}")
	public ResponseDTO updateSubject(@PathVariable Long id,@RequestBody Subject subject) {
		return subjectService.updateSubject(id,subject);
		
	}
	
	@DeleteMapping("/remove-subject/{id}")
	public ResponseDTO dropSubject(@PathVariable Long  id) {
		return subjectService.dropSubject(id);
	}

}
