package com.atlas.models;

import com.atlas.utils.IDGenerator;

import java.io.Serializable;

public class Bus implements Serializable {
    private int busId;
    private String regNo;
    private String busType;
    private int totalCapacity;
    private int seatFilled;
    private String busCoOrdinatorID;

    public Bus(int busId, String regNo, String busType, int totalCapacity, String busCoordinatorID) {
        this.busId = busId;
        this.regNo = regNo;
        this.busType = busType;
        this.totalCapacity = totalCapacity;
        this.seatFilled = 0;
        this.busCoOrdinatorID = busCoordinatorID;
    }

    public int getBusId() {
        return busId;
    }

    public String getBusCoOrdinatorID() {
        return busCoOrdinatorID;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getSeatFilled() {
        return seatFilled;
    }

    public void setSeatFilled(int seatFilled) {
        this.seatFilled = seatFilled;
    }

    public void incrementSeatFilled(){
        seatFilled++;
    }

    public void deccrementSeatFilled(){
        seatFilled--;
    }

    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", regNo='" + regNo + '\'' +
                ", busType='" + busType + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", seatFilled=" + seatFilled +
                '}';
    }
}