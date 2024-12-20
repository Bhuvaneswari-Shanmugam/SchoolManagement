package com.school.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.management.dto.QuestionDTO;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Question;
import com.school.management.repository.QuestionRepository;
import com.school.management.util.Constants;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private ResponseDTO responseDto;
	
	

	public ResponseDTO createQuestion(Question question) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(questionRepo.save(question));
		responseDto.setStatusCode(200);
		return responseDto;
		
	}
	
	
	// getAllQuestionWithAnswer
	public ResponseDTO getAllQuestion() {
		responseDto.setMessage(Constants.RETRIEVED);
		responseDto.setData(questionRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
		
		
	}
	
   //getAllQuestionOnly
	public ResponseDTO getAllQuestionOnly(Long subjectId) {
		
		 List<Question> question=questionRepo.findAllQuestionBySubjectId(subjectId);
		 List<QuestionDTO> dto=new ArrayList<>();
		 
		 for(Question q:question) {
			 QuestionDTO obj=new QuestionDTO(q.getId(),q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			 dto.add(obj);
		 }
		 responseDto.setMessage(Constants.RETRIEVED);
		 responseDto.setData(dto);
		 responseDto.setStatusCode(200);
		 return responseDto;
		
	}

	public ResponseDTO updateQuestion(Long id,Question question) {
		
		Question obj=questionRepo.findById(id).orElseThrow(null);
		obj.setQuestion(question.getQuestion());
		obj.setOption1(question.getOption1());
		obj.setOption2(question.getOption2());
		obj.setOption3(question.getOption3());
		obj.setOption4(question.getOption4());
		obj.setAnswer(question.getAnswer());
		responseDto.setMessage(Constants.MODIFIED);
		responseDto.setData(questionRepo.save(obj));
		responseDto.setStatusCode(200);
		return responseDto;
		
	}


	public ResponseDTO dropQuestion(Long id) {
		 questionRepo.deleteById(id);
		 responseDto.setMessage(Constants.REMOVED);
		 responseDto.setStatusCode(200);
		 return responseDto;
	}


	
	
	

}
