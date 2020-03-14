package com.atlas.models;

import com.atlas.utils.IDGenerator;

import java.util.LinkedList;

public class Route {
    private int routeId;
    private String source;
    private String destination;
    private LinkedList<String> stops;
    private String time;
    private String eta;
    public Route(String source, String destination, LinkedList<String> stops, String time, String eta)
    {
        routeId= IDGenerator.getRouteID();
        this.source=source;
        this.destination=destination;
        this.stops=stops;
        this.time=time;
        this.eta=eta;
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

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", stops=" + stops +
                ", time='" + time + '\'' +
                ", eta='" + eta + '\'' +
                '}';
    }
}
