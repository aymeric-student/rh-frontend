package com.courses.rhproject.modules.jobOffer;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {
    @EntityGraph(attributePaths = {"workflow", "workflow.steps"})
    List<JobOffer> findAll();

    @EntityGraph(attributePaths = {"workflow", "workflow.steps"})
    Optional<JobOffer> findById(UUID id);
}
