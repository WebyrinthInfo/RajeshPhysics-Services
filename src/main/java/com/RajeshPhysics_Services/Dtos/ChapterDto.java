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
	
	

}
