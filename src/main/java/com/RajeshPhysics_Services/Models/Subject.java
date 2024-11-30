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

@Entity
@Table(name="SUBJECTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 458567242680803213L;
	
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="SUBJECT_NAME")
	private String name;
	
	@Column(name="IMAGE_URL")
	private String imgUrl;
	
	@Column(name="IS_ACTIVE")
	private int isActive = 1;
	
	@Column(name="SUBJECT_INTRODUCTION")
	private String subjectIntroduction;
	
	@Column(name="SUBJECT_DESCRIPTION")
	private String subjectDescription;
	
	@Column(name = "CREATED_AT")
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;

	@Column(name = "UPDATE_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updateAt;
	
//	-----------------One subject having many Chapters-------------------
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "SUBJECT_CHAPTER_MAPPER", joinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "CHAPTER_ID", referencedColumnName = "ID"))
	private List<Chapter> chapters = new ArrayList<>();

}
