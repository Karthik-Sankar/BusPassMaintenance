package com.atlas.models;

import com.atlas.utils.IDGenerator;

import java.io.Serializable;
import java.util.LinkedList;

public class Route implements Serializable {
    private int routeId;
    private Bus bus;
    private String source;
    private String destination;
    private LinkedList<String> stops;
    private String time;
    private String eta;

    public Route(String source, String destination, LinkedList<String> stops, String time, String eta, Bus bus) {
        routeId = IDGenerator.getRouteID();
        this.source = source;
        this.destination = destination;
        this.stops = stops;
        this.time = time;
        this.eta = eta;
        this.bus = bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
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

    public Bus getBus() {
        return bus;
    }

    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", stops=" + stops +
                ", time='" + time + '\'' +
                ", eta='" + eta + '\'' +
                ", Bus Info ='" + bus + '\'' +
                '}';
    }
}
