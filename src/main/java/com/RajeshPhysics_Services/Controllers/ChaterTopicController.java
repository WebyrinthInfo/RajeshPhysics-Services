package com.RajeshPhysics_Services.Controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RajeshPhysics_Services.Dtos.ChapterTopicDto;
import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Services.ChapterTopicService;

@RestController
@RequestMapping("/api/chapter-topic")
@CrossOrigin("*")
public class ChaterTopicController {
	
private static final Logger logger = LoggerFactory.getLogger(ChaterTopicController.class);
    
    @Autowired
    private ChapterTopicService chapterTopicServe;
    
    // ---------------- Add chapter topic --------------
    @PostMapping("/add")
    public ResponseEntity<GenericResponse<ChapterTopicDto>> addChapterTopic(@RequestBody ChapterTopicDto ChapterTopicDto, GenericResponse<ChapterTopicDto> response) {
        logger.info("Entering addChapterTopic with data: {} : {}", ChapterTopicDto, LocalDateTime.now() );
        
        try {
            ChapterTopicDto createdChapterTopic = chapterTopicServe.addChapterTopic(ChapterTopicDto);
            response.setData(createdChapterTopic);
            response.setMsg("ChapterTopic created successfully.");
            response.setStatus("Success");
            logger.info("ChapterTopic added successfully: {} : {}", createdChapterTopic.getTopicName(), LocalDateTime.now());
            return new ResponseEntity<GenericResponse<ChapterTopicDto>>(response, HttpStatus.CREATED);
        } catch (ResourceAlreadyExistsException e) {
            logger.warn("Attempted to add an existing ChapterTopic: {} : {}", ChapterTopicDto.getTopicName(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("CONFLICT");
            response.setMsg("ChapterTopic '" + ChapterTopicDto.getTopicName() + "' already exists.");
            return new ResponseEntity<GenericResponse<ChapterTopicDto>>(response, HttpStatus.CONFLICT);
        } catch (Exception e) {
            logger.error("Error while adding ChapterTopic: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("FAILURE");
            response.setMsg("An error occurred while creating the ChapterTopic.");
            return new ResponseEntity<GenericResponse<ChapterTopicDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting addChapterTopic : {}", LocalDateTime.now());
        }
    }
    
    // ---------------- Get All ChapterTopics --------------
    @GetMapping("/get-all")
    public ResponseEntity<GenericResponse<PageableDataResponse<List<ChapterTopic>>>> getAllChapterTopics(
            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
            @RequestParam(required = false) String search) {

        logger.info("Entering getAllChapterTopics with parameters - pageNumber: {}, pageSize: {}, sortBy: {}, sortDir: {}, search: {}, {}", 
                pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
        
        GenericResponse<PageableDataResponse<List<ChapterTopic>>> response = new GenericResponse<>();
        
        try {
            PageableDataResponse<List<ChapterTopic>> ChapterTopicPage = chapterTopicServe.getAllChapterTopics(pageNumber, pageSize, sortBy, sortDir, search);
            
            if (ChapterTopicPage != null) {
                response.setData(ChapterTopicPage);
                response.setStatus("SUCCESS");
                response.setMsg("Data fetched successfully!");
                logger.info("ChapterTopics fetched successfully : {}", LocalDateTime.now());
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<ChapterTopic>>>>(response, HttpStatus.OK);
            } else {
                response.setData(null);
                response.setStatus("SUCCESS");
                response.setMsg("No data available!");
                logger.warn("No ChapterTopics available");
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<ChapterTopic>>>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching ChapterTopics: {} : {}", e.getMessage(), LocalDateTime.now(), e);
            response.setData(null);
            response.setStatus("INTERNAL_SERVER_ERROR");
            response.setMsg("An error occurred while fetching data!");
            return new ResponseEntity<GenericResponse<PageableDataResponse<List<ChapterTopic>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            logger.info("Exiting getAllChapterTopics : {}", LocalDateTime.now());
        }
    }

}
