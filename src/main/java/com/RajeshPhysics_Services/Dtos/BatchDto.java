package com.RajeshPhysics_Services.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.RajeshPhysics_Services.Models.Subject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchDto {
	
	private Long id;
	
	private String batchCode;
	
	private String title;
	
	private String description;
	
	private int isActive;
		
	private String timing;
	
	private String batchStartAt;
	
	private String batchEndAt;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	private List<Subject> subjects = new ArrayList<>();
	

}
