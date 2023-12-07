package com.developeriq.metricesapi.repository;

import com.developeriq.metricesapi.model.IssuesDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueDetailsRepository extends JpaRepository<IssuesDetails, Long> {
}
