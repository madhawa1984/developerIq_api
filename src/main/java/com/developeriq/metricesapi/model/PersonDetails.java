package com.developeriq.metricesapi.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "PERSON_DETAILS")
public class PersonDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String date;
    @OneToOne(mappedBy = "author" )
    private CommitDetails authorDetails;
    @OneToOne(mappedBy = "commiter" )
    private CommitDetails committerDetails;
}
