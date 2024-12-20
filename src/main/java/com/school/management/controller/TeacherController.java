package com.school.management.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Teacher;
import com.school.management.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	
	@PostMapping("/create-teacher")
	public ResponseDTO createTeacher(@RequestBody Teacher teacher) {
		return teacherService.createTeacher(teacher);
		
	}
	
	
	//get all teacher
	@GetMapping("/all-teacher")
	public ResponseDTO getAllTeacher(){
		return teacherService.getAllTeacher();
	}
	
	
	//get teacher by teacherId
	@GetMapping("teacher-by/{id}")
	public ResponseDTO getTeacherById(@PathVariable long id) {
		return teacherService.getTeacherById(id);
	}
	
	
	

	
	//get All teacherBy schoolID
	@GetMapping("/all-teacher-by-school/{schoolId}")
	public ResponseDTO getAllTeacherBySChool(@PathVariable Long schoolId){
		return teacherService.getAllTeacherBySchool(schoolId);
	}
	
	@PutMapping("/update-teacher/{id}")
	public ResponseDTO updateTeacher(@PathVariable long id,@RequestBody Teacher teacher) {
		return teacherService.updateTeacher(id,teacher);
		
	}
	
	
	@DeleteMapping("/remove-teacher/{id}")
	public ResponseDTO dropTeacher(@PathVariable long id) {
	     return teacherService.dropTeacher(id);
	}
	
	
	//searching
	@GetMapping("/search/{keyword}")
	public ResponseDTO SearchTeacher(@PathVariable String keyword) {
		return teacherService.SearchTeacher(keyword);
		
	}


	//paging 
	@GetMapping("/page")
	public Page<Teacher> getTeacherByPaging(@RequestParam int page,@RequestParam int size,@RequestParam String sortBy,@RequestParam String sortDirection){
		return teacherService.getTeacherByPaging(page,size,sortBy,sortDirection);
	}
		
}


