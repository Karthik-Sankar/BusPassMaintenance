package com.atlas.models;

import com.atlas.utils.BusPassConstants;

import java.io.Serializable;

public class BusPass implements Serializable {
    int busPassId;
    int routeId;
    String userId;
    Bus bus;
    int busPassStatus;

    public BusPass(int busPassId, int routeId, String userId, Bus bus) {
        this.busPassId = busPassId;
        this.routeId = routeId;
        this.userId = userId;
        this.bus = bus;
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
                ", bus=" + bus +
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

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}