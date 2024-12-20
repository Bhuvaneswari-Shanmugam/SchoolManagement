package com.school.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.management.entity.Mark;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long>{

}
