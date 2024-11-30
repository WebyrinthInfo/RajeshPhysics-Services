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

import com.RajeshPhysics_Services.Dtos.SubjectDto;
import com.RajeshPhysics_Services.Models.Subject;
import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Services.SubjectService;

@RestController
@RequestMapping("/api/subject")
@CrossOrigin("*")
public class SubjectController {
	private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);
	
	@Autowired
	private SubjectService subjectServe;
	
	 @PostMapping("/add")
	    public ResponseEntity<GenericResponse<SubjectDto>> createSubjectAndAssignChapterId(
	            @RequestBody SubjectDto subjectDto,
	            @RequestParam(name = "chapterId", required = true) Long chapterId,
	            GenericResponse<SubjectDto> response) {

	        logger.info("Subject request received with chapter  Id: {} : {}", chapterId, LocalDateTime.now());
	        SubjectDto subjectDtoInfo = null;
	        if (chapterId != null && subjectDto != null) {
	            subjectDtoInfo = subjectServe.addSubject(subjectDto, chapterId);
	            if (subjectDtoInfo != null) {
	                response.setData(subjectDtoInfo);
	                response.setMsg("Data Add Successfully!");
	                response.setStatus("SUCCESS");
	                logger.info("Subject Add successful : {} : {}", subjectDtoInfo.getName(), LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<SubjectDto>>(response, HttpStatus.OK);
	            } else {
	                response.setData(subjectDtoInfo);
	                response.setMsg("Something went Wrong!!");
	                response.setStatus("INTERNAL_SERVER_ERROR");
	                logger.error("Subject Data failed for Save: {} : {}", subjectDto.getName(), LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<SubjectDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        } else {
	            response.setData(subjectDtoInfo);
	            response.setMsg("Invalid Parameters");
	            response.setStatus("BAD_REQUEST");
	            logger.warn("Invalid Subject parameters received : "+LocalDateTime.now());
	            return new ResponseEntity<GenericResponse<SubjectDto>>(response, HttpStatus.BAD_REQUEST);
	        }
	    }

//	    ------------------get All Subject info------------------------
	    @GetMapping("/get-all")
	    public ResponseEntity<GenericResponse<PageableDataResponse<List<Subject>>>> getAllSubjectInfo(
	            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
	            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
	            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
	            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
	            @RequestParam(required = false) String search) {

	        GenericResponse<PageableDataResponse<List<Subject>>> response = new GenericResponse<>();
	        
	        logger.warn("Fetching Subject with pageNumber={}, pageSize={}, sortBy={}, sortDir={}, search={} : {}", pageNumber, pageSize, sortBy, sortDir, search, LocalDateTime.now());
	        
	        try {
	            PageableDataResponse<List<Subject>> subjectPage = subjectServe.getAllSubjects(pageNumber, pageSize, sortBy, sortDir, search);
	            
	            if (subjectPage != null) {
	                response.setData(subjectPage);
	                response.setStatus("SUCCESS");
	                response.setMsg("Data fetched successfully!");
	                logger.info("Data fetched successfully! : {} ",LocalDateTime.now() );
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Subject>>>>(response, HttpStatus.OK);
	            } else {
	                response.setData(null);
	                response.setStatus("SUCCESS");
	                response.setMsg("No data available!");
	                logger.warn("No data available! : {} ", LocalDateTime.now());
	                return new ResponseEntity<GenericResponse<PageableDataResponse<List<Subject>>>>(response, HttpStatus.OK);
	            }
	        } catch (Exception e) {
	            response.setData(null);
	            response.setStatus("INTERNAL_SERVER_ERROR");
	            response.setMsg("An error occurred while fetching data!");
	            logger.error("An error occurred while fetching data : {}" ,LocalDateTime.now(), e);
	            return new ResponseEntity<GenericResponse<PageableDataResponse<List<Subject>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
