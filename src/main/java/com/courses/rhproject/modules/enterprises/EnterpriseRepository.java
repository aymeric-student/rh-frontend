package com.courses.rhproject.modules.enterprises;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID> {

    @Query("""
    SELECT DISTINCT e
    FROM Enterprise e
    LEFT JOIN FETCH e.jobOffers jo
    LEFT JOIN FETCH jo.workflow w
    WHERE e.enterpriseId = :enterpriseId
""")
    Optional<Enterprise> findByIdWithOffersAndWorkflow(UUID enterpriseId);
}
