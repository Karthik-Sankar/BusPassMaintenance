package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.Set;

public class BusHandler {
    private static BusHandler busHandler;
    public HashMap<Integer, Bus> bus;
    private BusHandler(){
        bus = new HashMap<Integer, Bus>();
    }
    public static BusHandler getInstance(){
        if(busHandler==null){
            busHandler=new BusHandler();
        }
        return busHandler;
    }
    public void addBus(String busType, int totalCapacity){
        Bus b = new Bus(busType,totalCapacity);;
        bus.put(b.getBusId(), b);
    }
    public void removeBus(int busID){
        bus.remove(busID);
    }
    public Bus getBus(int busID){
        return bus.get(busID);
    }

    public void listBuses() {
        Set<Integer> keys = bus.keySet();
        for(Integer key:keys){
            Bus bp = bus.get(key);
            System.out.println();
            Lines.lines();
            System.out.println("Bus Details Summary");
            Lines.lines();
            System.out.println("Bus ID : " + bp.getBusId());
            System.out.println("Bus Type : " + bp.getBusType());
            System.out.println("Total Capacity : " + bp.getTotalCapacity());
            System.out.println("Seats Filled :" + bp.getSeatFilled());
            Lines.lines();
        }
    }
}
