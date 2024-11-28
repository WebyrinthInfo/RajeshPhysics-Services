package com.RajeshPhysics_Services.Services;

import java.util.List;

import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;

public interface UserService {
	public User createUser(User user, Long roleId, Long courseId);
	public User updateUserToken(String userName, Long days, String isPaid);
	public PageableDataResponse<List<User>> getAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir, String search);

}
