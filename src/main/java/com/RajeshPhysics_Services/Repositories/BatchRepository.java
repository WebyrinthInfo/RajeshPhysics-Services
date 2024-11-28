package com.RajeshPhysics_Services.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RajeshPhysics_Services.Models.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

	@Query(value = "SELECT * FROM batches WHERE batch_code = :batchCode", nativeQuery = true)
	Optional<Batch> findByBatchCode(@Param("batchCode") String batchCode);

}
