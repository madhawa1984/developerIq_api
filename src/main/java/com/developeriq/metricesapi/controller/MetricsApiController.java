package com.developeriq.metricesapi.controller;


import com.developeriq.metricesapi.dto.CommitDetailsDto;
import com.developeriq.metricesapi.dto.IssuesDetailsDto;
import com.developeriq.metricesapi.dto.PullRequestDto;
import com.developeriq.metricesapi.utils.GitHubServices;
import com.developeriq.metricesapi.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricsApiController {

    @Autowired
    private GitHubServices gitHubServices;

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/commits/{owner}/{reponame}")
    public Mono<List<CommitDetailsDto>> fetchCommits(@PathVariable("owner") String owner,
                                                      @PathVariable("reponame") String repoName) {
        return metricsService.fetchCommits(owner,repoName);
    }
    // Add End points to get data from the DB
    @GetMapping("/issues/{owner}/{reponame}")
    public Mono<List<IssuesDetailsDto>> listIssuesBydeveloper(@PathVariable("owner") String owner,
                                                              @PathVariable("reponame") String repoName){
        return gitHubServices.getIssues(owner,repoName);
    }

    @GetMapping("/pulls/{owner}/{reponame}")
    public Mono<List<PullRequestDto>> listPullRequestsBydeveloper(@PathVariable("owner") String owner,
                                                                  @PathVariable("reponame") String repoName){
        return gitHubServices.getPullRequest(owner,repoName);
    }

}
