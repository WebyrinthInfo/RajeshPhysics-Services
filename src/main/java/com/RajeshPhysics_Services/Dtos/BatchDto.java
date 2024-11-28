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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}



	public String getBatchStartAt() {
		return batchStartAt;
	}

	public void setBatchStartAt(String batchStartAt) {
		this.batchStartAt = batchStartAt;
	}

	public String getBatchEndAt() {
		return batchEndAt;
	}

	public void setBatchEndAt(String batchEndAt) {
		this.batchEndAt = batchEndAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public BatchDto() {
		super();
	}
	
	

}
