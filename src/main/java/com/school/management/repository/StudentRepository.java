package com.school.management.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.school.management.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	List<Student> findAllByStandard(String Standard);



	Student findByEmailId(String emailId);



	boolean existsByEmailId(String emailId);



	Page<Student> findAllBy(Pageable pageable);


	  @Query("SELECT s FROM Student s WHERE " +
	           "s.name LIKE %:keyword% OR " +
	           "s.emailId LIKE %:keyword% OR " +
	           "s.standard LIKE %:keyword% OR " +
	           "s.school.name LIKE %:keyword%")
	List<Student> Search(String keyword);



	

}

