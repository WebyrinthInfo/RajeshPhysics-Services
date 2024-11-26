package com.RajeshPhysics_Services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.RajeshPhysics_Services.Models.Role;
import com.RajeshPhysics_Services.Models.User;
import com.RajeshPhysics_Services.Repositories.RoleRepository;
import com.RajeshPhysics_Services.Repositories.UserRepository;

@SpringBootApplication
public class RajeshPhysicsServicesApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(RajeshPhysicsServicesApplication.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(RajeshPhysicsServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		------------------adding role and user for development and Maintenance-------------------------
//		addRoleAndUser();
	}
	
	public void addRoleAndUser() {
		LocalDate currentDate = LocalDate.now();
		LocalDate plusDays = currentDate.plusDays(3);
		DateTimeFormatter  dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = dtf.format(plusDays);
		
		logger.info("Checking user is exit or Not");
		Optional<User> info = userRepo.findByMobile("9939718838");
		if(!info.isPresent()) {
			logger.info("adding user .........");
			//----------------------Adding WY by Role ---------------
			List<Role> roles = new ArrayList<>();
			Role role1 = new Role();
			role1.setId(1L);
			role1.setName("WY");
			role1.setDescription("this roles allow all apis to access.");
			roles.add(role1);

//			----------------------------------Adding  user and wy Role By Default---------------------
			User userInfo = new User();
			userInfo.setId(1L);
			userInfo.setFirstName("webyrith");
			userInfo.setMiddleName("(P)");
			userInfo.setLastName("Ltd.");
			userInfo.setAddress("Santi nagar jehanabad");
			userInfo.setIsActive(1);
			userInfo.setStatus("ACTIVE");
			userInfo.setMobile("9939718838");
			userInfo.setAccountExpireAt(formatedDate);
			userInfo.setPassword(passwordEncoder.encode("India@123"));
			userInfo.setLanguageMode("Hinglish");
			userInfo.setRoles(roles);
		
			userRepo.save(userInfo);
			logger.info("User Save SuccessFully : {}", userInfo.getMobile());
		}else {
			logger.warn("User is already added : {}", info.get().getMobile());
		}
		
	}

}
