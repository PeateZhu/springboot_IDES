package com.zbj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zbj.dao.ClassesDao;
import com.zbj.dao.StudentDao;
import com.zbj.entity.Classes;
import com.zbj.entity.Student;

 

@RestController
public class StudentController {
	
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private ClassesDao classDao;
	@PostMapping("/studetList")
	public Map<String, Object> studetList(Integer page,Integer rows, String stuName){
		
		Pageable pageable=new PageRequest(page-1, rows);
		
		Page<Student> findAll = studentDao.findAll(get(stuName),pageable);
		List<Student> content = findAll.getContent();
		long totalElements = findAll.getTotalElements();
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("total", totalElements);
		map.put("rows", content);
		return map;
	}
	
	
	private Specification<Student> get(final String stuName){
		
		return new Specification<Student>() {
			
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				Predicate conjunction = cb.conjunction();
				List<Expression<Boolean>> expressions = conjunction.getExpressions();
				if(!StringUtils.isEmpty(stuName)) {
					expressions.add(cb.like(root.<String>get("stuName"),'%'+stuName+'%'));
				}
				return conjunction;
			}
		};
	}
	//查询所有班级
	@PostMapping("/findClasses")
	public List<Classes> findClasses(){
		return classDao.findAll();
	}
	//新增学生和修改
	@PostMapping("/insertStudent")
	public Student insertStudent(Student student,Classes classes) {
		student.setClasses(classes);
		return studentDao.saveAndFlush(student);
	}
	//删除
	@PostMapping("/delect")
	public Integer delStudent(Integer stuId) {
		try {
			studentDao.delete(stuId);
			return 1;
		}catch(Exception e){
			return 0;
		}
	}

}
