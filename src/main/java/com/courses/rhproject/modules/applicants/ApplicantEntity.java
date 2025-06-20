package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.stepResult.StepResultEntity;
import com.courses.rhproject.modules.users.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "applicants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApplicantEntity {

    @Id
    @GeneratedValue
    @Column(name = "uuid", updatable = false, unique = true)
    private UUID applicantId;

    @Enumerated(EnumType.STRING)
    private ApplicantStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_offer_id")
    @JsonBackReference(value = "job-offer-applicant")
    private JobOffer jobOffer;

    private LocalDate applicationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-applicant")
    private UserEntity user;
}
