package com.RajeshPhysics_Services.Models;

import java.io.Serializable;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="VIDEO")
public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 569818420920586234L;
	
	@Column(name="VIDEO_ID", nullable =  false, unique = true)
	@Id
	private String videoId;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CONTENT_TYPE")
	private String contentType;
	
	@Column(name="FILE_PATH")
	private String filePath;
	
	@Column(name="CREATED_AT", nullable = false)
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private String createdAt;
	
	@Column(name="UPDATED_AT", nullable =  false)
	@UpdateTimestamp
	@JsonFormat(shape=Shape.STRING, pattern ="dd-MM-yyyy HH:mm:ss a")
	private String updatedAt;
	
	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentType() {
		return contentType;
	}


	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Video() {
		super();
	}
	
	

}
