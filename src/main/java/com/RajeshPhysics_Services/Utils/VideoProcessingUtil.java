package com.RajeshPhysics_Services.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.RajeshPhysics_Services.Payloads.VideoProcessingResponse;


@Component
public class VideoProcessingUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoProcessingUtil.class);

    @Value("${files.hls.video}")
    private String HLS_VIDEO_DIR;
    
    @Value("${files.video}")
    private String VIDEO_DIR;
    

    public VideoProcessingResponse executeVideoProcessing(MultipartFile file) {
        VideoProcessingResponse response = new VideoProcessingResponse();
        try {
            // Validate input
            if (file == null || file.isEmpty()) {
                logger.error("File is null or empty");
                throw new IllegalArgumentException("File must not be null or empty");
            }

            // Generate a unique video ID
            String videoId = UUID.randomUUID().toString();
            response.setVideoId(videoId);
            response.setOriginalFileName(file.getOriginalFilename());
            response.setFileSize(file.getSize());

            // Define output directories for each resolution
            String output240p = HLS_VIDEO_DIR + videoId + "/240p/";
            String output360p = HLS_VIDEO_DIR + videoId + "/360p/";
            String output720p = HLS_VIDEO_DIR + videoId + "/720p/";
            String originalVideoDir = VIDEO_DIR + videoId + "/";

            // Create directories if they don't exist
            createDirectories(output240p, output360p, output720p, originalVideoDir);

            // Save the original video file
            String originalFilePath = originalVideoDir + file.getOriginalFilename();
            Files.copy(file.getInputStream(), Paths.get(originalFilePath), StandardCopyOption.REPLACE_EXISTING);
            logger.info("Saved original file to: {}", originalFilePath);
            response.setOriginalFilePath(originalFilePath);

            // Construct and execute the ffmpeg command
            String command = constructFfmpegCommand(originalFilePath, videoId);
            executeFfmpegCommand(command);

            response.setProcessingStatus("SUCCESS");
            response.setMessage("Video processing completed successfully");

        } catch (Exception e) {
            logger.error("Video processing failed", e);
            response.setProcessingStatus("FAILED");
            response.setMessage("Video processing failed: " + e.getMessage());
        }

        return response;
    }

    private void createDirectories(String... directories) throws IOException {
        for (String dir : directories) {
            File directory = new File(dir);
            if (!directory.exists()) {
                if (directory.mkdirs()) {
                    logger.info("Created directory: {}", dir);
                } else {
                    logger.error("Failed to create directory: {}", dir);
                }
            } else {
                logger.info("Directory already exists: {}", dir);
            }
        }
    }

    private String constructFfmpegCommand(String originalFilePath, String videoId) {
        return new StringBuilder()
            .append("ffmpeg -i ").append(originalFilePath).append(" ")
            .append("-map 0:v -map 0:a -s:v:0 426x240 -b:v:0 400k ")
            .append("-map 0:v -map 0:a -s:v:1 640x360 -b:v:1 800k ")
            .append("-map 0:v -map 0:a -s:v:2 1280x720 -b:v:2 2800k ")
            .append("-var_stream_map \"v:0,a:0 v:1,a:0 v:2,a:0\" ")
            .append("-master_pl_name ").append(HLS_VIDEO_DIR).append(videoId).append("/master.m3u8 ")
            .append("-f hls -hls_time 10 -hls_list_size 0 ")
            .append("-hls_segment_filename \"").append(HLS_VIDEO_DIR).append(videoId).append("/%v/prog_index%03d.ts\" ")
            .append(HLS_VIDEO_DIR).append(videoId).append("/master.m3u8")
            .toString();
    }

    private void executeFfmpegCommand(String command) throws IOException, InterruptedException {
        logger.info("Executing ffmpeg command: {}", command);
        ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logger.info(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("ffmpeg command failed with exit code " + exitCode);
        }

        logger.info("ffmpeg command executed successfully");
    }
}
