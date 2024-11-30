package com.RajeshPhysics_Services.Repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Models.Chapter;
@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

	
	
	@Query(value = "SELECT * FROM rajeshphysics.chapters WHERE chapter_name = :chapterName", nativeQuery = true)
	Optional<Chapter> findByChapterName(@Param("chapterName") String chapterName);

	
	@Query(value = "SELECT * FROM rajeshphysics.chapters WHERE id LIKE CONCAT('%', :search, '%') OR chapter_name LIKE CONCAT('%', :search, '%')", nativeQuery = true)
	Page<Chapter> findByKeyword(@Param("search") String search, PageRequest page);


}
