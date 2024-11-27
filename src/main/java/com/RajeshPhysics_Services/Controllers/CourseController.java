package com.RajeshPhysics_Services.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RajeshPhysics_Services.Dtos.CourseDto;
import com.RajeshPhysics_Services.Dtos.RoleDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Services.CourseService;

@RestController
@RequestMapping("/api/course")
@CrossOrigin("*")
public class CourseController {
	public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	
	@Autowired
	private CourseService courseServe;
	
//	---------------- add course--------------
	 @PostMapping("/add")
	 public ResponseEntity<GenericResponse<CourseDto>> addCourse(@RequestBody CourseDto courseDto, GenericResponse<CourseDto> response) {
		 try {
	            CourseDto createdRole = courseServe.addCourse(courseDto);
	            response.setData(createdRole);
	            response.setMsg("Course created successfully.");
	            response.setStatus("Success");
	            logger.info("Course added: {}", courseDto.getName());
	            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.CREATED);
	        } catch (ResourceAlreadyExistsException e) {
	            logger.warn("Attempted to add an existing course: {}", courseDto.getName());
	            response.setData(null);
	            response.setStatus("CONFLICT");
	            response.setMsg("Course"+ courseDto.getName() + "' already exists.");
	            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.OK); 
	        } catch (Exception e) {
	            logger.error("Error while adding Course: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while creating the Course.");
	            return new ResponseEntity<GenericResponse<CourseDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
	        }
	    }
	 

}
