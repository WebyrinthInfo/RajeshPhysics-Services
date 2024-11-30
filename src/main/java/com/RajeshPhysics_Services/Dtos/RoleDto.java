package com.RajeshPhysics_Services.Dtos;


import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	private Long id;

	private String name;

	// 1 = Active, 0 = Inactive
	private int isActive;
	
	private String description;

	private String createdAt;

	private String updateAt;
}
