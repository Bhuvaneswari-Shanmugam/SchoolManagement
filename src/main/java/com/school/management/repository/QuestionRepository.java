package com.school.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.school.management.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>{

	List<Question> findAllQuestionBySubjectId(Long subjectId);

	List<Question> findBySubjectId(Long subjectId);

}

