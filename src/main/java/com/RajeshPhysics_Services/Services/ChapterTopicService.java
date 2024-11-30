package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Dtos.ChapterTopicDto;
import com.RajeshPhysics_Services.Models.ChapterTopic;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface ChapterTopicService {
    public ChapterTopicDto addChapterTopic(ChapterTopicDto chapterTopicDto);
    public PageableDataResponse<List<ChapterTopic>> getAllChapterTopics(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir, String search);
}
