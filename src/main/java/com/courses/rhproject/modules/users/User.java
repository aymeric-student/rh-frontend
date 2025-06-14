package com.courses.rhproject.modules.users;

import com.courses.rhproject.modules.applicants.ApplicantEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    private String password;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-applicant")
    private List<ApplicantEntity> applications = new ArrayList<>();

    public boolean isRecruiter() {
        return role == RoleType.RECRUITER;
    }

    public boolean isCandidate() {
        return role == RoleType.CANDIDATE;
    }
}
