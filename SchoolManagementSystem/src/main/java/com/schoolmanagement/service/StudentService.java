package com.schoolmanagement.service;

import java.util.ArrayList;

import com.schoolmanagement.model.StudentDetails;

public interface StudentService {
	
	public StudentDetails getStudentDetails(Long id);
	public StudentDetails addStudentInDataBase(StudentDetails sd);
	public ArrayList<StudentDetails> getAllStudentDetails();
}
