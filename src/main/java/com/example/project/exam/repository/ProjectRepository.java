package com.example.project.exam.repository;

import com.example.project.exam.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
