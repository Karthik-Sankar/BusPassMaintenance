package com.atlas.models;

import java.io.Serializable;

public class Visitor implements Serializable {
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

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
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
