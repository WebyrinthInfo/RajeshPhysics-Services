package com.RajeshPhysics_Services.ServiceImps;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.RajeshPhysics_Services.Exceptions.ResourceNotFoundException;
import com.RajeshPhysics_Services.Models.Video;
import com.RajeshPhysics_Services.Repositories.VideoRepository;
import com.RajeshPhysics_Services.Services.VideoService;

import jakarta.annotation.PostConstruct;

@Service
public class VideoServiceImpl implements VideoService {
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Value("${files.video}")
    private String VIDEO_DIR;
    
    @Value("${files.hls.video}")
    private String HLS_VIDEO_DIR;

    @Autowired
    private VideoRepository videoRepo;

    @PostConstruct
    public void init() {
        File file = new File(VIDEO_DIR);
        if (!file.exists()) {
            boolean created = file.mkdir();
            if (created) {
                logger.info("Video directory created at {}", VIDEO_DIR);
            } else {
                logger.error("Failed to create video directory at {}", VIDEO_DIR);
            }
        }else {
        	logger.info("Video directory already exits {}", VIDEO_DIR);
        }
    }

    @Override
    public Video saveVideo(Video video, MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new ResourceNotFoundException("Uploaded file is empty");
            }

            String originalFileName = file.getOriginalFilename();
            if (originalFileName == null) {
                throw new ResourceNotFoundException("Uploaded file has no name");
            }
            
            String orignalFileName = file.getOriginalFilename();
			String fileContentType = file.getContentType();
			Long fileSize = file.getSize();
			InputStream inputStream = file.getInputStream();

            String cleanFolder = StringUtils.cleanPath(VIDEO_DIR);
            String cleanFileName = StringUtils.cleanPath(originalFileName);
            Path path = Paths.get(cleanFolder, cleanFileName);

            // Save file to disk
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
           
            
//            
           
            // Save video metadata to the database
            Video savedVideo = videoRepo.save(video);
            logger.info("Video saved with ID: {}", savedVideo.getVideoId());

            return savedVideo;
        } catch (IOException e) {
            logger.error("Failed to save video file", e);
            throw new RuntimeException("Failed to save video file", e);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid file upload request", e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while saving video", e);
            throw new RuntimeException("Unexpected error occurred while saving video", e);
        }
    }
    
    
}
