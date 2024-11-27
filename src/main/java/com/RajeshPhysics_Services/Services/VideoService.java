package com.RajeshPhysics_Services.Services;

import org.springframework.web.multipart.MultipartFile;

import com.RajeshPhysics_Services.Models.Video;

public interface VideoService {
	
	public Video saveVideo(Video video, MultipartFile file);

}
