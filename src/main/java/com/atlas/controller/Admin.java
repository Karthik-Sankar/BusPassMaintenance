package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.utils.ScannerUtil;

import java.util.LinkedList;

public class Admin {
    boolean isAuthenticated = false;
    Admin admin;
    ScannerUtil scannerUtil = ScannerUtil.getInstance();

    public void AdminEntry() {
        System.out.println("Enter Credentials : ");
        System.out.println("Username : ");
        String uname = scannerUtil.readLine();
        System.out.println("Password : ");
        String passwd = scannerUtil.readLine();
        if(uname.equals("admin") && passwd.equals("admin123"))
        {
            isAuthenticated = true;
        }
        else{
            System.out.println("You are not an administrator!");
            return;
        }
        Admin admin = new Admin();
        RouteHandler routeHandler = RouteHandler.getInstance();
        BusHandler buses = BusHandler.getInstance();
        BusPassHandler busPasses = BusPassHandler.getInstance();
        UserHandler userHandler = UserHandler.getInstance();
        char session = 'y';
        NotificationHandler ns = NotificationHandler.getNotificationInstance();
        while (session == 'y') {
            System.out.println("Admin Controls:");
            System.out.println("1: Read Notification");
            System.out.println("2: Approve/Reject Application (Yet to Implement)");
            System.out.println("3: Add Route");
            System.out.println("4: Change Bus Route");
            System.out.println("5: Delete Route");
            System.out.println("6: List route details");
            System.out.println("7: List User details");
            System.out.println("8: Add Buses");
            System.out.println("9: Remove Buses");
            System.out.println("10: List Buses");
            System.out.println("11: Generate billing reports");
            System.out.println("Press 0 key to go to main menu!");
            int choice = scannerUtil.readInt();
            switch (choice) {
                case 1:
                    NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                    notificationHandler.ListNotification("Admin");
                    break;
                case 2:

                    break;
                case 3:
                    admin.routeAddition(routeHandler);
                    break;
                case 4:
                    admin.changeBusRoute(routeHandler);
                    break;
                case 5:
                    admin.deleteRoute(routeHandler);
                    break;
                case 6:
                    routeHandler.displayRoute();
                    break;
                case 7:
                    userHandler.displayUsers();
                    break;
                case 8:
                    admin.addBuses(buses);
                    break;
                case 9:
                    System.out.println("Enter bus number: ");
                    buses.removeBus(scannerUtil.readInt());
                    break;
                case 10:
                    buses.listBuses();
                    break;
                case 11:
                    break;
                default:
                    session = 'n';
                    break;

            }
        }
        RouteHandler.save();
    }

    private void routeAddition(RouteHandler routeHandler){
        System.out.println("Enter Bus No : ");
        BusHandler busHandler = BusHandler.getInstance();
        Bus bus = busHandler.getBus(scannerUtil.readInt());
        System.out.println("Enter source : ");
        String source = scannerUtil.readLine();
        System.out.println("Enter destination : ");
        String destination = scannerUtil.readLine();
        System.out.println("Enter number of stops : ");
        int nos = scannerUtil.readInt();
        LinkedList<String> stops = new LinkedList<String>();
        System.out.println("Enter "+nos+" stops : ");
        while(nos>0) {
            stops.add(scannerUtil.readLine());
            nos--;
        }
        System.out.println("Enter start time : ");
        String time = scannerUtil.readLine();
        System.out.println("Enter ETA : ");
        String eta = scannerUtil.readLine();
        routeHandler.addRoutes(source, destination, stops,time, eta, bus);
    }

    private void addBuses(BusHandler busHandler)
    {
        System.out.println("Enter the bus type: ");
        String busType = scannerUtil.readLine();
        System.out.println("Enter the seat capacity: ");
        int seatCapacity = scannerUtil.readInt();
        busHandler.addBus(busType, seatCapacity);
    }

    private void changeBusRoute(RouteHandler route)
    {
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        System.out.println("Enter the bus number to change :");
        int busId = scannerUtil.readInt();
        route.modifyBusRoute(routeId, busId);
    }

    private void deleteRoute(RouteHandler route)
    {
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        route.deleteRoute(routeId);
    }
}
