package com.school.management.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.school.management.dto.AnswerDTO;
import com.school.management.dto.ResponseDTO;
import com.school.management.entity.Answer;
import com.school.management.entity.Question;
import com.school.management.entity.Student;
import com.school.management.entity.Subject;
import com.school.management.repository.AnswerRepository;
import com.school.management.repository.QuestionRepository;
import com.school.management.repository.StudentRepository;
import com.school.management.repository.SubjectRepository;
import com.school.management.util.Constants;


@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepo;
	
	@Autowired
	private QuestionRepository questionRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private ResponseDTO responseDto;

	
	//post
	public ResponseDTO createAnswer(List<AnswerDTO> answerDto) {
	
		Student student=studentRepo.findById(answerDto.get(0).getStudentId()).orElseThrow(null);
		Subject subject=subjectRepo.findById( answerDto.get(0).getSubjectId()).orElseThrow(null);
		List<Answer> answer=new ArrayList<>();
		for(AnswerDTO a:answerDto)
		{
			Answer ans=new Answer();	
			Question question=questionRepo.findById(a.getQuestionId()).orElseThrow();
			
			ans.setAnswer(a.getAnswer());
			ans.setQuestion(question);	
			ans.setSubject(subject);
			ans.setStudent(student);
			
			answer.add(ans);	
		}

		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(answerRepo.saveAll(answer));
		responseDto.setStatusCode(200);
		return responseDto;
	}
	

	//get all answer for student
	public ResponseDTO getAllAnswer() {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(answerRepo.findAll());
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//get all answer by studentID
	public ResponseDTO getAllAnswerByStudentId(Long studentId) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData(answerRepo.findByStudentId(studentId));
		responseDto.setStatusCode(200);
		return responseDto;
	}


	//get all answer by studentID and subjectID
	public ResponseDTO getAllAnswerByStudentIdAndSubjectId(Long studentId, Long subjectId) {
		responseDto.setMessage(Constants.CREATED);
		responseDto.setData( answerRepo.findByStudentIdAndSubjectId(studentId,subjectId));
		responseDto.setStatusCode(200);
		return responseDto;
	}

	
	//get all correct answer only  by studentID and subjectID
	public Integer getMarkBySubjectId(Long studentId, Long subjectId) {
		List<Answer>answer=answerRepo.findByStudentIdAndSubjectId(studentId,subjectId) ;
		List<Question> question=questionRepo.findBySubjectId(subjectId);
		
		Integer totalCount=0;
		for(Answer a: answer) {
			for(Question q: question) {
				if(a.getQuestion().getId().equals(q.getId()) && a.getAnswer().equals(q.getAnswer()))
				{
					totalCount++;	
					break;
				}
			}	
		}
		return totalCount*5;
	}


	//get Total MarkBy studentID
	public Integer getTotalMarkByStudentId(Long studentId) {
		List<Answer> answer= answerRepo.findAllByStudentId(studentId);
		List<Question> question=questionRepo.findAll();
		Integer totalCount=0;
		for(Answer a: answer) {
			for(Question q:question) {
				if(a.getSubject().getId().equals(q.getSubject().getId())  && a.getQuestion().getId().equals(q.getId()) &&  a.getAnswer().equals(q.getAnswer())) {
					totalCount++;
					break;}
			}
		}
		return totalCount * 5;
	}
	
	

}
