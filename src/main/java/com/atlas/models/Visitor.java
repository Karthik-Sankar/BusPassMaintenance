package com.atlas.models;

public class Visitor {
    private String userId;
    private String userName;
    private String phoneNumber;
    private String source;
    private String destination;

    public Visitor(String userId, String userName, String phoneNumber, String source, String destination) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.source = source;
        this.destination = destination;
    }

    public String getUserId() {
        return userId;
    }

    public String toString() {
        return "Visitor{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
