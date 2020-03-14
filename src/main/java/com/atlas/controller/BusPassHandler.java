package com.atlas.controller;

import com.atlas.models.BusPass;

import java.util.HashMap;
import java.util.Set;

public class BusPassHandler {
    private static BusPassHandler busPassHandler;
    HashMap<String, BusPass> busPass = new HashMap<String,BusPass>();
    private BusPassHandler(){
    }
    public static BusPassHandler getInstance() {
        if(busPassHandler == null) {
            busPassHandler = new BusPassHandler();
        }
        return busPassHandler;
    }
    public BusPass getBusPass(String busPassId) {
        return busPass.get(busPassId);

    }
    public void getBusPassInfo() {

        Set<String> busspassm = busPass.keySet();
        for(String bpobj : busspassm) {
            BusPass bp = bpobj.get(bpobj);
            System.out.println("---------------------------------------");
            System.out.println("Buspass Info : " + bp.getBusPassId());
            System.out.println("ID : " + bp.getUserId());
            System.out.println("Bus ID : " + bp.getBusId());
            System.out.println("Route ID :" + bp.getRouteId());
            System.out.println("---------------------------------------");
        }
}
