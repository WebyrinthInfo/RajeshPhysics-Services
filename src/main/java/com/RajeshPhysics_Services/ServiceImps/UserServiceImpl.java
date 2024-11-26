package com.RajeshPhysics_Services.ServiceImps;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RajeshPhysics_Services.Exceptions.ResourceAlreadyExistsException;
import com.RajeshPhysics_Services.Exceptions.ResourceNotFoundException;
import com.RajeshPhysics_Services.Models.Role;
import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Repositories.RoleRepository;
import com.RajeshPhysics_Services.Repositories.UserRepository;
import com.RajeshPhysics_Services.Services.UserService;
import com.RajeshPhysics_Services.Utils.JwtUtil;


@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class); 
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user, Long id) {
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(3);
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = dtf.format(plusDays);
		
		//create user and assign Role
			Optional<User> fbe = userRepo.findByMobile(user.getMobile());
			  boolean empty = fbe.isEmpty();
			if(empty == false) {
				throw new ResourceAlreadyExistsException("User is already exist : "+fbe.get().getMobile());
			}else {
				
//				---------------------get Role info ----------------------
				Role role2 = roleRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role is not Found  : "+id));
				List<Role>  roles = new ArrayList<>(); 
				roles.add(role2);
				
//				----------------------set user and access token -----------
			
				user.setMobile(user.getMobile().trim());
				user.setRoles(roles);
				user.setAccountExpireAt(formatedDate);
//				String activationToken = null;
//				if(role2.getName()=="STUDENT") {
//					activationToken = commonUtils.generateActivationToken();
//				}
//				user.set(activationToken);
				user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
				String generateToken = jwtUtil.generateToken(user);
				logger.info("Generate Token Successfully");
				user.setJwtToken(generateToken);
				User save = userRepo.save(user);
//				if(role2.getName()=="STUDENT") {
//					commonUtils.sendActivationEmail(user.getEmail(), activationToken);
//				}
				
				return save;
		}
	}

}
