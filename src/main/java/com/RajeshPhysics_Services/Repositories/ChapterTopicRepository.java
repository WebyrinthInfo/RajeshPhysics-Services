package com.RajeshPhysics_Services.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Dtos.ChapterTopicDto;
import com.RajeshPhysics_Services.Models.ChapterTopic;

@Repository
public interface ChapterTopicRepository extends JpaRepository<ChapterTopic, Long> {


	@Query(value = "SELECT * FROM rajeshphysics.chapter_topics WHERE topic_name = :topicName", nativeQuery = true)
	Optional<ChapterTopicDto> findByTopicName(@Param("topicName") String topicName);
	
	@Query(value = "SELECT * FROM rajeshphysics.users WHERE id LIKE CONCAT('%', :search, '%') OR topic_name LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<ChapterTopic> findByKeyword(@Param("search") String search, PageRequest page);


}
