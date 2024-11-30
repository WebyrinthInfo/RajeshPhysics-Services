package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Dtos.BatchDto;
import com.RajeshPhysics_Services.Models.Batch;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface BatchService {
	 public BatchDto addBatch(BatchDto batchDto, Long subjectId);
	 public PageableDataResponse<List<Batch>> getAllBatch(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);

}
