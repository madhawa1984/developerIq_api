package com.developeriq.metricesapi.services;

import com.developeriq.metricesapi.dto.CommitDetailsDto;
import com.developeriq.metricesapi.dto.IssuesDetailsDto;
import com.developeriq.metricesapi.model.*;
import com.developeriq.metricesapi.repository.CommitDetailsRepository;
import com.developeriq.metricesapi.repository.IssueDetailsRepository;
import com.developeriq.metricesapi.utils.GitHubServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricsService {
    @Autowired
    GitHubServices gitHubServices;
    @Autowired
    CommitDetailsRepository commitDetailsRepository;
    @Autowired
    IssueDetailsRepository issueDetailsRepository;

    public Mono<List<CommitDetailsDto>> fetchCommits(String owner, String reponame) {
        return  gitHubServices.getCommits(owner,reponame)
                .doOnSuccess(commitDetailsDtos -> commitDetailsDtos.forEach(commitDetailsDto -> {
                    CommitDetails commitDetails = new CommitDetails();
                    commitDetails.setShaValue(commitDetailsDto.getShaValue());
                    commitDetails.setNodeId(commitDetailsDto.getNodeId());
                    commitDetails.setUrl(commitDetailsDto.getUrl());
                    commitDetails.setHtmlUrl(commitDetailsDto.getHtmlUrl());
                    commitDetails.setParentCommits(commitDetailsDto.getParents()
                            .stream().map(parent -> {
                        ParentCommits parentCommits = new ParentCommits();
                        parentCommits.setSha(parent.getSha());
                        parentCommits.setHtmlUrl(parent.getHtmlUrl());
                        parentCommits.setUrl(parent.getUrl());
                        parentCommits.setCommitDetails(commitDetails);
                        return parentCommits;
                    }).collect(Collectors.toList()));
                    CommitDetailsDto.Commit.Person author = commitDetailsDto.getCommit().getAuthor();
                    commitDetails.setAuthor(PersonDetails.builder().name(author.getName())
                            .email(author.getEmail()).date(author.getDate()).authorDetails(commitDetails).build());
                    CommitDetailsDto.Commit.Person commiter = commitDetailsDto.getCommit().getCommitter();
                    commitDetails.setCommiter(PersonDetails.builder().name(commiter.getName())
                            .email(commiter.getEmail()).date(commiter.getDate()).committerDetails(commitDetails).build());
                    commitDetailsRepository.save(commitDetails);
                }))
                .doOnError(Throwable::printStackTrace);

    }

    public Mono<List<IssuesDetailsDto>> fetchListOfIssues(String owner, String reponame) {
        return gitHubServices.getIssues(owner,reponame)
                .doOnSuccess(issuesDetailsDtos -> {
                    issuesDetailsDtos.forEach(issuesDetailsDto -> {
                        IssuesDetailsDto.User user =  issuesDetailsDto.getUser();
                        UserDetails userDetails = UserDetails.builder()
                                .login(user.getLogin())
                                .nodeId(user.getNodeId())
                                .avatarUrl(user.getAvatarUrl())
                                .gravatarId(user.getGravatarId())
                                .url(user.getUrl())
                                .htmlUrl(user.getHtmlUrl())
                                .followUrl(user.getFollowers_url())
                                .gistsUrl(user.getGistsUrl())
                                .starredUrl(user.getStarredUrl())
                                .subscriptionsurl(user.getSubscriptionsUrl())
                                .organizationsUrl(user.getOrganizationsUrl())
                                .reposUrl(user.getOrganizationsUrl())
                                .eventsUrl(user.getEventsUrl())
                                .receivedEventsUrl(user.getReceivedeventsUrl())
                                .type(user.getType())
                                .build();
                        IssuesDetails.IssuesDetailsBuilder issuDetBuldr= IssuesDetails.builder()
                                .url(issuesDetailsDto.getUrl())
                                .number(issuesDetailsDto.getNumber())
                                .title(issuesDetailsDto.getTitle())
                                .repository_url(issuesDetailsDto.getRepoUrl())
                                .commentsUrl(issuesDetailsDto.getCommentsUrl())
                                .htmlUrl(issuesDetailsDto.getHtmlUrl())
                                .nodeId(issuesDetailsDto.getNodeId())
                                .state(issuesDetailsDto.getState())
                                .locked(issuesDetailsDto.isLocked())
                                .body(issuesDetailsDto.getBody())
                                .author_association(issuesDetailsDto.getAuthorAssociation())
                                .createdAt(issuesDetailsDto.getCreatedAt())
                                .milestone(issuesDetailsDto.getMilestone())
                                .user(userDetails)
                                .assignee(userDetails)
                                .assignees(Collections.singletonList(userDetails));
                        IssuesDetails issuesDetails = issuDetBuldr.build(); // THIS IS MAIN ENTITY
                        userDetails.setUserIssuesDetails(issuesDetails);
                        userDetails.setAssigneeIssuesDetails(issuesDetails);
                        userDetails.setAssigneesLst(issuesDetails);
                        issueDetailsRepository.save(issuesDetails);


                   });
                });
    }



}
