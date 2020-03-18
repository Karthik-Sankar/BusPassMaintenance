package com.atlas.utils;

import java.util.LinkedList;
import java.util.Random;

public class IDGenerator{
    private static IDGenerator idGenerator;
    static LinkedList<Integer> route;
    static LinkedList<Integer> bus;
    static LinkedList<Integer> busPass;
    static LinkedList<Integer> notify;
    static int routeID;
    static int busID;
    static int busPassID;
    static int notifyID;

    private IDGenerator(){
        route = new LinkedList<Integer>();
        bus = new LinkedList<Integer>();
        busPass = new LinkedList<Integer>();
        notify =  new LinkedList<Integer>();
    }

    public static IDGenerator getInstance(){
        if(idGenerator==null){
            idGenerator = new IDGenerator();
        }
        return idGenerator;
    }

    public static int getRouteID() {
        getInstance();
        routeID = IDGenerator.generateID();
        while(route.contains(routeID))
        {
            routeID = IDGenerator.generateID();
        }
        route.add(routeID);
        return routeID;
    }

    public static int getBusID() {
        getInstance();
        busID = IDGenerator.generateID();
        while(bus.contains(busID))
        {
            busID = IDGenerator.generateID();
        }
        bus.add(busID);
        return busID;
    }

    public static int getBusPassID() {
        getInstance();
        busPassID = IDGenerator.generateID();
        while(busPass.contains(busPassID))
        {
            busPassID = IDGenerator.generateID();
        }
        busPass.add(busPassID);
        return busPassID;
    }

    public static int getNotifyID() {
        getInstance();
        notifyID = IDGenerator.generateID();
        while(notify.contains(notifyID))
        {
            notifyID = IDGenerator.generateID();
        }
        notify.add(notifyID);
        return notifyID;
    }

    private static int generateID() {
        Random r = new Random();
        return r.nextInt(9000) + 1000;
    }

    public Object getObject(){
        return idGenerator;
    }
}