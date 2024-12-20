
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
import com.school.management.entity.School;
import com.school.management.service.SchoolService;

@RestController
@RequestMapping("/school")
public class SchoolController {
	
	@Autowired
	private SchoolService schoolService;
	
	@PostMapping("/create-school")
	public ResponseDTO createSchool(@RequestBody School school) {
		return schoolService.createSchool(school);
		
	}
	
	@GetMapping("/all-school")
	public ResponseDTO  getAllSchool(){
		return schoolService.getAllSchool();
	}
	
	@GetMapping("/school-by/{id}")
	public ResponseDTO getSchoolById(@PathVariable Long id) {
		return schoolService.getSchoolById(id);
	}
	
	
	
	@PutMapping("/update-school/{id}")
	public ResponseDTO updateSchool(@PathVariable Long id,@RequestBody School school) {
		return schoolService.updateSchool(id,school);
		
	}
	
	@DeleteMapping("/remove-school/{id}")
	public ResponseDTO dropSchool(@PathVariable Long id) {
		return schoolService.dropSchool(id);
		
	}

}
