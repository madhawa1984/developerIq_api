package com.developeriq.metricesapi.services;

import com.developeriq.metricesapi.dto.CommitDetailsDto;
import com.developeriq.metricesapi.model.CommitDetails;
import com.developeriq.metricesapi.model.ParentCommits;
import com.developeriq.metricesapi.model.PersonDetails;
import com.developeriq.metricesapi.repository.CommitDetailsRepository;
import com.developeriq.metricesapi.utils.GitHubServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricsService {
    @Autowired
    GitHubServices gitHubServices;
    @Autowired
    CommitDetailsRepository commitDetailsRepository;

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
                                // Add lombok builder
                                System.out.println("Iterate through parent commits");
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
                }));


    }



}
