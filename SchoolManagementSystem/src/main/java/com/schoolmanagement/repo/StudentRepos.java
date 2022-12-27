package com.schoolmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;import com.schoolmanagement.model.StudentDetails;

public interface StudentRepos extends JpaRepository<StudentDetails, Long> {
	
	

}
