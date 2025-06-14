package com.courses.rhproject.modules.step;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StepRepository extends JpaRepository<StepEntity, UUID> {
}

