package com.atlas.models;

import com.atlas.utils.IDGenerator;

import java.io.Serializable;

public class Bus implements Serializable {
    private int busId;
    private String busType;
    private int totalCapacity;
    private int seatFilled;

    public Bus(int busId, String busType, int totalCapacity) {
        this.busId = busId;
        this.busType = busType;
        this.totalCapacity = totalCapacity;
        this.seatFilled = 0;
    }

    public int getBusId() {
        return busId;
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

    public void incrementSeatFilled(){
        seatFilled++;
    }
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busType='" + busType + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", seatFilled=" + seatFilled +
                '}';
    }
}