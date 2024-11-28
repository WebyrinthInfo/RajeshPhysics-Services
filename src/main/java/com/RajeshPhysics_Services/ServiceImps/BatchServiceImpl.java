package com.RajeshPhysics_Services.ServiceImps;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RajeshPhysics_Services.Dtos.BatchDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.Batch;
import com.RajeshPhysics_Services.Models.Course;
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

}
