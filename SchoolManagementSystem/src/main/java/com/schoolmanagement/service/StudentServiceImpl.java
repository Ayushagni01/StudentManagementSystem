package com.schoolmanagement.service;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.schoolmanagement.model.StudentDetails;
import com.schoolmanagement.repo.StudentRepos;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepos sr;
	
	@Override
	public StudentDetails getStudentDetails(Long id) {
		StudentDetails sd=	this.sr.findById(id).orElse(null);
		return sd;
	}

	@Transactional
	@Override
	public StudentDetails addStudentInDataBase(StudentDetails sd) {
		return this.sr.save(sd);
		
	}

	@Override
	public ArrayList<StudentDetails> getAllStudentDetails() {
		List<StudentDetails> sdd=new ArrayList<>();
		sdd=this.sr.findAll();
		return (ArrayList<StudentDetails>) sdd;
		
	}

}
