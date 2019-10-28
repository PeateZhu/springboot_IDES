package com.zbj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zbj.entity.Student;

public interface StudentDao extends JpaRepository<Student, Integer>,JpaSpecificationExecutor<Student>{

}
