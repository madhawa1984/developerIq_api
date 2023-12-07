package com.developeriq.metricesapi.model;

import com.developeriq.metricesapi.dto.CommitDetailsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.List;

@Entity
@Table(name = "COMMIT_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommitDetails {
    @Id
    @GeneratedValue
    @Column(name = "COMMIT_ID")
    private Long id;
    @Column(name = "SHA_VALUE")
    private String shaValue;
    @Column(name = "NODE_ID")
    private String nodeId;
    private String url;
    @Column(name = "HTML_URL")
    private String htmlUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "id")
    private  PersonDetails author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMMITER_ID", referencedColumnName = "id")
    private  PersonDetails commiter;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commitDetails", cascade = CascadeType.ALL)
    private List<ParentCommits> parentCommits;
}
