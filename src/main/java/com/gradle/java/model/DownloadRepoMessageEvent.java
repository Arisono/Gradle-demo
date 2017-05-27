package com.gradle.java.model;

public class DownloadRepoMessageEvent {
    public String message;

    public DownloadRepoMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
