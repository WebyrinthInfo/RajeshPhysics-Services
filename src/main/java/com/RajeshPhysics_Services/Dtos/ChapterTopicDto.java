package com.RajeshPhysics_Services.Dtos;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChapterTopicDto {
	private Long id;
	private String topicName;
	private String imgpath;
	private String topicIntroduction;
	private String topicDescription;
	private String topicStreamedURL;
	private int isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	
}
