package com.RajeshPhysics_Services.Controllers;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RajeshPhysics_Services.Dtos.RoleDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Services.RoleService;


@RestController
@RequestMapping("/api/role")
@CrossOrigin("*")
public class RoleController {
	public static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleServe;
	
	
	
	
	
//	add roles
	 @PostMapping("/add")
	 public ResponseEntity<GenericResponse<RoleDto>> addRole(@RequestBody RoleDto roleDto, GenericResponse<RoleDto> response) {
		 try {
	            RoleDto createdRole = roleServe.addRole(roleDto);
	            response.setData(createdRole);
	            response.setMsg("Role created successfully.");
	            response.setStatus("Success");
	            logger.info("Role added: {}", roleDto.getName());
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.CREATED);
	        } catch (ResourceAlreadyExistsException e) {
	            logger.warn("Attempted to add an existing role: {}", roleDto.getName());
	            response.setData(null);
	            response.setStatus("CONFLICT");
	            response.setMsg("Role '" + roleDto.getName() + "' already exists.");
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.OK); 
	        } catch (Exception e) {
	            logger.error("Error while adding role: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while creating the role.");
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
	        }
	    }
	 
//	 Get All roles
	    @GetMapping("/get-all")
	    public ResponseEntity<GenericResponse<List<RoleDto>>> getAllRoles() {
	        GenericResponse<List<RoleDto>> response = new GenericResponse<>();
	        try {
	            List<RoleDto> roles = roleServe.getAllRoles();
	            response.setData(roles);
	            response.setMsg("Roles fetched successfully.");
	            response.setStatus("Success");
	            logger.info("Fetched all roles.");
	            return new ResponseEntity<GenericResponse<List<RoleDto>>>(response, HttpStatus.OK);
	        } catch (Exception e) {
	            logger.error("Error while fetching roles: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while fetching the roles.");
	            return new ResponseEntity<GenericResponse<List<RoleDto>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    // Update role
	    @PutMapping("/update")
	    public ResponseEntity<GenericResponse<RoleDto>> updateRole(@RequestParam Long roleId,@RequestBody RoleDto roleDto) {
	        GenericResponse<RoleDto> response = new GenericResponse<>();
	        try {
	            RoleDto updatedRole = roleServe.updateRole(roleId, roleDto);
	            response.setData(updatedRole);
	            response.setMsg("Role updated successfully.");
	            response.setStatus("Success");
	            logger.info("Role updated: {}", roleDto.getName());
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.OK);
	        } catch (RoleNotFoundException e) {
	            logger.warn("Role not found: {}", roleId);
	            response.setData(null);
	            response.setStatus("NOT_FOUND");
	            response.setMsg(e.getMessage());
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            logger.error("Error while updating role: {}", e.getMessage(), e);
	            response.setData(null);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while updating the role.");
	            return new ResponseEntity<GenericResponse<RoleDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	 // Delete role
	    @DeleteMapping("/delete")
	    public ResponseEntity<GenericResponse<Void>> deleteRole(@RequestParam Long roleId) {
	        GenericResponse<Void> response = new GenericResponse<>();
	        try {
	            roleServe.deleteRole(roleId);
	            response.setMsg("Role deleted successfully.");
	            response.setStatus("Success");
	            logger.info("Role deleted: {}", roleId);
	            return new ResponseEntity<GenericResponse<Void>>(response, HttpStatus.OK);
	        } catch (RoleNotFoundException e) {
	            logger.warn("Role not found: {}", roleId);
	            response.setStatus("NOT_FOUND");
	            response.setMsg(e.getMessage());
	            return new ResponseEntity<GenericResponse<Void>>(response, HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            logger.error("Error while deleting role: {}", e.getMessage(), e);
	            response.setStatus("FAILURE");
	            response.setMsg("An error occurred while deleting the role.");
	            return new ResponseEntity<GenericResponse<Void>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    
	    
}
