package com.RajeshPhysics_Services.Controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RajeshPhysics_Services.Exceptions.AuthenticationException;
import com.RajeshPhysics_Services.Exceptions.ForbbidonExceptions;
import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Payloads.AppConstrants;
import com.RajeshPhysics_Services.Payloads.AuthRequest;
import com.RajeshPhysics_Services.Payloads.GenericResponse;
import com.RajeshPhysics_Services.Repositories.UserRepository;
import com.RajeshPhysics_Services.Services.UserService;
import com.RajeshPhysics_Services.Utils.JwtUtil;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api/ajax")
@CrossOrigin("*")
public class AjaxController {
private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private UserDetailsService uds;
	
	@Autowired
	private AuthenticationManager am;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userServe;
	
	@PostMapping("/registration")
	public ResponseEntity<GenericResponse<User>> createUserAndAssignRole(@RequestBody User user,
	@RequestParam(name="roleId", defaultValue =AppConstrants.STUDENT_ROLE_ID,required = false) Long id,
	GenericResponse<User> response
	){
		User userInfo = null;
		if(user!=null && id !=null) {
			 userInfo = userServe.createUser(user, id);
			 if(userInfo!=null) {
				 response.setData(userInfo);
				 response.setMsg("Registration Successfully");
				 response.setStatus("SUCCESS");
				 return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
			 }else {
				 response.setData(userInfo);
				 response.setMsg("Something went Wrong!!");
				 response.setStatus("INTERNAL_SERVER_ERROR");
				 return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			 }
		}else {
			response.setData(userInfo);
			response.setMsg("Invalid Parameters");
			response.setStatus("BAD_REQUEST");
			return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	//-------------{ login }------------------------------------------
		@PostMapping("/login")
		public ResponseEntity<GenericResponse<User>> loginUser(@RequestBody AuthRequest authRequest, GenericResponse<User> response, HttpSession session) throws Exception{
			if(authRequest!=null) {
				authenticate(authRequest.getUsername().trim(), authRequest.getPassword().trim());
				UserDetails ud = uds.loadUserByUsername(authRequest.getUsername().trim());
				User user = userRepo.findByMobile(ud.getUsername()).get();
				try {
					Boolean validateToken = jwtutil.validateToken(user.getJwtToken(), user);
					 int isActive = user.getIsActive();
					 
					if(validateToken == true && isActive == 1) {
						session.setAttribute("userInfo", user);
						response.setData(user);
						response.setStatus("SUCCESS");
						response.setMsg("Login Successfully !!");
						return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
					}else {
						response.setData(null);
						response.setStatus("SUCCESS");
						response.setMsg("User is InActive !");
						return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
					}
				} catch (Exception e) {
					throw new AuthenticationException("Your Subscription has been Expired !");
//					throw new Exception();
				}
				
			}else {
				response.setData(null);
				response.setMsg("Invalid Parameters");
				response.setStatus("BAD_REQUEST");
				return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.BAD_REQUEST);
			}
		}
		private void authenticate(String username, String password) throws Exception {
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(username, password);
			try {
				am.authenticate(upat);
			} catch (BadCredentialsException e) {
				throw new AuthenticationException("Invlaid username or password");
			}catch (DisabledException e) {
				throw new AuthenticationException("Disable username or password");
			}
		}
		
//		-------------{ update user token }-----------------------------------
		@PutMapping("/updateToken")
		public ResponseEntity<GenericResponse<User>> updateUserToken(@RequestParam ("username") String username, @RequestParam ("days") Long days, @RequestParam (name="isPaid", defaultValue = AppConstrants.FREE_STUDENT) String isPaid, GenericResponse<User> response){
			User userInfo = null;
			if(username!=null && days !=null) {
				 userInfo = userServe.updateUserToken(username, days, isPaid);
				 if(userInfo!=null) {
					 response.setData(userInfo);
					 response.setMsg("Token update Successfully Successfully for "+days+" Days");
					 response.setStatus("SUCCESS");
					 return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.OK);
				 }else {
					 response.setData(userInfo);
					 response.setMsg("Something went Wrong!!");
					 response.setStatus("INTERNAL_SERVER_ERROR");
					 return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				 }
			}else {
				response.setData(userInfo);
				response.setMsg("Invalid Parameters");
				response.setStatus("BAD_REQUEST");
				return new ResponseEntity<GenericResponse<User>>(response, HttpStatus.BAD_REQUEST);
			}
			
		}
		
}
