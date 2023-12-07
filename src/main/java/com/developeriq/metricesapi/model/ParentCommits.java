package com.developeriq.metricesapi.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "PARENT_COMMIT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentCommits {
    @Id
    @GeneratedValue
    private Long id;
    private String sha;
    private String url;
    @Column(name = "HTML_URL")
    private String htmlUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMIT_ID")
    private CommitDetails commitDetails;
}
