package com.courses.rhproject.modules.jobOffer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {
}
