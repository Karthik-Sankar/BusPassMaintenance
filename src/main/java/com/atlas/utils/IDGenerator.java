package com.atlas.utils;

public class IDGenerator {
    static int routeID = 0;
    static int busID = 0;
    static int busPassID = 0;

    public static int getRouteID() {
        routeID++;
        return routeID;
    }

    public static int getBusID() {
        busID++;
        return busID;
    }

    public static int getBusPassID() {
        busPassID++;
        return busPassID;
    }
}
