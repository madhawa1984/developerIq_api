package com.developeriq.metricesapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;


@Entity
@Table(name = "ISSUES_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssuesDetails {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String url;
    @Column(name = "REPOSITORY_URL")
    private String repository_url;
    @Column(name = "LABELS_URL")
    private String labelsUrl;
    @Column(name = "COMMENTS_URL")
    private String commentsUrl;
    @Column(name = "HTML_URL")
    private String htmlUrl;
    private int number;
    private String title;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private UserDetails user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ASSIGNEE_ID", referencedColumnName = "id")
    private UserDetails assignee;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "assigneesLst", cascade = CascadeType.ALL)
    private List<UserDetails> assignees;
    @Column(name = "NODE_ID")
    private String nodeId;
    private String name;
    private  String color;
    @Column(name = "DEFAULT_VAL")
    private boolean defaultVal;
    private String description;
    private String state;
    private boolean locked;
    private String milestone;
    private String comments;
    @Column(name = "CREATED_AT")
    private String createdAt;
    @Column(name = "UPDATED_AT")
    private String updatedAt;
    private String body;
    @Column(name = "AUTHOR_ASSOCIATION")
    private String author_association;

}
