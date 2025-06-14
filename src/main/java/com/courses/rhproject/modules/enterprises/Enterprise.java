package com.courses.rhproject.modules.enterprises;

import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enterprise")
public class Enterprise {

    @Id
    @GeneratedValue
    @Column(name = "uuid", updatable = false, unique = true)
    private UUID enterpriseId;

    private String name;
    private String description;
    private String website;
    private String phoneNumber;
    private String siret;
    private String industry;
    private String headquartersLocation;
    private Integer numberOfEmployees;
    private Integer foundedYear;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "enterprise-joboffers")
    private List<JobOffer> jobOffers = new ArrayList<>();
}
