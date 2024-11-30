package com.RajeshPhysics_Services.ServiceImps;

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
import com.RajeshPhysics_Services.Dtos.ChapterTopicDto;
import com.RajeshPhysics_Services.Dtos.CourseDto;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Repositories.ChapterTopicRepository;
import com.RajeshPhysics_Services.Services.ChapterTopicService;

@Service
public class ChapterTopicServiceImpl implements ChapterTopicService {
	public static final Logger logger = LoggerFactory.getLogger(ChapterTopicServiceImpl.class);

	@Autowired
	private ChapterTopicRepository chapterTopicRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ChapterTopicDto addChapterTopic(ChapterTopicDto chapterTopicDto) {
		logger.info("Adding a new ChapterTopic: {} : {}", chapterTopicDto.getTopicName());

		Optional<ChapterTopicDto> existingChapterTopicDto = chapterTopicRepo
				.findByTopicName(chapterTopicDto.getTopicName().trim().toUpperCase());
		if (existingChapterTopicDto.isPresent()) {
			logger.warn("Attempt to add existing Chapter Topic: {}", chapterTopicDto.getTopicName());
			throw new ResourceAlreadyExistsException(
					"Chapter Topic  '" + chapterTopicDto.getTopicName() + "' already exists.");
		}

		ChapterTopic chapterTopic = modelMapper.map(chapterTopicDto, ChapterTopic.class);
		chapterTopic.setTopicName(chapterTopicDto.getTopicName().trim().toUpperCase());
		chapterTopic.setImgpath("default.png");
		chapterTopic.setTopicIntroduction(chapterTopic.getTopicIntroduction().trim());
		chapterTopic.setTopicDescription(chapterTopic.getTopicDescription().toUpperCase().trim());
		chapterTopic.setTopicStreamedURL(chapterTopic.getTopicStreamedURL());

		ChapterTopic savedChapterTopic = chapterTopicRepo.save(chapterTopic);
		logger.info("Chapter Topic added successfully: {}", savedChapterTopic.getTopicName());

		return modelMapper.map(savedChapterTopic, ChapterTopicDto.class);
	}

	@Override
	public PageableDataResponse<List<ChapterTopic>> getAllChapterTopics(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, String search) {
		try {
			Sort sorting = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
					: Sort.by(sortBy).ascending();
			PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sorting);
			Page<ChapterTopic> chapterTopicPage;

			if (search == null || search.isEmpty()) {
				chapterTopicPage = chapterTopicRepo.findAll(pageRequest);
			} else {
				chapterTopicPage = chapterTopicRepo.findByKeyword(search, pageRequest);
			}

			List<ChapterTopic> content = chapterTopicPage.getContent();
			PageableDataResponse<List<ChapterTopic>> response = new PageableDataResponse<>();
			response.setContent(content);
			response.setPageNumber(chapterTopicPage.getNumber());
			response.setPageSize(chapterTopicPage.getSize());
			response.setTotalElements(chapterTopicPage.getTotalElements());
			response.setTotalPages(chapterTopicPage.getTotalPages());
			response.setLastPage(chapterTopicPage.isLast());

			logger.info("Fetched {} courses (page {}/{})", content.size(), chapterTopicPage.getNumber() + 1,
					chapterTopicPage.getTotalPages());
			
			return response;

		} catch (Exception e) {
			logger.error("Error occurred while fetching courses: ", e);
			throw new ForbbidonExceptions("Something went wrong while fetching courses!");
		}
	}

}
