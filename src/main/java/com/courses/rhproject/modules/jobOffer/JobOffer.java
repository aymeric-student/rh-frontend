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

    public ContractType getContractType() {
        return contractType;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public void setWorkflow(WorkflowEntity workflow) {
        this.workflow = workflow;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setApplicantEntities(List<ApplicantEntity> applicantEntities) {
        this.applicantEntities = applicantEntities;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public WorkflowEntity getWorkflow() {
        return workflow;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public List<ApplicantEntity> getApplicantEntities() {
        return applicantEntities;
    }

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
