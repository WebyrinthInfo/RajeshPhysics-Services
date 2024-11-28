package com.RajeshPhysics_Services.Controllers;

import java.time.LocalDateTime;

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

import com.RajeshPhysics_Services.Dtos.BatchDto;
import com.RajeshPhysics_Services.Dtos.RoleDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Services.BatchService;
import com.RajeshPhysics_Services.Services.RoleService;

@RestController
@RequestMapping("/api/batch")
@CrossOrigin("*")
public class BatchController {
	public static final Logger logger = LoggerFactory.getLogger(BatchController.class);
	
	 @Autowired
	    private BatchService batchServe;
	    
	    // ---------------- Add Batch --------------
	    @PostMapping("/add")
	    public ResponseEntity<GenericResponse<BatchDto>> addBatch(@RequestBody BatchDto batchDto, GenericResponse<BatchDto> response) {
	        logger.info("Entering addBatch with data: {} : {}", batchDto, LocalDateTime.now());
	        
	        try {
	        	BatchDto createdBatch = batchServe.addBatch(batchDto);
	            response.setData(createdBatch);
	            response.setMsg("Batch created successfully.");
	            response.setStatus("Success");
	            logger.info("Batch added successfully: {} : {}", createdBatch.getBatchCode(), LocalDateTime.now());
	            return new ResponseEntity<>(response, HttpStatus.CREATED);
	        } catch (ResourceAlreadyExistsException e) {
	            logger.warn("Attempted to add an existing batch: {} : {}", batchDto.getBatchCode(), LocalDateTime.now());
	            response.setData(null);
	            response.setStatus("CONFLICT");
	            response.setMsg("Batch '" + batchDto.getBatchCode() + "' already exists.");
	            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	        } catch (Exception e) {
	            logger.error("Error while adding batch: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while creating the batch.");
	            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        } finally {
	            logger.info("Exiting addBath : {} : {}",batchDto.getBatchCode(), LocalDateTime.now());
	        }
	    }
}
