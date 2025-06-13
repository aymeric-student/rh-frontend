package com.courses.rhproject.modules.applicants;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, UUID> {
}
