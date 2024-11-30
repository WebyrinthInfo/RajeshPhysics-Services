package com.RajeshPhysics_Services.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
	
	private Integer id;

	private String name;
	
	private String imgPath;
	
	private String courseLanguage;
	
	private String courseTitle;
	
	private String courseDescription;
	
	private String coursePrice;

}
