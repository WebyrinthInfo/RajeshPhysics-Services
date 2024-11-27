package com.RajeshPhysics_Services.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Models.Video;
@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

}
