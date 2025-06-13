package com.courses.rhproject.modules.applicants;

import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.users.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "applicants")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @JsonIgnore
    private JobOffer jobOffer;

    private LocalDate applicationDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
