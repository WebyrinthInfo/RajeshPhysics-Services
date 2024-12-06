package com.RajeshPhysics_Services.Models;

import java.io.Serializable;
import java.time.LocalDate;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="BATCHES")
public class Batch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6970048370016928604L;
	
	@Column(name="ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="BATCH_CODE", nullable = false, unique = true)
	private String batchCode;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="IS_ACTIVE")
	private int isActive=1;
	
	
	@Column(name="BATCH_TIMING", nullable = false)
	private String timing;
	
	@Column(name="BATCH_START_AT", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private String batchStartAt;
	
	@Column(name="BATCH_END_AT", nullable = false)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private String batchEndAt;
	
	
	@Column(name="CREATED_AT", nullable = false)
	@CreationTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime createdAt;
	
	@Column(name="UPDATED_AT", nullable = false)
	@UpdateTimestamp
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	private LocalDateTime updatedAt;
	
//	-----------------One Batch having many SUBJECT-------------------
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "BATCH_SUBJECT_MAPPER", joinColumns = @JoinColumn(name = "BATCH_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID"))
	private List<Subject> subjects = new ArrayList<>();

	
	

}
