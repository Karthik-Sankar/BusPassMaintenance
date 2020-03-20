package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectStore;
import com.atlas.utils.Lines;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class RouteHandler {
    private static RouteHandler routeHandler;
    HashMap<Integer, Route> route;

    private RouteHandler() {
        route = new HashMap<Integer, Route>();
        initalize();
    }

    public static RouteHandler getInstance() {
        if (routeHandler == null) {
            routeHandler = new RouteHandler();
        }
        return routeHandler;
    }

    public void initalize(){
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getRoutesObj();
        if(o!=null) {
            HashMap<Integer, Route> temp = (HashMap<Integer, Route>) o;
            Set<Integer> keys = temp.keySet();
            for (Integer key : keys) {
                Route r = temp.get(key);
                addRoutes(r.getRouteId(),r.getSource(), r.getDestination(), r.getStops(), r.getTime(), r.getEta(), r.getBus());
            }
        }
    }

    public int addRoutes(int routeId, String source, String destination, LinkedList<String> stops, String time, String eta, Bus bus) {
        if (!route.containsKey(getRouteID(source))) {
            Route r = new Route(routeId, source, destination, stops, time, eta, bus);
            route.put(routeId, r);
            return r.getRouteId();
        } else {
            System.out.println("Already have a route in same location with id : " + route.get(routeId).getRouteId());
            return route.get(routeId).getRouteId();
        }
    }

    public Bus getBus(int routeID){
        if(route.containsKey(routeID)) {
            return route.get(routeID).getBus();
        }
        else{
            System.out.println("There is no routeID Found");
        }
        return null;
    }

    public int getRouteID(String source) {
        if(route.containsKey(source)) {
            Set<Integer> keys = route.keySet();
            for (Integer key : keys) {
                Route r = route.get(key);
                if (r.getSource().equals(source)) return r.getRouteId();
            }
        }
        else{
            System.out.println("No such routes available for the given source");
        }
        return -1;
    }

    public void displayRoute() {
        if(!route.isEmpty()) {
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
        else{
            System.out.println("No Route Found to Display");
        }
    }

    public void modifyBusRoute(int routeId, int busId) {
        BusHandler busHandler = BusHandler.getInstance();
        if(route.containsKey(routeId) && busHandler.bus.containsKey(busId)) {
            if(route.get(routeId).getBus().getBusId() != busId) {
                route.get(routeId).setBus(busHandler.getBus(busId));
            }
            else{
                System.out.println("Route already assigned to this Bus");
            }
        }
        else{
            System.out.println("No such route/bus available!!!");
        }
    }

    public void deleteRoute(int routeId){
        if(route.containsKey(routeId)){
            route.remove(routeId);
        }
        else{
            System.out.println("No such route available!!!");
        }
    }

    public void displayRoute(int routeId) {
        if(route.containsKey(routeId)) {
            System.out.println();
            Lines.lines();
            System.out.println("Route Details");
            Lines.lines();
            Set<Integer> keys = route.keySet();
            for (Integer key : keys) {
                Route r = route.get(key);
                if (routeId == r.getRouteId()) {
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
        else{
            System.out.println("No such route available!!!");
        }
    }

    public void routeCapacityStatus() {
        if(!route.isEmpty()) {
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
                double per;
                if (b != null)
                    per = (b.getSeatFilled() / b.getTotalCapacity()) * 100;
                else per = 0.0;
                System.out.println("Percentage Occupied : " + per + "%");
                Lines.lines();
            }
        }
        else{
            System.out.println("No Routes Found");
        }
    }

    public Object getObject(){
        return route;
    }
}
