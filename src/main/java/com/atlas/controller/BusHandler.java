package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;
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
                addBus(b.getBusId(),b.getRegNo(), b.getBusType(), b.getTotalCapacity(), b.getBusCoOrdinatorID());
            }
        }
    }

    public static BusHandler getInstance(){
        if(busHandler==null){
            busHandler=new BusHandler();
        }
        return busHandler;
    }
    public void addBus(int busId, String regNo, String busType, int totalCapacity, String busCordinatorID){
        Bus b = new Bus(busId,regNo, busType, totalCapacity, busCordinatorID);
        bus.put(b.getBusId(), b);
    }
    public void removeBus(int busID){
        if(bus.containsKey(busID)) {
            bus.remove(busID);
        }
        else{
            System.out.println("Invalid bus ID");
        }
    }
    public Bus getBus(int busID){
        if(bus.containsKey(busID)) {
            return bus.get(busID);
        }
        else{
            System.out.println("\nInvalid bus ID");
        }
        return null;
    }

    public void listBuses() {
        if(!bus.isEmpty()) {
            Set<Integer> keys = bus.keySet();
            for (Integer key : keys) {
                Bus bp = bus.get(key);
                System.out.println();
                Lines.lines();
                System.out.println("Bus Details Summary");
                Lines.lines();
                System.out.println("Bus ID : " + bp.getBusId());
                System.out.println("Bus Registration Number : " + bp.getRegNo());
                System.out.println("Bus Type : " + bp.getBusType());
                System.out.println("Total Capacity : " + bp.getTotalCapacity());
                System.out.println("Seats Filled :" + bp.getSeatFilled());
                Lines.lines();
            }
        }
        else{
            System.out.println("No buses added yet!");
        }
    }

    public Object getObject(){
        return bus;
    }
}
