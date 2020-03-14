package com.atlas.controller;

import com.atlas.models.Route;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Routes {
    private static Routes routes;
    public HashMap<String, Route> route;
    private Routes(){
        route=new HashMap<String, Route>();
    }

    public static Routes getInstance(){
        if(routes==null){
            routes = new Routes();
        }
        return routes;
    }
    public int addRoutes(String source, String destination, LinkedList<String> stops, String time, String eta){
        if(!route.containsKey(source)) {
            Route r = new Route(source, destination, stops, time, eta
            );
            routes.route.put(source, r);
            return r.getRouteId();
        }
        else{
            System.out.println("Already have a route in same location with id : "+route.get(source).getRouteId());
            return route.get(source).getRouteId();
        }
    }
    public void displayRoute(){
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("---------------------------------Route Chart-------------------------------");
        System.out.println("---------------------------------------------------------------------------");
        Set<String> keys = route.keySet();
        for (String key : keys) {
            Route r = route.get(key);
            System.out.println("Route No : " + r.getRouteId());
            System.out.println("Source : " + r.getSource());
            System.out.println("Destination : " + r.getDestination());
            System.out.println("Stops : " + r.getStops());
            System.out.println("Time : " + r.getTime());
            System.out.println("ETA : " + r.getEta());
            System.out.println("---------------------------------------------------------------------------");
        }
        System.out.println("---------------------------------------------------------------------------");
    }

    public void displayRoute(int route_id){
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("--------------------------------Route Details------------------------------");
        System.out.println("---------------------------------------------------------------------------");
        Set<String> keys = route.keySet();
        for (String key : keys) {
            Route r = route.get(key);
            if(route_id == r.getRouteId()) {
                System.out.println("Route No : " + r.getRouteId());
                System.out.println("Source : " + r.getSource());
                System.out.println("Destination : " + r.getDestination());
                System.out.println("Stops : " + r.getStops());
                System.out.println("---------------------------------------------------------------------------");
            }

        }
        System.out.println("---------------------------------------------------------------------------");
    }
}
