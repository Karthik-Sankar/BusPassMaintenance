package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.Lines;
import com.atlas.utils.ScannerUtil;

import java.util.HashMap;
import java.util.Set;

public class BusHandler {
    private static BusHandler busHandler;
    public HashMap<Integer, Bus> bus;

    private BusHandler() {
        bus = new HashMap<Integer, Bus>();
        initalize();
    }

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getBusObj();
        if (o != null) {
            HashMap<Integer, Bus> temp = (HashMap<Integer, Bus>) o;
            bus = temp;
        }
    }

    public static BusHandler getInstance() {
        if (busHandler == null) {
            busHandler = new BusHandler();
        }
        return busHandler;
    }

    public void addBus(int busId, String regNo, String busType, int totalCapacity, String busCordinatorID) {
        Bus b = new Bus(busId, regNo, busType, totalCapacity, busCordinatorID);
        bus.put(b.getBusId(), b);
        System.out.println(ColourMe.ANSI_GREEN + "Bus Added Sucessfully!" + ColourMe.ANSI_RESET);
    }

    public void removeBus(int busID) {
        if (bus.containsKey(busID)) {
            RouteHandler routeHandler = RouteHandler.getInstance();
            routeHandler.route.get(bus.get(busID).getRouteID()).setBus(-1);
            bus.remove(busID);
            System.out.println(ColourMe.ANSI_GREEN + "Bus removed successfully!" + ColourMe.ANSI_RESET);
        } else {
            System.out.println(ColourMe.ANSI_RED + "Invalid bus ID" + ColourMe.ANSI_RESET);
        }
    }

    public Bus getBus(int busID) {
        if (bus.containsKey(busID)) {
            return bus.get(busID);
        } else {
            return null;
        }
    }

    public void listBuses() {
        if (!bus.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%55s", "Bus Details Summary") + ColourMe.ANSI_RESET);
            Lines.lines();
            RouteHandler routeHandler = RouteHandler.getInstance();
            Set<Integer> keys = bus.keySet();
            for (Integer key : keys) {
                Bus bp = bus.get(key);
                System.out.println("Bus ID         : " + String.format("%10d", bp.getBusId()) + "\t\t\tBus Registration Number : " + String.format("%25s", bp.getRegNo()));
                System.out.println("Bus Type       : " + String.format("%10s", bp.getBusType()) + "\t\t\tBus co-ordinator        : " + String.format("%25s", bp.getBusCoOrdinatorID()));
                System.out.println("Total Capacity : " + String.format("%10d", bp.getTotalCapacity()) + "\t\t\tSeats Filled            : " + String.format("%25d", bp.getSeatFilled()));
                Route r = routeHandler.route.get(bp.getRouteID());
                if (bp.getRouteID() != -1 && r != null)
                    System.out.println("Route ID       : " + String.format("%10d", bp.getRouteID()) + "\t\t\tRoute Details           : " + String.format("%25s", routeHandler.route.get(bp.getRouteID()).getSource() + "-" + routeHandler.route.get(bp.getRouteID()).getDestination()));
                else {
                    System.out.println(ColourMe.ANSI_RED + "Bus is not tagged to a route yet!" + ColourMe.ANSI_RESET);
                }
                Lines.lines();
            }
            Lines.lines();
        } else {
            System.out.println(ColourMe.ANSI_RED + "No buses added yet!" + ColourMe.ANSI_RESET);
            System.out.println();
        }
    }

    public boolean isSeatsAvailable(int busID) {
        if (busID != -1)
            return bus.get(busID).getTotalCapacity() > bus.get(busID).getSeatFilled();
        else
            return false;
    }

    public boolean getUnAssignedBuses() {
        boolean flag = false;
        if (!bus.isEmpty()) {
            Set<Integer> keys = bus.keySet();
            for (Integer key : keys) {
                Bus b = bus.get(key);
                if (b.getRouteID() == -1) {
                    System.out.print(ColourMe.ANSI_BRIGHT_GREEN + "Bus - " + b.getBusId() + " is free!" + ColourMe.ANSI_RESET);
                    flag = true;
                }
                System.out.println();
            }
            if (!flag)
                System.out.println(ColourMe.ANSI_BRIGHT_RED + "No bus is free!" + ColourMe.ANSI_RESET);
        }
        return flag;
    }

    public void addBuses() {
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        System.out.println("Enter the bus type: ");
        String busType = scannerUtil.readLine();
        System.out.println("Enter the bus registration number: ");
        String regno = scannerUtil.readLine();
        System.out.println("Enter the seat capacity: ");
        int seatCapacity = scannerUtil.readInt();
        System.out.println("Enter the bus co-ordinator ID: ");
        String busCoordinator = scannerUtil.readLine();
        addBus(IDGenerator.getBusID(), regno, busType, seatCapacity, busCoordinator);
    }

    public Object getObject() {
        return bus;
    }
}
