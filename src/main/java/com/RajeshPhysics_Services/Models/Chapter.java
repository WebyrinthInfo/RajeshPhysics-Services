package com.RajeshPhysics_Services.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="CHAPTERS")
@Entity
public class Chapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9165584429078515121L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "CHAPTER_NAME")
	private String chapterName;
	
	@Column(name = "IMAGE_PATH")
	private String imagePath;
	
	@Column(name="CHAPTER_INTRODUCTION")
	private String chapterIntroduction;
	
	@Column(name="CHAPTER_DESCRIPTION")
	private String chapterDescription;
	
	@Column(name="IS_ACTIVE")
	private int isActive=1;
	
	@Column(name = "CREATED_AT")
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;

	@Column(name = "UPDATE_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updateAt;
	
//	-----------one chapter having many topics------------------------
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CHAPTER_CHAPTER-TOPIC_MAPPER", joinColumns = @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CHAPTER_TABLE_ID", referencedColumnName = "ID"))
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}