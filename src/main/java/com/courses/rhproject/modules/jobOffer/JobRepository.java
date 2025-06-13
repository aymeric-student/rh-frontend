package com.courses.rhproject.modules.jobOffer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobOffer, UUID> {
}
