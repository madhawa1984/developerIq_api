package com.developeriq.metricesapi.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommitDetailsDto implements Serializable {
    @JsonProperty("sha")
    private String shaValue;
    @JsonProperty("node_id")
    private String nodeId;

    private Commit commit;

    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;

    // private  String author;
    private  String commiter;

    private List<Commit.Parent> parents;

    @JsonProperty("comments_url")
    private String commentsUrl;
    @Data
    private static class Commit {
        private Author author;
        // private Commit.Person committer;
        private String message;
        private Tree tree;
        private String url;
        @JsonProperty("comment_count")
        private int commentCount;
        private Verification verification;
        @Data
        private static class  Author {
            private String name;
            private  String email;
            private String date;
        }
        @Data
        private static class Tree {

            private String sha;
            private String url;
        }
        @Data
        private static class Verification{
            private boolean verified;

            private  String reason;
            private String signature;
            private String payload;
        }
        @Data
        private static class Parent {
            private String sha;

            private String url;
            @JsonProperty("html_url")
            private String htmlUrl;
        }
    }




}
