package com.courses.rhproject.modules.jobOffer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {

    @Query("""
        SELECT DISTINCT jo 
        FROM JobOffer jo 
        LEFT JOIN FETCH jo.workflow w
        LEFT JOIN FETCH jo.enterprise e
        """)
    List<JobOffer> findAllWithDetails();

}
