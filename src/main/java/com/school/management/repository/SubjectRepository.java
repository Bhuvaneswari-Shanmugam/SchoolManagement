package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long>{

}
