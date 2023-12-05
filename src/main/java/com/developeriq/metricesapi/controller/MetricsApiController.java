package com.developeriq.metricesapi.controller;


import com.developeriq.metricesapi.dto.CommitDetailsDto;
import com.developeriq.metricesapi.services.GitHubServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricsApiController {

    @Autowired
    private GitHubServices gitHubServices;

    @GetMapping("/commits/{owner}/{reponame}")
    public Mono<List<CommitDetailsDto>> listofCommits(@PathVariable("owner") String owner,
                                                      @PathVariable("reponame") String repoName) {
        return gitHubServices.getCommits(owner,repoName);
        // save the data in to the DB level

    }
    // Add End points to get data from the DB

}
