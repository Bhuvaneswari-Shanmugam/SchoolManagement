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
import com.school.management.entity.Question;
import com.school.management.service.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	
	@PostMapping("/create-question")
	public ResponseDTO createQuestion(@RequestBody Question question) {
		return questionService.createQuestion(question);

	}

	
	// getAllQuestionWithAnswer
	@GetMapping("/all-question")
	public ResponseDTO getAllQuestion(){
		return questionService.getAllQuestion();
	}
	
	
	
	
	//getAllQuestionOnlyBySubjectId
	@GetMapping("/questionOnly-by-subject/{subjectId}")
	public ResponseDTO getAllQuestionOnly(@PathVariable Long subjectId){
		return questionService.getAllQuestionOnly(subjectId);
	}
	
	
	@PutMapping("/updare-question/{id}")
	public ResponseDTO updateQuestion(@PathVariable Long id,@RequestBody Question question) {
		return questionService.updateQuestion(id,question);
		
	}
	
	
	@DeleteMapping("/remove-question/{id}")
	public ResponseDTO dropQuestion(@PathVariable Long id) {
		 return 
				 questionService.dropQuestion(id);
		 
	}
	
	


}
