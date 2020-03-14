package com.atlas.models;

import com.atlas.utils.IDGenerator;

public class BusPass {
    int busPassId;
    int routeId;
    String userId;
    Bus bus;

    public BusPass(int busPassId, int routeId, String userId, Bus bus) {
        this.busPassId = IDGenerator.getBusPassID();
        this.routeId = routeId;
        this.userId = userId;
        this.bus = bus;
    }

    public int getBusPassId() {
        return busPassId;
    }

    public int getRouteId() {
        return routeId;
    }

    public String getUserId() {
        return userId;
    }

    public Bus getBus() {
        return bus;
    }

    public String toString() {
        return "BusPassModel [busPassId=" + busPassId + ", routeId=" + routeId + ", userId=" + userId + ", bus="
                + bus + "]";
    }

}