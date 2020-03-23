package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.Lines;
import com.atlas.utils.ScannerUtil;

import javax.sound.sampled.Line;
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

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getRoutesObj();
        if (o != null) {
            HashMap<Integer, Route> temp = (HashMap<Integer, Route>) o;
            route = temp;
        }
    }

    public int addRoutes(int routeId, String source, String destination, LinkedList<String> stops, String time, String eta, int bus) {
        if (!route.containsKey(getRouteID_S(source)) || !route.containsKey(getRouteID_D(destination))) {
            Route r = new Route(routeId, source, destination, stops, time, eta, bus);
            route.put(routeId, r);
            return r.getRouteId();
        } else {
            System.out.println("Already have a route in same location with id : " + route.get(getRouteID_S(source)).getRouteId());
            displayRoute(route.get(getRouteID_S(source)).getRouteId());
            return route.get(routeId).getRouteId();
        }
    }

    public void routeAddition() {
        BusHandler busHandler = BusHandler.getInstance();
        System.out.println("Enter Bus ID : ");
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        Bus bus = busHandler.getBus(scannerUtil.readInt());
        //System.out.println("Bus object : "+bus.hashCode());
        if (bus != null) {
            if(bus.getRouteID()==-1) {
                System.out.println("Enter source : ");
                String source = scannerUtil.readLine();
                System.out.println("Enter destination : ");
                String destination = scannerUtil.readLine();
                System.out.println("Enter number of stops : ");
                int nos = scannerUtil.readInt();
                LinkedList<String> stops = new LinkedList<String>();
                System.out.println("Enter " + nos + " stops : ");
                while (nos > 0) {
                    stops.add(scannerUtil.readLine());
                    nos--;
                }
                System.out.println("Enter Shift [SHIFT1, SHIFT2, SHIFT3] : ");
                String time = scannerUtil.readLine();
                System.out.println("Enter Cab Arrival time at Source : ");
                String eta = scannerUtil.readLine();
                int routeID = IDGenerator.getRouteID();
                addRoutes(routeID, source, destination, stops, time, eta, bus.getBusId());
                busHandler.bus.get(bus.getBusId()).setRouteID(routeID);
            }
            else{
                System.out.println("Already assigned to a different route : "+ bus.getRouteID());
            }
            //System.out.println("RH :"+routeHandler.getBus(routeID).hashCode());
        } else {
            System.out.println("Are you choosing the right bus! Please first create a bus or give correct ID");
        }
    }

    public int getBus(int routeID) {
        if (route.containsKey(routeID)) {
            return route.get(routeID).getBus();
        } else {
            System.out.println("There is no routeID Found");
        }
        return -1;
    }

    public int getRouteID_S(String source) {
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            if (r.getSource().equals(source)) return r.getRouteId();
        }
        return -1;
    }

    public int getRouteID_D(String destination) {
        Set<Integer> keys = route.keySet();
        for (Integer key : keys) {
            Route r = route.get(key);
            if (r.getDestination().equals(destination)) return r.getRouteId();
        }
        return -1;
    }

    public void displayRoute() {
        if (!route.isEmpty()) {
            BusHandler busHandler = BusHandler.getInstance();
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s","Route Chart")+ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_PURPLE+"Shift 1 - 06:00 AM - 03:00 PM \t Shift 2 - 08:00 AM - 05:00 PM \t Shift 3 - 12:00 PM - 09:00 AM"+ColourMe.ANSI_RESET);
            Lines.lines();
            Set<Integer> keys = route.keySet();
            for (Integer key : keys) {
                Route r = route.get(key);
                Bus b = busHandler.getBus(r.getBus());
                System.out.println(ColourMe.ANSI_GREEN+String.format("%55s","Route No : " + r.getRouteId())+ColourMe.ANSI_RESET);
                System.out.println();
                System.out.println("Source   : "+String.format("%10s",r.getSource())+"\t\t\t"+"Destination  : " + String.format("%20s", r.getDestination()));
                if(r.getBus()!=-1)
                System.out.println("Shift    : "+String.format("%10s", r.getTime())+"\t\t\t"+"Bus No       : "+String.format("%20s",b.getBusId()));
                else
                System.out.println("Shift    : "+String.format("%10s", r.getTime())+"\t\t\t"+"Bus No       : "+ColourMe.ANSI_BRIGHT_RED+String.format("%20s","No Buses Available!")+ColourMe.ANSI_RESET);
                System.out.println("\n Additional Info :\n-------------------");
                System.out.println("Stops    : " +r.getStops());
                System.out.println("ETA      : " +r.getEta());
                Lines.lines();
            }
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"***Cab takes 15-20 mins to travel from one stop to another"+ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"No Route Found to Display"+ColourMe.ANSI_RESET);
        }
    }

    public void modifyBusRoute(int routeId, int newbusID) {
        RouteHandler routeHandler = RouteHandler.getInstance();
        BusHandler busHandler = BusHandler.getInstance();
        //checking if current route has a bus already!
        if (routeHandler.getBus(routeId) != -1) {
            //checking is new bus can accomodate previous users.
            if(busHandler.bus.get(newbusID).getTotalCapacity() > busHandler.bus.get(routeHandler.route.get(routeId).getBus()).getSeatFilled()){
                System.out.println("Route already has bus assigned (BusID : "+routeHandler.getBus(routeId)+")");
                int oldBus = routeHandler.route.get(routeId).getBus();
                int oldRouteID = busHandler.bus.get(newbusID).getRouteID();
                routeHandler.route.get(routeId).setBus(newbusID);
                busHandler.bus.get(newbusID).setRouteID(routeId);
                busHandler.bus.get(newbusID).setSeatFilled(busHandler.bus.get(routeHandler.route.get(routeId).getBus()).getSeatFilled());
                routeHandler.route.get(oldRouteID).setBus(-1);
                System.out.println("Route "+ routeHandler.route.get(routeId) + " has tagged with Bus "+ busHandler.bus.get(newbusID));
                System.out.println("Alert route "+oldRouteID+" which previously used this bus has no bus tagged now!");
            }
            else{
                System.out.println("Seat capacity not met! \n Choose a different bus!");
            }
        }
        else{
            System.out.println("Route doesn't have any bus linked!");
            routeHandler.route.get(routeId).setBus(newbusID);
            busHandler.bus.get(newbusID).setRouteID(routeId);
            busHandler.bus.get(newbusID).setSeatFilled(0);
            System.out.println("Route "+ routeHandler.route.get(routeId) + " has tagged with Bus "+ busHandler.bus.get(newbusID));
        }
    }

    public void delRoute(int routeId) {
        if (route.containsKey(routeId)) {
            BusHandler busHandler = BusHandler.getInstance();
            busHandler.bus.get(route.get(routeId).getBus()).setRouteID(-1);
            route.remove(routeId);
        } else {
            System.out.println("No such route available!!!");
        }
    }

    public void availableValidRoutes(){
        if (!route.isEmpty()) {
            Set<Integer> keys = route.keySet();
            for (Integer key : keys) {
                Route r = route.get(key);
                BusHandler busHandler = BusHandler.getInstance();
                if(busHandler.isSeatsAvailable(r.getBus())) {
                    System.out.println("[Route No : " + r.getRouteId());
                    System.out.print("Source : " + r.getSource());
                    System.out.println("Destination : " + r.getDestination() + "]\t");
                }
            }
        } else {
            System.out.println("No Routes Available currently!");
        }
    }

    public void displayRoute(int routeId) {
        if (route.containsKey(routeId)) {
            BusHandler busHandler = BusHandler.getInstance();
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s","Route Chart")+ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_PURPLE+"Shift 1 - 06:00 AM - 03:00 PM \t Shift 2 - 08:00 AM - 05:00 PM \t Shift 3 - 12:00 PM - 09:00 AM"+ColourMe.ANSI_RESET);
            Lines.lines();
            Route r = route.get(routeId);
            Bus b = busHandler.getBus(r.getBus());
            System.out.println(ColourMe.ANSI_GREEN+String.format("%55s","Route No : " + r.getRouteId())+ColourMe.ANSI_RESET);
            System.out.println();
            System.out.println("Source   : "+String.format("%10s",r.getSource())+"\t\t\t"+"Destination  : " + String.format("%20s", r.getDestination()));
            if(r.getBus()!=-1)
                System.out.println("Shift    : "+String.format("%10s", r.getTime())+"\t\t\t"+"Bus No       : "+String.format("%20s",b.getBusId()));
            else
                System.out.println("Shift    : "+String.format("%10s", r.getTime())+"\t\t\t"+"Bus No       : "+ColourMe.ANSI_BRIGHT_RED+String.format("%20s","No Buses Available!")+ColourMe.ANSI_RESET);
            System.out.println("\n Additional Info :\n-------------------");
            System.out.println("Stops    : " +r.getStops());
            System.out.println("ETA      : " +r.getEta());
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"***Cab takes 15-20 mins to travel from one stop to another"+ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"No Such Route Available!"+ColourMe.ANSI_RESET);
        }
    }

    public void routeCapacityStatus() {
        if (!route.isEmpty()) {
            BusHandler busHandler = BusHandler.getInstance();
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s","Route Availability Chart")+ColourMe.ANSI_RESET);
            Lines.lines();
            Set<Integer> keys = route.keySet();
            for (Integer key : keys) {
                Route r = route.get(key);
                Bus b = busHandler.getBus(r.getBus());
                System.out.println(ColourMe.ANSI_GREEN+String.format("%55s","Route No : " + r.getRouteId())+ColourMe.ANSI_RESET);
                System.out.println();
                System.out.println("Source   : "+String.format("%10s",r.getSource())+"\t\t\t"+"Destination  : " + String.format("%20s", r.getDestination()));
                double per;
                if (b != null)
                    per = (b.getSeatFilled() / b.getTotalCapacity()) * 100;
                else per = 0.0;
                System.out.println(ColourMe.ANSI_BRIGHT_RED+"Percentage Occupied : " + per + "%"+ColourMe.ANSI_RESET);
                Lines.lines();
            }
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"No Route Found to Display"+ColourMe.ANSI_RESET);
        }
    }

    public void deleteRoute() {
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        System.out.println("Enter the route number to delete :");
        int routeId = scannerUtil.readInt();
        delRoute(routeId);
    }

    public void changeBusRoute() {
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        if(!routeHandler.route.containsKey(routeId)){
            System.out.println("No such routes!");
            return;
        }
        System.out.println("Enter the bus number to change :");
        int busId = scannerUtil.readInt();
        BusHandler busHandler = BusHandler.getInstance();
        if(!busHandler.bus.containsKey(busId)){
            System.out.println("No such bus!");
            return;
        }
        modifyBusRoute(routeId, busId);
    }

    public Object getObject() {
        return route;
    }
}
