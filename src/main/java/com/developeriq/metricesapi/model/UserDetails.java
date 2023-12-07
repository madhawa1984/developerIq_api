package com.developeriq.metricesapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String url;
    private String type;
    @Column(name = "NODE_ID")
    private String nodeId;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @Column(name = "gravatar_id")
    private String gravatarId;
    @Column(name = "html_url")
    private String htmlUrl;
    @Column(name = "follow_url")
    private String followUrl;
    @Column(name = "gists_url")
    private String gistsUrl;
    @Column(name="starred_url")
    private String starredUrl;
    @Column(name = "subscriptions_url")
    private String subscriptionsurl;
    @Column(name = "organizations_url")
    private String organizationsUrl;
    @Column(name ="repos_url")
    private String reposUrl;
    @Column(name ="events_url")
    private String eventsUrl;
    @Column( name ="received_events_url")
    private String receivedEventsUrl;
    @OneToOne(mappedBy = "user")
    private IssuesDetails userIssuesDetails;
    @OneToOne(mappedBy = "assignee")
    private IssuesDetails assigneeIssuesDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    // once this value changeed to df=ifferent id exception throwa
    // further when it has id, it will not fIll the column
    @JoinColumn(name = "ASSIGNEE_IXXXX", referencedColumnName = "id")
    private IssuesDetails assigneesLst;



}
