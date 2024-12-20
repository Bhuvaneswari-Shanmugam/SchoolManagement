package com.school.management.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	
	private String message;
	
	private Object data;
	
	private Integer statusCode;

}
