package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.entities.Course;

public interface CourseService {
	public List<Course> getCourses();
	public Course getCourse(long courseId);
	public Course addCourse(Course course);
//	public void updateCourse(Course course, long courseId);
	public Course updateCourse(Course course, long courseId);
	public void deleteCourse(long courseId);
	
	public boolean isMailRegistered(String mail);
	public boolean isValid(String mail);
	
//	// find mail
//	public List<Course> findMail( );
	public List<Course> findMain(String mailDomain);
}
