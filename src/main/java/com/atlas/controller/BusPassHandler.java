package com.atlas.controller;

import com.atlas.models.BusPass;

import java.util.HashMap;
import java.util.Set;

public class BusPassHandler {
    private static BusPassHandler busPassHandler;
    HashMap<Integer, BusPass> busPass = new HashMap<Integer, BusPass>();

    private BusPassHandler() {
    }

    public static BusPassHandler getInstance() {
        if (busPassHandler == null) {
            busPassHandler = new BusPassHandler();
        }
        return busPassHandler;
    }

    public BusPass getBusPass(int busPassId) {
        return busPass.get(busPassId);

    }

    public void getBusPassInfo() {
        Set<Integer> busspassm = busPass.keySet();
        for (Integer bpobj : busspassm) {
            BusPass bp = busPass.get(bpobj);
            System.out.println();
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("-------------------------------Buss Pass Info------------------------------");
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Buspass Info : " + bp.getBusPassId());
            System.out.println("ID : " + bp.getUserId());
            System.out.println("Bus ID : " + bp.getBusId());
            System.out.println("Route ID :" + bp.getRouteId());
            System.out.println("---------------------------------------------------------------------------");
        }
    }
}
