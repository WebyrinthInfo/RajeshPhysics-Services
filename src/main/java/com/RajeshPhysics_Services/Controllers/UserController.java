package com.RajeshPhysics_Services.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Payloads.PageableDataResponse;
import com.RajeshPhysics_Services.Services.UserService;

@RequestMapping("/api/user/")
@RestController
@CrossOrigin("*")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>> getPaidStudent(
            @RequestParam(defaultValue = AppConstrants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstrants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = AppConstrants.SORT_BY_ID, required = false) String sortBy,
            @RequestParam(defaultValue = AppConstrants.SORT_DIR_DESC, required = false) String sortDir,
            @RequestParam(required = false) String search) {

        GenericResponse<PageableDataResponse<List<User>>> response = new GenericResponse<>();
        
        logger.warn("Fetching users with pageNumber={}, pageSize={}, sortBy={}, sortDir={}, search={}", pageNumber, pageSize, sortBy, sortDir, search);
        
        try {
            PageableDataResponse<List<User>> userPage = userService.getAllUser(pageNumber, pageSize, sortBy, sortDir, search);
            
            if (userPage != null) {
                response.setData(userPage);
                response.setStatus("SUCCESS");
                response.setMsg("Data fetched successfully!");
                logger.info("Data fetched successfully!");
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.OK);
            } else {
                response.setData(null);
                response.setStatus("SUCCESS");
                response.setMsg("No data available!");
                logger.warn("No data available!");
                return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setData(null);
            response.setStatus("INTERNAL_SERVER_ERROR");
            response.setMsg("An error occurred while fetching data!");
            logger.error("An error occurred while fetching data", e);
            return new ResponseEntity<GenericResponse<PageableDataResponse<List<User>>>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}