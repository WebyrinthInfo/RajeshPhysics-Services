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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getTopicIntroduction() {
		return topicIntroduction;
	}
	public void setTopicIntroduction(String topicIntroduction) {
		this.topicIntroduction = topicIntroduction;
	}
	public String getTopicDescription() {
		return topicDescription;
	}
	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}
	public String getTopicStreamedURL() {
		return topicStreamedURL;
	}
	public void setTopicStreamedURL(String topicStreamedURL) {
		this.topicStreamedURL = topicStreamedURL;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public ChapterTopicDto() {
		super();
	}
	
	
}
