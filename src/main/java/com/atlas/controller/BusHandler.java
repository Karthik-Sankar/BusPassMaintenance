package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.models.Route;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Set;

public class BusHandler {
    private static BusHandler busHandler;
    public HashMap<Integer, Bus> bus;
    private BusHandler(){
        bus = new HashMap<Integer, Bus>();
        initalize();
    }

    public void initalize(){
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getBusObj();
        if(o!=null) {
            HashMap<Integer, Bus> temp = (HashMap<Integer, Bus>) o;
            Set<Integer> keys = temp.keySet();
            for (Integer key : keys) {
                Bus b = temp.get(key);
                addBus(b.getBusId(),b.getBusType(), b.getTotalCapacity());
            }
        }
    }

    public static BusHandler getInstance(){
        if(busHandler==null){
            busHandler=new BusHandler();
        }
        return busHandler;
    }
    public void addBus(int busNo, String busType, int totalCapacity){
        Bus b = new Bus(busNo, busType,totalCapacity);;
        bus.put(busNo, b);
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

    public Object getObject(){
        return bus;
    }
}
