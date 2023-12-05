package com.developeriq.metricesapi.services;

import com.developeriq.metricesapi.dto.CommitDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

import static com.developeriq.metricesapi.constant.Constant.*;

@Service
public class GitHubServices {
    @Autowired
    @Qualifier("gitHubclient")
    private WebClient webClient;


    public Mono<List<CommitDetailsDto>> getCommits(String owner, String reponame) {
         return webClient.get().uri(uriBuilder -> uriBuilder.path("/repos/".concat(owner)
                        .concat("/").concat(reponame).concat("/commits")).build())
                .headers(httpHeaders -> {
                    httpHeaders.add(ACCEPT_HEADER, GITHUB_CONTENT_TYPE);
                    httpHeaders.add(GITHUB_API_VERSION_HEADER, GITHUB_API_VERSION);
                }).retrieve().bodyToFlux(CommitDetailsDto.class).collectList()
                 .doOnError(Throwable::printStackTrace);
               // TODO Improve this code to give the each users commit lists
               // TODO Generate a Graph using above stats

    }

    public void getIssues(String owner, String repoName) {

    }

    public void getPullRequest() {

    }

}
