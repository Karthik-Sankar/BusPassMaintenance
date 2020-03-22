package com.atlas.models;

import java.io.Serializable;
import java.util.LinkedList;

public class Route implements Serializable {
    private int routeId;
    private int busID;
    private String source;
    private String destination;
    private LinkedList<String> stops;
    private String time;
    private String eta;

    public Route(int routeId, String source, String destination, LinkedList<String> stops, String time, String eta, int busID) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.time = time;
        this.eta = eta;
        this.busID = busID;
    }

    public void setBus(int busID) {
        this.busID = busID;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRouteId() {
        return routeId;
    }

    public LinkedList<String> getStops() {
        return stops;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public String getEta() {
        return eta;
    }

    public String getTime() {
        return time;
    }

    public int getBus() {
        return busID;
    }

    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", stops=" + stops +
                ", time='" + time + '\'' +
                ", eta='" + eta + '\'' +
                ", Bus ='" + busID + '\'' +
                '}';
    }
}
