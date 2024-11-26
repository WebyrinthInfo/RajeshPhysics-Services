package com.RajeshPhysics_Services.Services;

import java.util.List;

import javax.management.relation.RoleNotFoundException;

import com.RajeshPhysics_Services.Dtos.RoleDto;


public interface RoleService {
	
//	----------add role-----------------------
	 public RoleDto addRole(RoleDto roleDto);
	 
//	 ---------get All roles-------------------
	 public List<RoleDto> getAllRoles();
	 
// -------------update roles----------------
	RoleDto updateRole(Long roleId, RoleDto roleDto) throws RoleNotFoundException;
	
//	----------delete role by id-----------------
	  public void deleteRole(Long roleId) throws RoleNotFoundException;

}
