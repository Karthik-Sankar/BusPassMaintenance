package com.atlas.models;

public class User {
    private BusPass busPass;
    private String userId;
    private String userName;
    private String phoneNumber;
    private int routeNum;

    public User(BusPass busPass, String userId, String userName, String phoneNumber, int routeNum) {
        this.busPass = busPass;
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.routeNum = routeNum;
    }

    public BusPass getBusPass() {
        return busPass;
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

    public int getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(int routeNum) {
        this.routeNum = routeNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "busPassId='" + busPass + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", routeNum=" + routeNum +
                '}';
    }
}
