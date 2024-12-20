package com.school.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.management.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

	List<Answer> findByStudentId(Long studentId);

	List<Answer> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

	List<Answer> findAllByStudentId(Long studentId);


	

}

