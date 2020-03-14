package com.atlas.models;

import com.atlas.utils.IDGenerator;

public class Bus {
    int busId;
    String busType;
    int totalCapacity;
    int seatFilled;

    public Bus(int busId, String busType, int totalCapacity, int seatFilled) {
        this.busId = IDGenerator.getBusID();
        this.busType = busType;
        this.totalCapacity = totalCapacity;
        this.seatFilled = seatFilled;
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

    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busType='" + busType + '\'' +
                ", totalCapacity=" + totalCapacity +
                ", seatFilled=" + seatFilled +
                '}';
    }
}