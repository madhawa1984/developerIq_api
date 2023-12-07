package com.developeriq.metricesapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.core.SpringVersion;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssuesDetailsDto {
    private String url;
    private int id;
    private int number;
    private String title;
    private User user;
    private List<Labels> labels;
    private String state;
    private boolean locked;
    private List<User> assignees;
    private String comments;
    private String body;
    @JsonProperty("repository_url")
    private String repoUrl;
    @JsonProperty("labels_url")
    private String labelsUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("node_id")
    private String nodeId;
    private String milestone;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("closed_at")
    private String closedAt;
    @JsonProperty("author_association")
    private String authorAssociation;
    @JsonProperty("active_lock_reason")
    private String active_lock_reason;
    // private Reactions reactions;
    @JsonProperty("timelineUrl")
    private String timelineUrl;
    @JsonProperty("performed_via_github_app")
    private String performed_via_github_app;
    @JsonProperty("state_reason")
    private String stateReason;

    @Data
    public static class User {
        private String login;
        private String id;
        private String url;
        private String type;
        @JsonProperty("node_id")
        private String nodeId;
        @JsonProperty("avatar_url")
        private String avatarUrl;
        @JsonProperty("gravatar_id")
        private String gravatarId;
        @JsonProperty("html_url")
        private String htmlUrl;
        @JsonProperty("followers_url")
        private String followers_url;
        @JsonProperty("following_url")
        private  String followingUrl;
        @JsonProperty("gists_url")
        private String gistsUrl;
        @JsonProperty("starred_url")
        private String starredUrl;
        @JsonProperty("subscriptions_url")
        private String subscriptionsUrl;
        @JsonProperty("organizations_url")
        private String organizationsUrl;
        @JsonProperty("repos_url")
        private String reposUrl;
        @JsonProperty("events_url")
        private String eventsUrl;
        @JsonProperty("received_events_url")
        private String receivedeventsUrl;
        @JsonProperty("site_admin")
        private  String siteAdmin;
    }

    @Data
    private static class Labels {
        private String id;
        @JsonProperty("node_id")
        private String nodeId;
        private String url;
        private String name;
        private  String color;
        @JsonProperty("default")
        private boolean defaultVal;
        private String description;
    }

    @Data
    private static class Reactions {
    private  String url;
    @JsonProperty("total_count")
    private String totalcount;
    @JsonProperty("+1")
    private boolean positiveOne;
    @JsonProperty("-1")
    private boolean negativeOne;
    private boolean laugh;
    private boolean hooray;
    private boolean confused;
    private  boolean heart;
    private  boolean rocket;
    private  boolean eyes;

    }


}
