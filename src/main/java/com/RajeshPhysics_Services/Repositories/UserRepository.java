package com.RajeshPhysics_Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByMobile(String string);

}
