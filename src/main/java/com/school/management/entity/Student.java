
package com.school.management.entity;


import com.school.management.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {
	
	
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String emailId;
	private String standard;
	private Long fee;
	
	@OneToOne
	private User userId;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@ManyToOne
	private School school;
	
	
	

	


}
