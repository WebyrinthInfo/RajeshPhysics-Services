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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "COURSES")
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {

	private static final long serialVersionUID = 7228516541029629119L;

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "COURSE_NAME", nullable = false)
	private String name;

	@Column(name = "COURSE_IMAGE_PATH", nullable = true)
	private String imgPath;

	@Column(name = "COURSE_LANGUAGE", nullable = false)
	private String courseLanguage;

	@Column(name = "COURSE_TITLE")
	private String courseTitle;

	@Column(name = "COURSE_DESCRIPTION")
	private String courseDescription;

	@Column(name = "COURSE_PRICE")
	private String coursePrice;
	
	@Column(name="CREATED_AT", nullable = false)
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updatedAt;

//	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private ArrayList<User> users = new ArrayList<>();

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

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getCourseLanguage() {
		return courseLanguage;
	}

	public void setCourseLanguage(String courseLanguage) {
		this.courseLanguage = courseLanguage;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCoursePrice() {
		return coursePrice;
	}

	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
//
//	public ArrayList<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(ArrayList<User> users) {
//		this.users = users;
//	}
//	

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

	public Course() {
		super();
	}

}
