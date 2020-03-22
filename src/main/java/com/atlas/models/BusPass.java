package com.atlas.models;

import com.atlas.utils.BusPassConstants;

import java.io.Serializable;

public class BusPass implements Serializable {
    private int busPassId;
    private int routeId;
    private String userId;
    private int busID;
    private int busPassStatus;

    public BusPass(int busPassId, int routeId, String userId, int busID) {
        this.busPassId = busPassId;
        this.routeId = routeId;
        this.userId = userId;
        this.busID = busID;
        busPassStatus = BusPassConstants.ACTIVE;
    }

    public int getBusPassId() {
        return busPassId;
    }

    public int getBusPassStatus() {
        return busPassStatus;
    }

    @Override
    public String toString() {

        return "BusPass{" +
                "busPassId=" + busPassId +
                ", routeId=" + routeId +
                ", userId='" + userId + '\'' +
                ", busID=" + busID +
                ", busPassStatus=" + busPassStatus +
                '}';
    }

    public void setBusPassStatus(int busPassStatus) {
        this.busPassStatus = busPassStatus;
    }

    public void setBusPassId(int busPassId) {
        this.busPassId = busPassId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBus() {
        return busID;
    }

    public void setBus(int busID) {
        this.busID = busID;
    }

}