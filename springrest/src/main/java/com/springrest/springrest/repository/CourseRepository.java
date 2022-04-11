package com.springrest.springrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springrest.springrest.entities.Course;

@Repository
//@EnableJpaRepositories
public interface CourseRepository extends JpaRepository<Course, Long> , CrudRepository<Course, Long>{
	
//	@Query("SELECT c FROM Course c WHERE c.email LIKE '%gmail.com'")
//	public List<Course> search( );
	
	public List<Course> findByEmailEndingWith(String mailDomain);
	
}
