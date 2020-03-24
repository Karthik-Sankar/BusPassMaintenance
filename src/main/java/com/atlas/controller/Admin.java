package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.ScannerUtil;

import java.util.LinkedList;

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
    ObjectSaver objectSaver = new ObjectSaver();
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
        routeHandler.getNoBusRoutes();
        char session = 'y';
        while (session == 'y') {
            System.out.println("Admin Controls:");
            System.out.println("1: Notification");
            System.out.println("2: Approve/Reject Application");
            System.out.println("3: Route Operations");
            System.out.println("4: List User details");
            System.out.println("5: Bus Operations");
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
                                objectSaver.saveAll();
                                break;
                            case 3:
                                notificationHandler.clearAllNotification();
                                objectSaver.saveAll();
                                break;
                            case 0:
                                session3 = 'n';
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;
                case 2:
                    char session2 = 'y';
                    while (session2 == 'y') {
                        System.out.println("Accept / Reject Routes");
                        System.out.println("1. List Visitor Application");
                        System.out.println("2. Approve visitor application");
                        System.out.println("3. Reject visitor application");
                        System.out.println("Press 0 key to previous menu!");
                        int choice2 = scannerUtil.readInt();
                        switch (choice2) {
                            case 1:
                                visitorHandler.displayVisitor();
                                break;
                            case 2:
                                approveUserApplication();
                                objectSaver.saveAll();
                                break;
                            case 3:
                                rejectUserApplication();
                                objectSaver.saveAll();
                                break;
                            case 0:
                                session2 = 'n';
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;
                case 3:
                    char session4 = 'y';
                    while (session4 == 'y') {
                        System.out.println("Route operations");
                        System.out.println("1. List Routes");
                        System.out.println("2. Add route");
                        System.out.println("3. Delete route");
                        System.out.println("4. Change bus in route");
                        System.out.println("Press 0 key to previous menu!");
                        int choice4 = scannerUtil.readInt();
                        switch (choice4) {
                            case 1:
                                routeHandler.displayRoute();
                                break;
                            case 2:
                                boolean isBusAvailable = buses.getUnAssignedBuses();
                                if(isBusAvailable) {
                                    routeHandler.routeAddition();
                                }
                                else{
                                    System.out.println("Add new bus (y/n) ?");
                                    String c = scannerUtil.readLine();
                                    if(c.equals("y")){
                                        buses.addBuses();
                                        routeHandler.routeAddition();
                                    }
                                    else if (c.equals("n")) {
                                        System.out.println("operations canceled!");
                                    }
                                    else{
                                        System.out.println("Invalid operation!");
                                    }
                                }
                                objectSaver.saveAll();
                                break;
                            case 3:
                                routeHandler.deleteRoute();
                                objectSaver.saveAll();
                                break;
                            case 4:
                                routeHandler.changeBusRoute();
                                objectSaver.saveAll();
                                break;
                            case 0:
                                session4 = 'n';
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                    break;
                case 4:
                    userHandler.displayUsers();
                    break;
                case 5:
                    char session5 = 'y';
                    while (session5 == 'y') {
                        System.out.println("Bus operations");
                        System.out.println("1. List Available Buses");
                        System.out.println("2. Add Bus");
                        System.out.println("3. Delete Bus");
                        System.out.println("4. Change bus coordinator");
                        System.out.println("Press 0 key to previous menu!");
                        int choice5 = scannerUtil.readInt();
                        switch (choice5) {
                            case 1:
                                buses.listBuses();
                                break;
                            case 2:
                                buses.addBuses();
                                objectSaver.saveAll();
                                break;
                            case 3:
                                System.out.println("Enter bus number: ");
                                buses.removeBus(scannerUtil.readInt());
                                objectSaver.saveAll();
                                break;
                            case 4:
                                System.out.println("Enter bus number: ");
                                int busNo = scannerUtil.readInt();
                                if(buses.bus.containsKey(busNo)) {
                                    System.out.println("Enter bus coordinator user ID: ");
                                    buses.getBus(busNo).setBusCoOrdinatorID(scannerUtil.readLine());
                                    System.out.println("Bus co-ordinator changed successfully!");
                                    objectSaver.saveAll();
                                }
                                else{
                                    System.out.println("No such bus available!");
                                }
                                break;
                            default:
                                session5 = 'n';
                                break;
                        }
                    }
                    break;
                default:
                    session = 'n';
                    break;

            }
        }
    }


    private void approveUserApplication(){
        System.out.println("Enter a vistor id to approve :");
        String userId = scannerUtil.readLine();
        if(visitorHandler.visitor.containsKey(userId)) {
            Visitor v = visitorHandler.getVisitor(userId);
            int routeID = v.getRouteID();
            String userID = v.getUserId();
            String paswd = v.getPassword();
            String usname = v.getUserName();
            String phone = v.getPhoneNumber();
            String addr = v.getAddress();
            int busPassID = IDGenerator.getBusPassID();
            buses.getBus(routeHandler.getBus(routeID)).incrementSeatFilled();
            busPasses.addBusPass(busPassID, routeID, userID, routeHandler.getBus(routeID));
            userHandler.addUser(busPassID, userID, paswd, usname, phone, addr, routeID);
            visitorHandler.visitor.remove(userId);
            NotificationHandler handler = NotificationHandler.getNotificationInstance();
            handler.createNotification("Amazon Transport",userID, "Your bus pass has been approved!");
            System.out.println("User " + v.getUserName() + " Application Approved");
        }
        else{
            System.out.println("No such user applied!");
        }
    }

    private void rejectUserApplication(){
        System.out.println("Enter a vistor id to reject :");
        String userId2 = scannerUtil.readLine();
        if(visitorHandler.visitor.containsKey(userId2)) {
            visitorHandler.visitor.remove(userId2);
            NotificationHandler handler = NotificationHandler.getNotificationInstance();
            handler.createNotification("Amazon Transport",userId2, "Your bus pass has been rejected!");
            System.out.println("User " + userId2 + " Application Rejected");
        }
        else{
            System.out.println("No such user applied!");
        }
    }
}
