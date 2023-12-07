package com.developeriq.metricesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PullRequestDto {
    private String url;
    private int id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("html_url")
    private String htmllUrl;
    @JsonProperty("diff_url")
    private String diffUrl;
    @JsonProperty("patch_url")
    private String patchUrl;
    @JsonProperty("issue_url")
    private String issueUrl;
    private int number;
    private String state;
    private boolean locked;
    private String title;
    private User user;

    @Data
    private static class User {
        private  String login;
        private int id;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatar_id;
        private String url;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followers_url;
        @JsonProperty("following_url")
        private String followersUrl;
        @JsonProperty("gists_url")
        private String gists_url;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String repos_url;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedEventsUrl;
        private  String type;
        @JsonProperty("site_admin")
        private String siteAdmin;

    }
    @Data
    private static class Head {
        private String label;
        private String ref;
        private String sha;
        private User user;

        @Data
        private static class User {
            private  String login;
            private int id;
            private String node_id;
            @JsonProperty("avatar_url")
            private String avatarUrl;

            @JsonProperty("gravatar_id")
            private int gravatarId;
            private String url;
            @JsonProperty("html_url")
            private String htmlUrl;
            @JsonProperty("followers_url")
            private String followersUrl;
            @JsonProperty("following_url")
            private String followingUurl;
            @JsonProperty("gists_url")
            private String gistsUrl;
            private String  gistTul;
            @JsonProperty("starred_url")
            private String starredUrl;
            @JsonProperty("subscriptions_url")
            private String subscriptionsUrl;

            private String  type;
            @JsonProperty("site_admin")
            private String siteAdmin;
        }

    }
}
