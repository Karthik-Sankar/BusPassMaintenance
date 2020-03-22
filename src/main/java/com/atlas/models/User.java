package com.atlas.models;

import com.atlas.utils.CredsMasker;

import java.io.Serializable;

public class User implements Serializable {
    private int busPassID;
    private String userId;
    private String userName;
    private String password;
    private String address;
    private String phoneNumber;
    private int routeNum;

    public User(int busPassID, String userId, String password, String userName, String phoneNumber, String address,
                int routeNum) {
        this.busPassID = busPassID;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.routeNum = routeNum;
        this.address = address;
        this.password = CredsMasker.encrypt(password);
    }

    public int getBusPass() {
        return busPassID;
    }

    public void setBusPass(int busPassID) {
        this.busPassID = busPassID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return CredsMasker.decrypt(password);
    }

    public void setPassword(String password) {
        this.password = CredsMasker.encrypt(password);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(int routeNum) {
        this.routeNum = routeNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "busPassID=" + busPassID +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", routeNum=" + routeNum +
                '}';
    }
}
