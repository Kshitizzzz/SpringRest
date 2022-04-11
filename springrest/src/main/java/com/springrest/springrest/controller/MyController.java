package com.springrest.springrest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

@RestController
@RequestMapping("/api")
public class MyController {

	@Autowired
	private CourseService courseService;

//	@GetMapping("/test")
//	public String test() {
//		return "Hello from API";
//	}

	// Get the courses
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses() {
		List<Course> list = this.courseService.getCourses();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list)); // By default status is OK
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
		Course course = this.courseService.getCourse(Long.parseLong(courseId));
		if (course == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(course));
	}

	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) { // because course is coming from request's
																			// body

		Course c = null;

		try {
			c = this.courseService.addCourse(course);
			if (c == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/courses/{courseId}")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, @PathVariable("courseId") long courseId) {
		try {
			this.courseService.updateCourse(course, courseId);
			return ResponseEntity.ok().body(course);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/courses/{courseId}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable("courseId") String courseId) {
		try {
			this.courseService.deleteCourse(Long.parseLong(courseId));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
//	@GetMapping("/courses/mail") 
//	public ResponseEntity<?> getMails(){
//		return ResponseEntity.ok(this.courseService.findMail());
//	}
	
	@GetMapping("/{domain}") 
	public ResponseEntity<List<Course>> getMails(@PathVariable("domain") String domain){
		return ResponseEntity.ok(this.courseService.findMain(domain));
//		return ResponseEntity.ok(this.courseService.findMail());
	}
}
