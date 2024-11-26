package com.RajeshPhysics_Services.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ROLES")
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7197034083275104657L;
	
	@Column(name="ID", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
//	1 : active, 0 : inactive
	@Column(name="IS_ACTIVE", nullable = false)
	private int isActive;
	
	@Column(name="DESCRIPTION", nullable = false, length = 255)
	private String description;
	
	@Column(name="CREATED_AT")
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;
	
	@Column(name="UPDATE_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updateAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Role(Long id, String name, int isActive, String description, LocalDateTime createdAt,
			LocalDateTime updateAt) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.description = description;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public Role() {
		super();
	}
	
	

}
