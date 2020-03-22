package com.atlas.models;

import java.io.Serializable;

public class Bus implements Serializable {
    private int busId;
    private String regNo;
    private String busType;
    private int totalCapacity;
    private int seatFilled;
    private String busCoOrdinatorID;
    private int routeID;

    public Bus(int busId, String regNo, String busType, int totalCapacity, String busCoordinatorID) {
        this.busId = busId;
        this.regNo = regNo;
        this.busType = busType;
        this.totalCapacity = totalCapacity;
        this.seatFilled = 0;
        this.busCoOrdinatorID = busCoordinatorID;
        this.routeID = -1;
    }

    public int getBusId() {
        return busId;
    }

    public void setSeatFilled(int seatFilled) {
        this.seatFilled = seatFilled;
    }

    public String getBusCoOrdinatorID() {
        return busCoOrdinatorID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public void setBusCoOrdinatorID(String busCoOrdinatorID) {
        this.busCoOrdinatorID = busCoOrdinatorID;
    }


    public String getRegNo() {
        return regNo;
    }

    public String getBusType() {
        return busType;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getSeatFilled() {
        return seatFilled;
    }

    public void incrementSeatFilled() {
        seatFilled++;
    }

    public void deccrementSeatFilled() {
        seatFilled--;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", regNo='" + regNo + '\'' +
                ", busType='" + busType + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", seatFilled=" + seatFilled +
                ", busCoOrdinatorID='" + busCoOrdinatorID + '\'' +
                ", routeID=" + routeID +
                '}';
    }
}