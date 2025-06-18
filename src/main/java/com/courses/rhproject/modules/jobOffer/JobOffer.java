package com.courses.rhproject.modules.jobOffer;

import com.courses.rhproject.core.BaseEntity;
import com.courses.rhproject.modules.applicants.ApplicantEntity;
import com.courses.rhproject.modules.enterprises.Enterprise;
import com.courses.rhproject.modules.workflows.WorkflowEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_offer")
public class JobOffer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true)
    private UUID id;

    private String title;
    private String description;
    private BigDecimal salary;
    private String location;
    private LocalDate publicationDate;
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workflow_id")
    private WorkflowEntity workflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @JsonBackReference(value = "enterprise-joboffers")
    private Enterprise enterprise;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "job-offer-applicants")
    private List<ApplicantEntity> applicantEntities = new ArrayList<>();
}
