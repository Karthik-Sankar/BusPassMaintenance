package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.utils.ScannerUtil;

import java.net.UnknownServiceException;
import java.util.LinkedList;

public class Admin {
    boolean isAuthenticated = false;
    Admin admin;
    ScannerUtil scannerUtil = ScannerUtil.getInstance();
    /*public String assignUserRoute(String userId, int routeId)
    {
        int routeNum;
        Routes route = Routes.getInstance();
        routeNum = route.route.get();
        //route.assignUserRoute(userId, routeNum);
        return "Your updated Route number is :"+routeNum;
    }*/

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
        Routes routes = Routes.getInstance();
        BusHandler buses = BusHandler.getInstance();
        BusPassHandler busPasses = BusPassHandler.getInstance();
        UserHandler userHandler = UserHandler.getInstance();
        char session = 'y';
        NotificationStore ns = NotificationStore.getNotificationInstance();
        while (session == 'y') {
            System.out.println("Admin Controls:");
            System.out.println("1: Read Notification");
            System.out.println("2: Approve/Reject Application (Yet to Implement)");
            System.out.println("3: Add Route");
            System.out.println("4: Change Bus Route");
            System.out.println("5: Assign User Route (Yet to Implement)");
            System.out.println("6: List route details");
            System.out.println("7: List User details");
            System.out.println("8: Add Buses");
            System.out.println("9: Remove Buses");
            System.out.println("10: List Buses");
            System.out.println("Press 0 key to go to main menu!");
            int choice = scannerUtil.readInt();
            switch (choice) {
                case 1:
                    NotificationStore notificationStore = NotificationStore.getNotificationInstance();
                    notificationStore.ListNotification("Admin");
                    break;
                case 2:

                    break;
                case 3:
                    admin.routeAddition(routes);
                    break;
                case 4:
                    admin.changeBusRoute(routes, buses);
                    break;
                case 5:
                    break;
                case 6:
                    routes.displayRoute();
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
                default:
                    session = 'n';
                    break;

            }
        }
    }

    private void routeAddition(Routes routes){
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
        routes.addRoutes(source, destination, stops,time, eta, bus);
    }

    private void addBuses(BusHandler busHandler)
    {
        System.out.println("Enter the bus type: ");
        String busType = scannerUtil.readLine();
        System.out.println("Enter the seat capacity: ");
        int seatCapacity = scannerUtil.readInt();
        busHandler.addBus(busType, seatCapacity);
    }

    private void changeBusRoute(Routes routes, BusHandler busHandler)
    {
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        System.out.println("Enter the bus number to change :");
        int busId = scannerUtil.readInt();
        routes.route.get(routeId).setBus(busHandler.getBus(busId));
    }
}
