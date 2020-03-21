package com.atlas.models;

import com.atlas.utils.CredsMasker;

import java.io.Serializable;

public class User implements Serializable {
    private BusPass busPass;
    private String userId;
    private String userName;
    private String password;
    private String address;
    private String phoneNumber;
    private int routeNum;
    private double travelCost;

    public User(BusPass busPass, String userId, String password, String userName, String phoneNumber, String address,
                int routeNum) {
        this.busPass = busPass;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.routeNum = routeNum;
        this.address = address;
        this.password = CredsMasker.encrypt(password);
    }

    public BusPass getBusPass() {
        return busPass;
    }

    public void setBusPass(BusPass busPass) {
        this.busPass = busPass;
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

    public double getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(double travelCost) {
        this.travelCost = travelCost;
    }

    @Override
    public String toString() {
        return "User{" +
                "busPass=" + busPass +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", routeNum=" + routeNum +
                ", travelCost=" + travelCost +
                '}';
    }
}
