package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.Set;

public class BusPassHandler {
    private static BusPassHandler busPassHandler;
    HashMap<Integer, BusPass> busPass;

    private BusPassHandler() {
        busPass = new HashMap<Integer, BusPass>();
    }

    public static BusPassHandler getInstance() {
        if (busPassHandler == null) {
            busPassHandler = new BusPassHandler();
        }
        return busPassHandler;
    }

    public void addBusPass(Bus busID, int busPassID, int routeID, String userID){
        BusPass bp = new BusPass(busPassID, routeID, userID, busID);
        busPass.put(busPassID, bp);
    }

    public BusPass getBusPass(int busPassId) {
        return busPass.get(busPassId);

    }

    public void getBusPassInfo() {
        Set<Integer> busspassm = busPass.keySet();
        for (Integer bpobj : busspassm) {
            BusPass bp = busPass.get(bpobj);
            System.out.println();
            Lines.lines();
            System.out.println("Bus Pass Assigned Details");
            Lines.lines();
            System.out.println("Buspass Info : " + bp.getBusPassId());
            System.out.println("ID : " + bp.getUserId());
            System.out.println("Bus ID : " + bp.getBus().getBusId());
            System.out.println("Route ID :" + bp.getRouteId());
            Lines.lines();
        }
    }

    public Object getObject(){
        return busPass;
    }
}
