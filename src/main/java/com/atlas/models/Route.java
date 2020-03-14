package com.atlas.models;

import com.atlas.utils.IDGenerator;
import java.util.LinkedList;

public class Route {
    int routeId;
    String source;
    String destination;
    LinkedList<String> stops;
    String time;
    String eta;
    public Route(String source, String destination, LinkedList<String> stops)
    {
        routeId= IDGenerator.getRouteID();
        this.source=source;
        this.destination=destination;
        this.stops=stops;
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
}
