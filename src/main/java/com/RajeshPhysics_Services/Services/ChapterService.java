package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Dtos.ChapterDto;
import com.RajeshPhysics_Services.Models.Chapter;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface ChapterService {
	public ChapterDto addChapter(ChapterDto ChapterDto, Long chapterTopicId);
	public PageableDataResponse<List<Chapter>> getAllChapters(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);
}
