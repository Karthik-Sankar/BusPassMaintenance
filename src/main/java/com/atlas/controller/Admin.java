package com.atlas.controller;

import com.atlas.models.*;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectSaver;
import com.atlas.persistance.ObjectStore;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.ScannerUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class Admin {
    boolean isAuthenticated = false;
    Admin admin;
    ScannerUtil scannerUtil = ScannerUtil.getInstance();
    RouteHandler routeHandler = RouteHandler.getInstance();
    BusHandler buses = BusHandler.getInstance();
    BusPassHandler busPasses = BusPassHandler.getInstance();
    UserHandler userHandler = UserHandler.getInstance();
    NotificationHandler ns = NotificationHandler.getNotificationInstance();
    VisitorHandler visitorHandler = VisitorHandler.getInstance();
    public void AdminEntry() {
        System.out.println("Enter Credentials : ");
        System.out.println("Username : ");
        String uname = scannerUtil.readLine();
        System.out.println("Password : ");
        String passwd = scannerUtil.readLine();
        if (uname.equals("admin") && passwd.equals("admin123")) {
            isAuthenticated = true;
        } else {
            System.out.println("You are not an administrator!");
            return;
        }
        Admin admin = new Admin();
        char session = 'y';
        while (session == 'y') {
            System.out.println("Admin Controls:");
            System.out.println("1: Notification");
            System.out.println("2: Approve/Reject Application");
            System.out.println("3: Add Route");
            System.out.println("4: Change Bus Route");
            System.out.println("5: Delete Route");
            System.out.println("6: List route details");
            System.out.println("7: List User details");
            System.out.println("8: Add Buses");
            System.out.println("9: Remove Buses");
            System.out.println("10: List Buses");
            System.out.println("Press 0 key to go to main menu!");
            int choice = scannerUtil.readInt();
            switch (choice) {
                case 1:
                    char session3 = 'y';
                    while (session3 == 'y') {
                        System.out.println("Notifications");
                        System.out.println("1. Read Notifications");
                        System.out.println("2. Clear Notification");
                        System.out.println("3. Clear All Notification");
                        System.out.println("Press 0 key to go to previous menu!");
                        int choice3 = scannerUtil.readInt();
                        NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                        switch (choice3) {
                            case 1:
                                notificationHandler.ListNotification("Admin");
                                break;
                            case 2:
                                System.out.println("Enter Notification ID to delete : ");
                                notificationHandler.clearNotification(scannerUtil.readInt());
                                break;
                            case 3:
                                notificationHandler.clearAllNotification();
                                break;
                            case 0:
                                session3='n';
                                System.out.println("Press 0 key to go to previous menu!");
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;
                case 2:
                    char session2='y';
                    while(session2=='y') {
                        System.out.println("Accept / Reject Routes");
                        System.out.println("1. List Visitor Application");
                        System.out.println("2. Approve visitor application");
                        System.out.println("3. Reject visitor application");
                        System.out.println("Press 0 key to previous menu!");
                        ScannerUtil input = ScannerUtil.getInstance();
                        int choice2 = input.readInt();
                        switch (choice2) {
                            case 1:
                                visitorHandler.displayVisitor();
                                break;
                            case 2:
                                System.out.println("Enter a vistor id to approve :");
                                String userId = scannerUtil.readLine();
                                Visitor v = visitorHandler.getVisitor(userId);
                                int routeID = v.getRouteID();
                                String userID = v.getUserId();
                                String paswd = v.getPassword();
                                String usname = v.getUserName();
                                String phone = v.getPhoneNumber();
                                String addr = v.getAddress();
                                int busPassID = IDGenerator.getBusPassID();
                                Bus b = routeHandler.getBus(v.getRouteID());
                                busPasses.addBusPass(busPassID, routeID, userID, b);
                                userHandler.addUser(busPasses.getBusPass(busPassID), userID, paswd, usname, phone, addr, routeID);
                                routeHandler.getBus(v.getRouteID()).incrementSeatFilled();
                                System.out.println("User Created, Enter Fare (\u20b9) :  ");
                                userHandler.getUser(v.getUserId()).setTravelCost(scannerUtil.readDouble());
                                visitorHandler.visitor.remove(userId);
                                System.out.println("User "+v.getUserName()+" Application Approved");
                                break;
                            case 3:
                                System.out.println("Enter a vistor id to reject :");
                                String userId2 = scannerUtil.readLine();
                                visitorHandler.visitor.remove(userId2);
                                System.out.println("User "+userId2+" Application Rejected");
                                break;
                            default:
                                session2='n';
                                break;
                        }
                    }
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
        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveAll();
    }

    private void routeAddition(RouteHandler routeHandler) {
        System.out.println("Enter Bus ID : ");
        BusHandler busHandler = BusHandler.getInstance();
        Bus bus = busHandler.getBus(scannerUtil.readInt());
        if(bus!=null) {
            System.out.println("Enter source : ");
            String source = scannerUtil.readLine();
            System.out.println("Enter destination : ");
            String destination = scannerUtil.readLine();
            System.out.println("Enter number of stops : ");
            int nos = scannerUtil.readInt();
            LinkedList<String> stops = new LinkedList<String>();
            System.out.println("Enter " + nos + " stops : ");
            while (nos > 0) {
                stops.add(scannerUtil.readLine());
                nos--;
            }
            System.out.println("Enter start time : ");
            String time = scannerUtil.readLine();
            System.out.println("Enter ETA : ");
            String eta = scannerUtil.readLine();
            routeHandler.addRoutes(IDGenerator.getRouteID(), source, destination, stops, time, eta, bus);
        }
        else{
            System.out.println("Are you choosing the right bus! Please first create a bus or give correct ID");
        }
    }

    private void addBuses(BusHandler busHandler) {
        System.out.println("Enter the bus type: ");
        String busType = scannerUtil.readLine();
        System.out.println("Enter the bus registration number: ");
        String regno = scannerUtil.readLine();
        System.out.println("Enter the seat capacity: ");
        int seatCapacity = scannerUtil.readInt();
        System.out.println("Enter the bus co-ordinator ID: ");
        String busCoordinator = scannerUtil.readLine();
        busHandler.addBus(IDGenerator.getBusID(),regno, busType, seatCapacity, busCoordinator);
    }

    private void changeBusRoute(RouteHandler route) {
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        System.out.println("Enter the bus number to change :");
        int busId = scannerUtil.readInt();
        route.modifyBusRoute(routeId, busId);
    }

    private void deleteRoute(RouteHandler route) {
        System.out.println("Enter the route number to perform change :");
        int routeId = scannerUtil.readInt();
        route.deleteRoute(routeId);
    }
}
