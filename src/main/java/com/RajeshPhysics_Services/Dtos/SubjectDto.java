package com.RajeshPhysics_Services.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.RajeshPhysics_Services.Models.Chapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDto {
	private Long id;
	
	private String name;
	
	private String imgUrl;
	
	private int isActive;
	
	private String subjectIntroduction;
	
	private String subjectDescription;
	
	private LocalDateTime createdAt;

	private LocalDateTime updateAt;
	
	private List<Chapter> chapters = new ArrayList<>();

}
