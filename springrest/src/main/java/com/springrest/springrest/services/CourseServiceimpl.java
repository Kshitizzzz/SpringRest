package com.springrest.springrest.services;


import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.repository.CourseRepository;



// Created this class for Loose Coupling

@Service
public class CourseServiceimpl implements CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	
	List<Course> list;

	public CourseServiceimpl() {

		list = new ArrayList<>();
		list.add(new Course("Java Course","john123@gmail.com"));
		list.add(new Course("Spring Boot Course","rohan33@example.com"));
	}


	public boolean isValid(String email) {
		String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(regex);
	}

	public boolean isMailRegistered(String mail) {
		for(Course c: list) {
			if(c.getEmail().equals(mail)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Course> getCourses(){
//		return list;
		return this.courseRepository.findAll();
	}

	@Override
	public Course getCourse(long courseId) {
//		Course c = null;
//		try {
//			c = list.stream().filter(e->e.getId()==courseId).findFirst().get();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return c;
		return this.courseRepository.findById(courseId).get();
	}

	@Override
	public Course addCourse(Course course) {
		// validation before adding;
		String mail = course.getEmail();
		if(!isValid(mail)) {
			return null ; 
		}
		if(isMailRegistered(mail)) {
			return null;
		}

//		list.add(course);
//		return course;				//
		return this.courseRepository.save(course);
	}

	@Override  // void
	public Course updateCourse(Course course,long courseId) {
//		list.stream().map(c->{
//			if(c.getId()==courseId) {
//				c.setUserName(course.getUserName());
//				c.setEmail(course.getEmail());
//			}
//			return c;
//		}).collect(Collectors.toList());
		return this.courseRepository.save(course);
	}

	@Override
	public void deleteCourse(long courseId) {
//		try {
//			list = this.list.stream().filter(e -> e.getId()!=courseId).collect(Collectors.toList());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
		
		Course course = new Course();
		course.setId(courseId);
		this.courseRepository.delete(course);
	}


	@Override
	public List<Course> findMain(String mailDomain) {
//		String temp="";
		mailDomain=mailDomain+".com";
		System.out.println("The domain is "+mailDomain);
		return this.courseRepository.findByEmailEndingWith(mailDomain);
		
//		return null;
	}


//	@Override
//	public List<Course> findMail() {
//		
//		return (List<Course>) this.courseRepository.search();
//	}


}
