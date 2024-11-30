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


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="CHAPTER_TOPICS")
@Entity
public class ChapterTopic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 127638502244067743L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="TOPIC_NAME")
	private String topicName;
	
	@Column(name="IMAGE_PATH")
	private String imgpath;
	
	@Column(name="TOPIC_INTRODUCTION")
	private String topicIntroduction;
	
	@Column(name="TOPIC_DESCRIPTION")
	private String topicDescription;
	
	@Column(name="TOPIC_STREAMED_URL")
	private String topicStreamedURL;
	
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

}
