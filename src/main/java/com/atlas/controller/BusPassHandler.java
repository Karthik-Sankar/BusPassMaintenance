package com.atlas.controller;

import com.atlas.models.BusPass;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.ColourMe;

import java.util.HashMap;

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
            System.out.println(ColourMe.ANSI_RED + "Invalid buspass id!" + ColourMe.ANSI_RESET);
        }
        return null;
    }

    public Object getObject() {
        return busPass;
    }
}
