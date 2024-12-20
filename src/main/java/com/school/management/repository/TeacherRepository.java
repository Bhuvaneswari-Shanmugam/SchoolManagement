package com.school.management.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.school.management.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{

	List<Teacher> findAllBySchoolId(Long schoolId);

	

	Teacher findByEmailId(String emailId);



	boolean existsByEmailId(String emailId);


	@Query("SELECT t FROM Teacher t WHERE " +
	           "t.name LIKE %:keyword% OR " +
	           "t.emailId LIKE %:keyword% OR " +
	           "t.subject.name LIKE %:keyword% OR " +
	           "t.school.name LIKE %:keyword%")
	List<Teacher> search(String keyword);









	

}

