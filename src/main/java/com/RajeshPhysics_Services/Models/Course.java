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
import jakarta.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "COURSE_BATCH_MAPPER", joinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "BATCH_ID", referencedColumnName = "ID"))
	private List<Batch> batches = new ArrayList<>();

}
