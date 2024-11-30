package com.RajeshPhysics_Services.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDto {
	
	private long id;
	private String chapterName;
	private String imagePath;
	private String chapterIntroduction;
	private String chapterDescription;
	private int isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private List<ChapterTopic> chapterTopics = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getChapterIntroduction() {
		return chapterIntroduction;
	}
	public void setChapterIntroduction(String chapterIntroduction) {
		this.chapterIntroduction = chapterIntroduction;
	}
	public String getChapterDescription() {
		return chapterDescription;
	}
	public void setChapterDescription(String chapterDescription) {
		this.chapterDescription = chapterDescription;
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
	public List<ChapterTopic> getChapterTopics() {
		return chapterTopics;
	}
	public void setChapterTopics(List<ChapterTopic> chapterTopics) {
		this.chapterTopics = chapterTopics;
	}
	public ChapterDto() {
		super();
	}
	

}
