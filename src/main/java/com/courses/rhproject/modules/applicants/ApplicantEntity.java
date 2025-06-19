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
@Builder
public class ApplicantEntity {
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

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StepResultEntity> stepResults = new HashSet<>();

    public Set<StepResultEntity> getStepResults() {
        return stepResults;
    }

    public void setStepResults(Set<StepResultEntity> stepResults) {
        this.stepResults = stepResults;
    }

    public void setApplicantId(UUID applicantId) {
        this.applicantId = applicantId;
    }

    public void setStatus(ApplicantStatus status) {
        this.status = status;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Id
    @GeneratedValue
    @Column(name = "uuid", updatable = false, unique = true)
    private UUID applicantId;

    public UUID getApplicantId() {
        return applicantId;
    }

    public ApplicantStatus getStatus() {
        return status;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public UserEntity getUser() {
        return user;
    }


}
