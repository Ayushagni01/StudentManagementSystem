package com.schoolmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolmanagement.model.StudentDetails;
import com.schoolmanagement.service.StudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin("*")
public class StudentController {

	String path="image/";
	
	
	@Autowired
	private StudentService ss;
	@Autowired
	private ObjectMapper obm;
	
	//used to get the Student from the database based on the student id
	@GetMapping("/details/{id}")
	public StudentDetails getDetails(@PathVariable("id") Long id) {
		
		StudentDetails sd=	this.ss.getStudentDetails(id);
		return sd ;
	}
	
	
	
	//get all the students
	@GetMapping("/details/")
	public List<StudentDetails> getDetails() {
		
		ArrayList<StudentDetails> sd=	this.ss.getAllStudentDetails();
		System.out.println(sd);
		return sd ;
	}
	
	// How to upload the images and the json data from the front end 
	@PostMapping("/files1")
	public StudentDetails  fileStudents(
			@RequestParam("image") MultipartFile image,
			@RequestParam("json") String json) throws IOException
	{
		
		StudentDetails sd=	obm.readValue(json,StudentDetails.class);
		//System.out.println("ayush agnihotri is"+sd.getEmail());
		//String fileName= uploadImage(path, image);
		
	//	sd.setImageName(fileName);
		return	sd;
	
		
	}
	
	@PostMapping("/files")
	public String  fileStudents(
			@RequestParam("image") MultipartFile image,
			@ModelAttribute StudentDetails sd) throws IOException
	{
		System.out.println("ayush agnihotri is"+sd.getEmail());
		String fileName= uploadImage(path, image);
		
		sd.setImageName(fileName);
		return	"ayushagnihotri";
	
		
	}
	
	
	@PostMapping("/file")
	public StudentDetails fileStudent(
			@RequestParam("image") MultipartFile image,
			@ModelAttribute StudentDetails sd) throws IOException
	{
		System.out.println("ayush agnihotri is"+sd.getEmail());
		String fileName= uploadImage(path, image);
		
		sd.setImageName(fileName);
		return	this.ss.addStudentInDataBase(sd);
	
		
	}
	
	
	
	
	@PostMapping("/stu")
	public StudentDetails addStudent(@RequestBody StudentDetails sd,
			@RequestParam("image") MultipartFile image) throws IOException
	{
		
		String fileName= uploadImage(path, image);
		sd.setImageName(fileName);
		return	this.ss.addStudentInDataBase(sd);
	}
	
	
	
	
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		//getting file name 
		String name=file.getOriginalFilename();
		
		//random name generate file
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filepath=path+File.separator+fileName1;
		
		//create folder if not created
		File f=new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(filepath));
		return fileName1;
		
		
	}
	
	
	
		
	
}



//localhost:8080/student/details