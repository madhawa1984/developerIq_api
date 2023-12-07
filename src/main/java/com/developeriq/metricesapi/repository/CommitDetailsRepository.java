package com.developeriq.metricesapi.repository;

import com.developeriq.metricesapi.model.CommitDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommitDetailsRepository extends JpaRepository<CommitDetails, Long> {

}
