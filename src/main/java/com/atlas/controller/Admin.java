package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.ScannerUtil;

public class Admin {
    boolean isAuthenticated = false;
    ScannerUtil scannerUtil = ScannerUtil.getInstance();
    RouteHandler routeHandler = RouteHandler.getInstance();
    BusHandler buses = BusHandler.getInstance();
    BusPassHandler busPasses = BusPassHandler.getInstance();
    UserHandler userHandler = UserHandler.getInstance();
    VisitorHandler visitorHandler = VisitorHandler.getInstance();
    ObjectSaver objectSaver = new ObjectSaver();

    public void AdminEntry() {
        System.out.println(ColourMe.ANSI_GREEN + "Enter Credentials : " + ColourMe.ANSI_RESET);
        System.out.println("Username : ");
        String uname = scannerUtil.readLine();
        System.out.println("Password : ");
        String passwd = scannerUtil.readLine();
        if (uname.equals("admin") && passwd.equals("admin123")) {
            isAuthenticated = true;
        } else {
            System.out.println(ColourMe.ANSI_RED + "You are not an administrator!" + ColourMe.ANSI_RESET);
            return;
        }
        routeHandler.getNoBusRoutes();
        char session = 'y';
        while (session == 'y') {
            System.out.println(ColourMe.ANSI_BRIGHT_YELLOW + "Admin Controls:" + ColourMe.ANSI_RESET);
            System.out.print(ColourMe.ANSI_BLUE);
            System.out.println("1: Notification");
            System.out.println("2: Approve/Reject Application");
            System.out.println("3: Route Operations");
            System.out.println("4: List User details");
            System.out.println("5: Bus Operations");
            System.out.print("Press 0 key to go to main menu!");
            System.out.println(ColourMe.ANSI_RESET);
            int choice = scannerUtil.readInt();
            switch (choice) {
                case 1:
                    char session3 = 'y';
                    while (session3 == 'y') {
                        System.out.println(ColourMe.ANSI_BLUE);
                        System.out.println("Notifications");
                        System.out.println("1. Read Notifications");
                        System.out.println("2. Clear Notification");
                        System.out.println("3. Clear All Notification");
                        System.out.println("Press 0 key to go to previous menu!");
                        System.out.println(ColourMe.ANSI_RESET);
                        int choice3 = scannerUtil.readInt();
                        NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                        switch (choice3) {
                            case 1:
                                notificationHandler.ListNotification("Admin");
                                break;
                            case 2:
                                if (!notificationHandler.note.isEmpty()) {
                                    System.out.println("Enter Notification ID to delete : ");
                                    notificationHandler.clearNotification(scannerUtil.readInt());
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No Notification Available for admin!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 3:
                                if (!notificationHandler.note.isEmpty()) {
                                    notificationHandler.clearAllNotification();
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No Notification Available for admin!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 0:
                                session3 = 'n';
                                break;
                            default:
                                System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                        }
                    }
                    break;
                case 2:
                    char session2 = 'y';
                    while (session2 == 'y') {
                        System.out.println(ColourMe.ANSI_BLUE);
                        System.out.println("Accept / Reject Routes");
                        System.out.println("1. List Visitor Application");
                        System.out.println("2. Approve visitor application");
                        System.out.println("3. Reject visitor application");
                        System.out.println("Press 0 key to previous menu!");
                        System.out.println(ColourMe.ANSI_RESET);
                        int choice2 = scannerUtil.readInt();
                        switch (choice2) {
                            case 1:
                                visitorHandler.displayVisitor();
                                break;
                            case 2:
                                if (!visitorHandler.visitor.isEmpty()) {
                                    approveUserApplication();
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_YELLOW + "No applications available!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 3:
                                if (!visitorHandler.visitor.isEmpty()) {
                                    rejectUserApplication();
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_YELLOW + "No applications available!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 0:
                                session2 = 'n';
                                break;
                            default:
                                System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                        }
                    }
                    break;
                case 3:
                    char session4 = 'y';
                    while (session4 == 'y') {
                        System.out.println(ColourMe.ANSI_BLUE);
                        System.out.println("Route operations");
                        System.out.println("1. List Routes");
                        System.out.println("2. Add route");
                        System.out.println("3. Delete route");
                        System.out.println("4. Change bus in route");
                        System.out.println("Press 0 key to previous menu!");
                        System.out.println(ColourMe.ANSI_RESET);
                        int choice4 = scannerUtil.readInt();
                        switch (choice4) {
                            case 1:
                                if (!routeHandler.route.isEmpty())
                                    routeHandler.displayRoute();
                                else
                                    System.out.println(ColourMe.ANSI_RED + "No routes added yet!" + ColourMe.ANSI_RESET);
                                break;
                            case 2:
                                boolean isBusAvailable = buses.getUnAssignedBuses();
                                if (isBusAvailable) {
                                    routeHandler.routeAddition();
                                } else {
                                    System.out.println(ColourMe.ANSI_GREEN + "Add new bus (y/n) ?" + ColourMe.ANSI_RESET);
                                    String c = scannerUtil.readLine();
                                    if (c.equals("y")) {
                                        buses.addBuses();
                                        buses.getUnAssignedBuses();
                                        routeHandler.routeAddition();
                                    } else if (c.equals("n")) {
                                        System.out.println(ColourMe.ANSI_RED + "operations canceled!" + ColourMe.ANSI_RESET);
                                    } else {
                                        System.out.println(ColourMe.ANSI_RED + "Invalid operation!" + ColourMe.ANSI_RESET);
                                    }
                                }
                                objectSaver.saveAll();
                                break;
                            case 3:
                                if (!routeHandler.route.isEmpty()) {
                                    routeHandler.displayRoute();
                                    routeHandler.deleteRoute();
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No routes available!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 4:
                                if (!routeHandler.route.isEmpty()) {
                                    routeHandler.displayRoute();
                                    routeHandler.changeBusRoute();
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No routes available!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 0:
                                session4 = 'n';
                                break;
                            default:
                                System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                        }
                    }
                    break;
                case 4:
                    userHandler.displayUsers();
                    break;
                case 5:
                    char session5 = 'y';
                    while (session5 == 'y') {
                        System.out.println(ColourMe.ANSI_BLUE);
                        System.out.println("Bus operations");
                        System.out.println("1. List Available Buses");
                        System.out.println("2. Add Bus");
                        System.out.println("3. Delete Bus");
                        System.out.println("4. Change bus coordinator");
                        System.out.println("Press 0 key to previous menu!");
                        System.out.println(ColourMe.ANSI_RESET);
                        int choice5 = scannerUtil.readInt();
                        switch (choice5) {
                            case 1:
                                if (!buses.bus.isEmpty())
                                    buses.listBuses();
                                else
                                    System.out.println(ColourMe.ANSI_RED + "No bus added yet!" + ColourMe.ANSI_RESET);
                                break;
                            case 2:
                                buses.addBuses();
                                objectSaver.saveAll();
                                break;
                            case 3:
                                if (!buses.bus.isEmpty()) {
                                    buses.listBuses();
                                    System.out.println("Enter bus number: ");
                                    buses.removeBus(scannerUtil.readInt());
                                    objectSaver.saveAll();
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No bus added yet!" + ColourMe.ANSI_RESET);
                                }
                                break;
                            case 4:
                                if (!buses.bus.isEmpty()) {
                                    buses.listBuses();
                                    System.out.println("Enter bus number: ");
                                    int busNo = scannerUtil.readInt();
                                    if (buses.bus.containsKey(busNo)) {
                                        System.out.println("Enter bus coordinator user ID: ");
                                        buses.getBus(busNo).setBusCoOrdinatorID(scannerUtil.readLine());
                                        System.out.println(ColourMe.ANSI_GREEN + "Bus co-ordinator changed successfully!" + ColourMe.ANSI_RESET);
                                        objectSaver.saveAll();
                                    } else {
                                        System.out.println(ColourMe.ANSI_RED + "No such bus available!" + ColourMe.ANSI_RESET);
                                    }
                                } else {
                                    System.out.println(ColourMe.ANSI_RED + "No bus added yet!" + ColourMe.ANSI_RESET);
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


    private void approveUserApplication() {
        System.out.println("Enter a vistor id to approve :");
        String userId = scannerUtil.readLine();
        if (visitorHandler.visitor.containsKey(userId)) {
            Visitor v = visitorHandler.getVisitor(userId);
            int routeID = v.getRouteID();
            String userID = v.getUserId();
            String paswd = v.getPassword();
            String usname = v.getUserName();
            String phone = v.getPhoneNumber();
            String addr = v.getAddress();
            int busPassID = IDGenerator.getBusPassID();
            Route r = routeHandler.route.get(routeID);
            Bus b = buses.getBus(r.getBus());
            if (b != null) {
                b.incrementSeatFilled();
                busPasses.addBusPass(busPassID, routeID, userID, b.getBusId());
                userHandler.addUser(busPassID, userID, paswd, usname, phone, addr, routeID);
                visitorHandler.visitor.remove(userId);
                NotificationHandler handler = NotificationHandler.getNotificationInstance();
                handler.createNotification("Amazon Transport", userID, ColourMe.ANSI_GREEN + "Your bus pass has been approved!" + ColourMe.ANSI_RESET);
                System.out.println(ColourMe.ANSI_GREEN + "User " + v.getUserName() + " Application Approved" + ColourMe.ANSI_RESET);
            } else {
                System.out.println(ColourMe.ANSI_RED + "Currently the route has no bus! Please assign bus and then add user!" + ColourMe.ANSI_RESET);
            }
        } else {
            System.out.println(ColourMe.ANSI_RED + "No such user applied!" + ColourMe.ANSI_RESET);
        }
    }

    private void rejectUserApplication() {
        System.out.println("Enter a vistor id to reject :");
        String userId2 = scannerUtil.readLine();
        if (visitorHandler.visitor.containsKey(userId2)) {
            visitorHandler.visitor.remove(userId2);
            NotificationHandler handler = NotificationHandler.getNotificationInstance();
            handler.createNotification("Amazon Transport", userId2, ColourMe.ANSI_RED + "Your bus pass has been rejected!" + ColourMe.ANSI_RESET);
            System.out.println(ColourMe.ANSI_RED + "User " + userId2 + " Application Rejected" + ColourMe.ANSI_RESET);
        } else {
            System.out.println(ColourMe.ANSI_RED + "No such user applied!" + ColourMe.ANSI_RESET);
        }
    }
}
