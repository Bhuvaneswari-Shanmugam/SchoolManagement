package com.school.management.dto;

import lombok.Data;

@Data
public class AnswerDTO {
	
	private String answer;
	
	private Long questionId;
	
	private Long subjectId;
	
	private Long studentId;

}
