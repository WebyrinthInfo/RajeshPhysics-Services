package com.RajeshPhysics_Services.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RajeshPhysics_Services.Payloads.VideoProcessingResponse;
import com.RajeshPhysics_Services.Utils.VideoProcessingUtil;

@RestController
@RequestMapping("/api/video")
@CrossOrigin("*")
public class VideoController {
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	 @Autowired
	 private VideoProcessingUtil vpu;

	    @PostMapping("/upload")
	    public ResponseEntity<VideoProcessingResponse> uploadVideo(@RequestParam("file") MultipartFile file) {
	        VideoProcessingResponse response = vpu.executeVideoProcessing(file);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }

}
