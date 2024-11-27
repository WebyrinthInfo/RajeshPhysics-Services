package com.RajeshPhysics_Services.ServiceImps;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RajeshPhysics_Services.Dtos.CourseDto;
import com.RajeshPhysics_Services.Dtos.RoleDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Models.Role;
import com.RajeshPhysics_Services.Repositories.CourseRepository;
import com.RajeshPhysics_Services.Services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	public static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	 @Override
	    public CourseDto addCourse(CourseDto courseDto) {
	        // Check if the role already exists
	        Optional<Course> existingRole = courseRepo.findByName(courseDto.getName());
	        if (existingRole.isPresent()) {
	            logger.warn("Attempt to add existing course: {}", courseDto.getName());
	            throw new ResourceAlreadyExistsException("Role '" + courseDto.getName() + "' already exists.");
	        }
	        
	        Course course = modelMapper.map(courseDto, Course.class);
	        course.setName(courseDto.getName().trim().toUpperCase());
	        course.setCourseTitle(courseDto.getCourseTitle().trim());
	        course.setCourseLanguage(courseDto.getCourseLanguage().trim());
	        course.setCourseDescription(courseDto.getCourseDescription().trim());
	        course.setCoursePrice(courseDto.getCoursePrice().trim());

	        
	        Course savedRole = courseRepo.save(course);
	        logger.info("Couse added successfully: {}", savedRole.getName());

	        // Convert saved entity back to DTO and return it
	        return modelMapper.map(savedRole, CourseDto.class);
	    }
	
	

}
