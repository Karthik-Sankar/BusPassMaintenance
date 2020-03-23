package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.Lines;
import com.atlas.utils.NotifyConstants;
import com.atlas.utils.ScannerUtil;

import java.util.HashMap;
import java.util.Set;


public class VisitorHandler {
    private static VisitorHandler visitorHandler;
    HashMap<String, Visitor> visitor;

    private VisitorHandler() {
        visitor = new HashMap<String, Visitor>();
        initalize();
    }

    public static VisitorHandler getInstance() {
        if (visitorHandler == null) {
            visitorHandler = new VisitorHandler();
        }
        return visitorHandler;
    }

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getVisitorObj();
        if (o != null) {
            HashMap<String, Visitor> temp = (HashMap<String, Visitor>) o;
            visitor = temp;
        }
    }

    public void addVisitor(String userId, String password, String userName, String phoneNumber, String address, int routeID) {
        Visitor v = new Visitor(userId, password, userName, phoneNumber, address, routeID);
        visitor.put(userId, v);
    }

    public Visitor getVisitor(String userID) {
        return visitor.get(userID);
    }

    public void displayVisitor() {
        RouteHandler routeHandler = RouteHandler.getInstance();
        BusHandler busHandler = BusHandler.getInstance();
        if (!visitor.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s","Visitor Applied for Pass")+ColourMe.ANSI_RESET);
            Lines.lines();
            Set<String> u = visitor.keySet();
            for (String u1 : u) {
                Visitor element = visitor.get(u1);
                System.out.println("User ID : " + element.getUserId());
                System.out.println("User Name : " + element.getUserName());
                System.out.println("Phone Number : " + element.getPhoneNumber());
                System.out.println("Address:" + element.getAddress());
                System.out.println("Route ID :" + element.getRouteID());
                Lines.lines();
                Bus b = busHandler.bus.get(routeHandler.route.get(element.getRouteID()).getBus());
                if(b.getSeatFilled() < b.getTotalCapacity()){
                    System.out.println(ColourMe.ANSI_GREEN+"*** ALERT : SEATS AVAILABLE IN ROUTE ***"+ColourMe.ANSI_RESET);
                }
                else{
                    System.out.println(ColourMe.ANSI_BRIGHT_RED+"*** ALERT : SEATS AVAILABLE IN ROUTE ***"+ColourMe.ANSI_RESET);
                }
                Lines.lines();
            }
            Lines.lines();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"No Bus Pass Application Found!"+ColourMe.ANSI_RESET);
        }
    }

    RouteHandler routeHandler = RouteHandler.getInstance();
    NotificationHandler notification = NotificationHandler.getNotificationInstance();
    UserHandler userHandler = UserHandler.getInstance();

    public void VisitorEntry() {
        ObjectSaver objectSaver = new ObjectSaver();
        System.out.println("Enter UserID : ");
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        String uid = scannerUtil.readLine();
        char session;
        session = 'y';
        while (session == 'y') {
            System.out.println("Visitor Options");
            System.out.println("1. View Routes");
            System.out.println("2. View Seat Availability %");
            System.out.println("3. Request New Route Addition");
            System.out.println("4. Apply Bus Pass");
            System.out.println("5. Check Application Status");
            System.out.println("Press 0 key to go to main menu!");
            ScannerUtil input = ScannerUtil.getInstance();
            int choice = input.readInt();
            switch (choice) {
                case 1:
                    routeHandler.displayRoute();
                    break;
                case 2:
                    routeHandler.routeCapacityStatus();
                    break;
                case 3:
                    System.out.println("Enter new route as (Source-Destination) :");
                    notification.createNotification(uid,"Admin", "Create a new route between "+scannerUtil.readLine());
                    objectSaver.saveAll();
                    break;
                case 4:
                    if(!userHandler.user.containsKey(uid)) {
                        System.out.println("USER ID : \n" + uid);
                        System.out.println("ENTER ROUTE ID : ");
                        int routeID = scannerUtil.readInt();
                        String c = "y";
                        while (!routeHandler.route.containsKey(routeID)) {
                            System.out.println("You are entering invalid Route ID / Route has no seats available!! \nPress 'y' to continue and 'n' to cancel applying");
                            c = scannerUtil.readLine();
                            if (c.equals("y")) {
                                routeHandler.availableValidRoutes();
                                System.out.println("ENTER ROUTE ID AGAIN: ");
                                routeID = scannerUtil.readInt();
                            } else if (c.equals("n")) {
                                System.out.println("Bus pass application canceled!");
                                break;
                            } else {
                                System.out.println("Press 'y' or 'n', invalid key pressed!");
                            }
                        }
                        System.out.println("ENTER YOUR NAME : ");
                        String userName = scannerUtil.readLine();
                        System.out.println("ENTER PASSWORD : ");
                        String password = scannerUtil.readLine();
                        System.out.println("ENTER PHONE NUMBER : ");
                        String phoneNumber = scannerUtil.readLine();
                        System.out.println("ENTER ADDRESS IN SINGLE LINE : ");
                        String address = scannerUtil.readLine();
                        if (c.equals("y")) {
                            Visitor v = new Visitor(uid, password, userName, phoneNumber, address, routeID);
                            notification.createApplyPassNotification(uid, v);
                        }
                    }
                    else{
                        System.out.println("An existing user cant apply! Please cancel you exixting bus pass and try again!");
                    }
                    objectSaver.saveAll();
                    break;
                case 5:
                    if (userHandler.user.containsKey(uid)) {
                        System.out.println("ENTER PASSWORD TO CHECK STATUS : ");
                        String password2 = scannerUtil.readLine();
                        if (userHandler.user.get(uid).getPassword().equals(password2)) {
                            System.out.println("Application of " + uid + " has been approved! Please login as user!");
                        } else {
                            System.out.println("Incorrect password!");
                        }
                    } else {
                        System.out.println("Bus pass application rejected / Application not submitted yet!");
                    }
                    break;
                case 0:
                    session = 'n';
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public Object getObject() {
        return visitor;
    }
}
