package com.school.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="mark")
public class Mark {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;    
	

	private Long mark;
	
	@ManyToOne
	private Student student;
	
	@ManyToOne
	private School school;
	

	
}
