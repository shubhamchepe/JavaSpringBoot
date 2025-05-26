package com.javaspringboot.JavaSpringBoot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.javaspringboot.JavaSpringBoot.entities.Course;

@Service
public class CourseServiceImpl implements CourseService{

	List<Course> list;
	
	public CourseServiceImpl() {
		list = new ArrayList<>();
		list.add(new Course(1234,"Title 1", "Description of 1"));
		list.add(new Course(1235,"Title 2", "Description of 2"));
	}
	
	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public Course getCourse(long courseId) {
		Course c = null;
		for(Course course : list) {
			if(course.getId() == courseId) {
				c = course;
				break;
			}
		}
		return c;
	}

	@Override
	public Course addCourse(Course course) {
		list.add(course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		list.forEach(e -> {
			if(e.getId() == course.getId()) {
				e.setTitle(course.getTitle());
				e.setDescription(course.getDescription());
			}
		});
		return course;
	}

	@Override
	public void deleteCourse(long parseLong) {
		list=this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
	}
	
	

}
