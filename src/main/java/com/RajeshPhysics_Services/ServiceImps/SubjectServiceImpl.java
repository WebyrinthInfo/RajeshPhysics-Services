package com.RajeshPhysics_Services.ServiceImps;

import java.time.LocalDateTime;
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
import com.RajeshPhysics_Services.Dtos.SubjectDto;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Exceptions.ResourceNotFoundException;
import com.RajeshPhysics_Services.Models.Chapter;
import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.RajeshPhysics_Services.Models.Subject;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Repositories.ChapterRepository;
import com.RajeshPhysics_Services.Repositories.ChapterTopicRepository;
import com.RajeshPhysics_Services.Repositories.SubjectRepository;
import com.RajeshPhysics_Services.Services.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	public static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);
	
	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private ChapterRepository chapterRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SubjectDto addSubject(SubjectDto subjectDto, Long chapterId) {
		logger.info("Adding a new Subject: {} : {}", subjectDto.getName(), LocalDateTime.now());

		Optional<Subject> existingChapter = subjectRepo
				.findByName(subjectDto.getName().trim().toUpperCase());
		if (existingChapter.isPresent()) {
			logger.warn("Attempt to add existing Subject : {} : {}", subjectDto.getName(), LocalDateTime.now());
			throw new ResourceAlreadyExistsException("Subject '" + subjectDto.getName() + "' already exists.");
		}

//		--------------------get Chapter info------------------------
		Chapter chapterInfo = chapterRepo.findById(chapterId)
				.orElseThrow(() -> new ResourceNotFoundException("Chapter  is not Found  : " + chapterId));
		List<Chapter> chapter = new ArrayList<>();
		chapter.add(chapterInfo);

		Subject subject = modelMapper.map(subjectDto, Subject.class);
		subject.setSubjectDescription(subjectDto.getSubjectDescription().trim());
		subject.setChapters(chapter);
		subject.setImgUrl("default.png");
		subject.setName(subjectDto.getName().toUpperCase().trim());
		subject.setSubjectIntroduction(subjectDto.getSubjectIntroduction().trim());

		Subject savedSubject = subjectRepo.save(subject);
		logger.info("Chapter added successfully: {} : {}", savedSubject.getName(), LocalDateTime.now());

		return modelMapper.map(savedSubject, SubjectDto.class);
	}

	@Override
	public PageableDataResponse<List<Subject>> getAllSubjects(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, String search) {
		try {
			if (search == null || search == "") {
				Sort sorting = null;
				if (sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				} else {
					sorting = Sort.by(sortBy).ascending();
				}

				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Subject> pageSubject = subjectRepo.findAll(page);
				List<Subject> content = pageSubject.getContent();
				PageableDataResponse<List<Subject>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageSubject.getNumber());
				pr.setPageSize(pageSubject.getSize());
				pr.setTotalElements(pageSubject.getTotalElements());
				pr.setTotalPages(pageSubject.getTotalPages());
				pr.setLastPage(pageSubject.isLast());
				return pr;

			} else {
				Sort sorting = null;
				if (sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				} else {
					sorting = Sort.by(sortBy).ascending();
				}

				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Subject> pageSubject = subjectRepo.findByKeyword(search, page);
				List<Subject> content = pageSubject.getContent();
				PageableDataResponse<List<Subject>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageSubject.getNumber());
				pr.setPageSize(pageSubject.getSize());
				pr.setTotalElements(pageSubject.getTotalElements());
				pr.setTotalPages(pageSubject.getTotalPages());
				pr.setLastPage(pageSubject.isLast());
				return pr;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new ForbbidonExceptions("Something Went Wrong !!");
		}

	}

}
