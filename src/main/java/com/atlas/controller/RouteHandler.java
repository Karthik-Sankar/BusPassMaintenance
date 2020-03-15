package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.persistance.ObjectStore;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class RouteHandler {
    private static RouteHandler routeHandler;
    public static HashMap<Integer, Route> route;

    private RouteHandler() {
        route = new HashMap<Integer, Route>();
    }

    public static RouteHandler getInstance() {
        if (routeHandler == null) {
            routeHandler = new RouteHandler();
        }
        return routeHandler;
    }

    public int addRoutes(String source, String destination, LinkedList<String> stops, String time, String eta, Bus bus) {
        int routeId = getRouteID(source);
        if (!route.containsKey(routeId)) {
            Route r = new Route(source, destination, stops, time, eta, bus);
            route.put(routeId, r);
            return r.getRouteId();
        } else {
            System.out.println("Already have a route in same location with id : " + route.get(routeId).getRouteId());
            return route.get(routeId).getRouteId();
        }
    }

    public int getRouteID(String source) {
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            if (r.getSource().equals(source)) return r.getRouteId();
        }
        return 0;
    }

    public void displayRoute() {
        System.out.println();
        Lines.lines();
        System.out.println("Route Chart");
        Lines.lines();
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            System.out.println("Route No : " + r.getRouteId());
            System.out.println("Source : " + r.getSource());
            System.out.println("Destination : " + r.getDestination());
            System.out.println("Stops : " + r.getStops());
            System.out.println("Time : " + r.getTime());
            System.out.println("ETA : " + r.getEta());
            System.out.println("Bus : " + r.getBus());
            Lines.lines();
        }
    }

    public void modifyBusRoute(int routeId, int busId) {
        if(route.containsKey(routeId)) {
            BusHandler busHandler = BusHandler.getInstance();
            route.get(routeId).setBus(busHandler.getBus(busId));
        }
        else{
            System.out.println("\nNo such route available!!!");
        }
    }

    public void deleteRoute(int routeId){
        if(route.containsKey(routeId)){
            route.remove(routeId);
        }
        else{
            System.out.println("\nNo such route available!!!");
        }
    }

    public void displayRoute(int route_id) {
        System.out.println();
        Lines.lines();
        System.out.println("Route Details");
        Lines.lines();
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            if (route_id == r.getRouteId()) {
                System.out.println("Route No : " + r.getRouteId());
                System.out.println("Source : " + r.getSource());
                System.out.println("Destination : " + r.getDestination());
                System.out.println("Stops : " + r.getStops());
                System.out.println("Time : " + r.getTime());
                System.out.println("ETA : " + r.getEta());
                System.out.println("Bus : " + r.getBus());
                Lines.lines();
            }
        }
    }

    public void routeCapacityStatus() {
        System.out.println();
        Lines.lines();
        System.out.println("Route Seat Availability");
        Lines.lines();
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            System.out.println("Route No : " + r.getRouteId());
            System.out.println("Source : " + r.getSource());
            System.out.println("Destination : " + r.getDestination());
            Bus b = r.getBus();
            double per = (b.getSeatFilled() / b.getTotalCapacity()) * 100;
            System.out.println("Percentage Occupied : " + per + "%");
            Lines.lines();
        }
    }

    public static void save(){
        ObjectStore.saveObject(routeHandler, "Route");
    }
}
