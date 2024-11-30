package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Dtos.SubjectDto;
import com.RajeshPhysics_Services.Models.Subject;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface SubjectService {
	public SubjectDto addSubject(SubjectDto subjectDto, Long subjectId);
	public PageableDataResponse<List<Subject>> getAllSubjects(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);
}