package com.RajeshPhysics_Services.ServiceImps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import com.RajeshPhysics_Services.Dtos.BatchDto;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.Batch;
import com.RajeshPhysics_Services.Models.Course;
import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Repositories.BatchRepository;
import com.RajeshPhysics_Services.Services.BatchService;

@Service
public class BatchServiceImpl implements BatchService {
	private static final Logger logger = LoggerFactory.getLogger(BatchServiceImpl.class);

	@Autowired
	private BatchRepository batchRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BatchDto addBatch(BatchDto batchDto) {
		logger.info("Adding a new Batch: {} : {}", batchDto.getBatchCode(), LocalDateTime.now());

		Optional<Batch> existingBatch = batchRepo.findByBatchCode(batchDto.getBatchCode().trim().toUpperCase());
		if (existingBatch.isPresent()) {
			logger.warn("Attempt to add existing batch : {} : {}", batchDto.getBatchCode(), LocalDateTime.now());
			throw new ResourceAlreadyExistsException("Batch '" + batchDto.getBatchCode() + "' already exists.");
		}

//	        ---------batch will expire in 455 days form the created batch ----------------
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(455);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = dtf.format(plusDays);

		Batch batch = modelMapper.map(batchDto, Batch.class);
		batch.setBatchCode(batchDto.getBatchCode().trim().toUpperCase());
		batch.setBatchEndAt(formatedDate);
		batch.setTiming(batchDto.getTiming().trim());
		batch.setBatchStartAt(batchDto.getBatchStartAt());
		batch.setDescription(batchDto.getDescription().trim());
		batch.setIsActive(batchDto.getIsActive());
		batch.setTitle(batchDto.getTitle().trim());

		Batch savedBatch = batchRepo.save(batch);
		logger.info("Batch added successfully: {} : {}", savedBatch.getBatchCode(), LocalDateTime.now());

		return modelMapper.map(savedBatch, BatchDto.class);
	}
	
	@Override
	public PageableDataResponse<List<Batch>> getAllBatch(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search) {
		try {
			if(search==null || search == "") {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Batch> pageBatch = batchRepo.findAll(page);
				List<Batch> content = pageBatch.getContent();
				PageableDataResponse<List<Batch>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageBatch.getNumber());
				pr.setPageSize(pageBatch.getSize());
				pr.setTotalElements(pageBatch.getTotalElements());
				pr.setTotalPages(pageBatch.getTotalPages());
				pr.setLastPage(pageBatch.isLast());
				return pr;
				
			}else {
				Sort sorting = null;
				if(sortDir.equalsIgnoreCase("desc")) {
					sorting = Sort.by(sortBy).descending();
				}else {
					sorting = Sort.by(sortBy).ascending();
				}
					
				PageRequest page = PageRequest.of(pageNumber, pageSize, sorting);
				Page<Batch> pageBatch = batchRepo.findByKeyword(search, page);
				List<Batch> content = pageBatch.getContent();
				PageableDataResponse<List<Batch>> pr = new PageableDataResponse<>();
				pr.setContent(content);
				pr.setPageNumber(pageBatch.getNumber());
				pr.setPageSize(pageBatch.getSize());
				pr.setTotalElements(pageBatch.getTotalElements());
				pr.setTotalPages(pageBatch.getTotalPages());
				pr.setLastPage(pageBatch.isLast());
				return pr;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			throw new  ForbbidonExceptions("Something Went Wrong !!");
		}
		
	}

}