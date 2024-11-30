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

import com.RajeshPhysics_Services.Dtos.ChapterDto;
import com.RajeshPhysics_Services.Models.Chapter;
import com.RajeshPhysics_Services.Models.Chapter;
import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Services.ChapterService;

@RestController
@RequestMapping("/api/chapter")
@CrossOrigin("*")
public class ChapterController {
	private static final Logger logger = LoggerFactory.getLogger(ChapterController.class);
	
	@Autowired
	private ChapterService chapterServe;
	
	 @PostMapping("/add")
	    public ResponseEntity<GenericResponse<ChapterDto>> createChapterAndAssignChapterTopicId(
	            @RequestBody ChapterDto chapterDto,
	            @RequestParam(name = "chapterTopicId", required = true) Long chapterTopicId,
	            GenericResponse<ChapterDto> response) {

	        logger.info("Chapter request received with chapterTopic Id: {} : {}", chapterTopicId, LocalDateTime.now());
	        ChapterDto chapterDtoInfo = null;
	        if (chapterTopicId != null && chapterDto != null) {
	            chapterDtoInfo = chapterServe.addChapter(chapterDto, chapterTopicId);
	            if (chapterDtoInfo != null) {
	                response.setData(chapterDtoInfo);
	                response.setMsg("Data Add Successfully!");
	                response.setStatus("SUCCESS");
	                logger.info("Chapter Add successful : {} : {}", chapterDtoInfo.getChapterName(), LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<ChapterDto>>(response, HttpStatus.OK);
	            } else {
	                response.setData(chapterDtoInfo);
	                response.setMsg("Something went Wrong!!");
	                response.setStatus("INTERNAL_SERVER_ERROR");
	                logger.error("Chapter Data failed for Save: {} : {}", chapterDto.getChapterName(), LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<ChapterDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        } else {
	            response.setData(chapterDtoInfo);
	            response.setMsg("Invalid Parameters");
	            response.setStatus("BAD_REQUEST");
	            logger.warn("Invalid Chapter parameters received : "+LocalDateTime.now());
	            return new ResponseEntity<GenericResponse<ChapterDto>>(response, HttpStatus.BAD_REQUEST);
	        }
	    }

//	    ------------------get All Chapter info------------------------
	    @GetMapping("/get-all")
	    public ResponseEntity<GenericResponse<PageableDataResponse<List<Chapter>>>> getPaidStudent(
	            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
	            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
	            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
	            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
	            @RequestParam(required = false) String search) {

	        GenericResponse<PageableDataResponse<List<Chapter>>> response = new GenericResponse<>();
	        
	        logger.warn("Fetching Chapter with pageNumber={}, pageSize={}, sortBy={}, sortDir={}, search={} : {}", pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
	        
	        try {
	            PageableDataResponse<List<Chapter>> chapterPage = chapterServe.getAllChapters(pageNumber, pageSize, sortBy, sortDir, search);
	            
	            if (chapterPage != null) {
	                response.setData(chapterPage);
	                response.setStatus("SUCCESS");
	                response.setMsg("Data fetched successfully!");
	                logger.info("Data fetched successfully! : {} ",LocalDateTime.now() );
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Chapter>>>>(response, HttpStatus.OK);
	            } else {
	                response.setData(null);
	                response.setStatus("SUCCESS");
	                response.setMsg("No data available!");
	                logger.warn("No data available! : {} ", LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Chapter>>>>(response, HttpStatus.OK);
	            }
	        } catch (Exception e) {
	            response.setData(null);
	            response.setStatus("INTERNAL_SERVER_ERROR");
	            response.setMsg("An error occurred while fetching data!");
	            logger.error("An error occurred while fetching data : {}" ,LocalDateTime.now(), e);
	            return new ResponseEntity<GenericResponse<PageableDataResponse<List<Chapter>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
