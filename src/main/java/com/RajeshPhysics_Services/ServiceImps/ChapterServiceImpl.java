package com.RajeshPhysics_Services.ServiceImps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.RajeshPhysics_Services.Dtos.ChapterDto;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Exceptions.ResourceNotFoundException;
import com.RajeshPhysics_Services.Models.Chapter;
import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Repositories.ChapterRepository;
import com.RajeshPhysics_Services.Repositories.ChapterTopicRepository;
import com.RajeshPhysics_Services.Services.ChapterService;

@Service
public class ChapterServiceImpl implements ChapterService {
	private static final Logger logger = LoggerFactory.getLogger(ChapterTopicServiceImpl.class);
	
	@Autowired
	private ChapterRepository chapterRepo;
	
	@Autowired
	private ChapterTopicRepository chapterTopicRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ChapterDto addChapter(ChapterDto chapterDto, Long chapterTopicId) {
		logger.info("Adding a new Chapter: {} : {}", chapterDto.getChapterName(), LocalDateTime.now());

		Optional<Chapter> existingChapter = chapterRepo.findByChapterName(chapterDto.getChapterName().trim().toUpperCase());
		if (existingChapter.isPresent()) {
			logger.warn("Attempt to add existing Chapter : {} : {}", chapterDto.getChapterName(), LocalDateTime.now());
			throw new ResourceAlreadyExistsException("Chapter '" + chapterDto.getChapterName() + "' already exists.");
		}

//		--------------------get ChapterTopic info------------------------
		ChapterTopic chapterTopicInfo = chapterTopicRepo.findById(chapterTopicId).orElseThrow(()-> new ResourceNotFoundException("ChapterTopic  is not Found  : "+chapterTopicId));
		List<ChapterTopic>  chapterTopic = new ArrayList<>(); 
		chapterTopic.add(chapterTopicInfo);
	

		Chapter chapter = modelMapper.map(chapterDto, Chapter.class);
		chapter.setChapterDescription(chapterDto.getChapterDescription().trim());
		chapter.setChapterIntroduction(chapterDto.getChapterIntroduction().trim());
		chapter.setChapterName(chapterDto.getChapterName().toUpperCase().trim());
		chapter.setChapterTopics(chapterTopic);
		

		Chapter savedChapter = chapterRepo.save(chapter);
		logger.info("Chapter added successfully: {} : {}", savedChapter.getChapterName(), LocalDateTime.now());

		return modelMapper.map(savedChapter, ChapterDto.class);
	}
	
	@Override
	public PageableDataResponse<List<Chapter>> getAllChapters(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
		try {
			if(search==null || search == "") {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Chapter> pageChapter = chapterRepo.findAll(page);
				List<Chapter> content = pageChapter.getContent();
				PageableDataResponse<List<Chapter>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageChapter.getNumber());
				pr.setPageSize(pageChapter.getSize());
				pr.setTotalElements(pageChapter.getTotalElements());
				pr.setTotalPages(pageChapter.getTotalPages());
				pr.setLastPage(pageChapter.isLast());
				return pr;
				
			}else {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Chapter> pageChapter = chapterRepo.findByKeyword(search, page);
				List<Chapter> content = pageChapter.getContent();
				PageableDataResponse<List<Chapter>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageChapter.getNumber());
				pr.setPageSize(pageChapter.getSize());
				pr.setTotalElements(pageChapter.getTotalElements());
				pr.setTotalPages(pageChapter.getTotalPages());
				pr.setLastPage(pageChapter.isLast());
				return pr;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new  ForbbidonExceptions("Something Went Wrong !!");
		}
		
	}
}
