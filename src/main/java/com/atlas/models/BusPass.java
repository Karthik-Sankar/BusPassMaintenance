package com.atlas.models;


import com.atlas.utils.IDGenerator;

public class BusPass {
    int busPassId;
    int routeId;
    int userId;
    int busId;

    public BusPass(int busPassId, int routeId, int userId, int busId) {
        this.busPassId = IDGenerator.getBusPassID();
        this.routeId = routeId;
        this.userId = userId;
        this.busId = busId;
    }
    public int getBusPassId() {
        return busPassId;
    }
    public int getRouteId() {
        return routeId;
    }
    public int getUserId() {
        return userId;
    }
    public int getBusId() {
        return busId;
    }
    public String toString() {
        return "BusPassModel [busPassId=" + busPassId + ", routeId=" + routeId + ", userId=" + userId + ", busId="
                + busId + "]";
    }

}