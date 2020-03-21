package com.atlas.models;

import com.atlas.utils.CredsMasker;

import java.io.Serializable;

public class Visitor implements Serializable {
    private String userId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String address;
    private int routeID;
    private boolean applicationStatus;

    public Visitor(String userId, String password, String userName, String phoneNumber, String address, int routeID) {
        this.userId = userId;
        this.password = CredsMasker.encrypt(password);
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.routeID = routeID;
        this.applicationStatus = false;
    }

    public String getUserId() {
        return userId;
    }

    public boolean getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = CredsMasker.encrypt(password);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public String getPassword() {
        return CredsMasker.decrypt(password);
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", routeID=" + routeID +
                '}';
    }
}
