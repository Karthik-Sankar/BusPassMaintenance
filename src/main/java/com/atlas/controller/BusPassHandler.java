package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.Set;

public class BusPassHandler {
    private static BusPassHandler busPassHandler;
    HashMap<Integer, BusPass> busPass;

    private BusPassHandler() {
        busPass = new HashMap<Integer, BusPass>();
        initalize();
    }

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getBusPassObj();
        if (o != null) {
            HashMap<Integer, BusPass> temp = (HashMap<Integer, BusPass>) o;
            busPass = temp;
        }
    }

    public static BusPassHandler getInstance() {
        if (busPassHandler == null) {
            busPassHandler = new BusPassHandler();
        }
        return busPassHandler;
    }

    public void addBusPass(int busPassID, int routeID, String userID, int busID) {
        BusPass bp = new BusPass(busPassID, routeID, userID, busID);
        busPass.put(bp.getBusPassId(), bp);
    }

    public BusPass getBusPass(int busPassId) {
        if (busPass.containsKey(busPassId)) {
            return busPass.get(busPassId);
        } else {
            System.out.println("Invalid buspass id!");
        }
        return null;
    }

    public void listBusPasses() {
        if (!busPass.isEmpty()) {
            Set<Integer> busspassm = busPass.keySet();
            for (Integer bpobj : busspassm) {
                BusPass bp = busPass.get(bpobj);
                System.out.println();
                Lines.lines();
                System.out.println("Bus Pass Assigned Details");
                Lines.lines();
                System.out.println("Buspass Info : " + bp.getBusPassId());
                System.out.println("User : " + bp.getUserId());
                System.out.println("Bus ID : " + bp.getBus());
                System.out.println("Route ID :" + bp.getRouteId());
                Lines.lines();
            }
        } else {
            System.out.println("No bus passes registered!");
        }
    }

    public Object getObject() {
        return busPass;
    }
}
