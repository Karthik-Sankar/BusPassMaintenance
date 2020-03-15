package com.atlas.controller;

import com.atlas.models.BusPass;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.Lines;
import com.atlas.utils.ScannerUtil;

import java.util.HashMap;
import java.util.Set;


public class VisitorHandler {
    private static VisitorHandler visitorHandler;
    HashMap<String, Visitor> visitor;

    private VisitorHandler() {
        visitor = new HashMap<String,Visitor>();
    }

    public static VisitorHandler getInstance() {
        if (visitorHandler == null) {
            visitorHandler = new VisitorHandler();
        }
        return visitorHandler;
    }

    public void addVisitor(String userId, String userName, String phoneNumber, String source, String destination) {
        Visitor v = new Visitor(userId, userName, phoneNumber, source, destination);
        visitor.put(userId, v);
    }

    public Visitor getVisitor(String userID){
        return visitor.get(userID);
    }
    public void displayVisitor() {
        System.out.println();
        Lines.lines();
        System.out.println("Visitor Applied for Pass");
        Set<String> u = visitor.keySet();
        for (String u1 : u) {
            Visitor element = visitor.get(u1);
            Lines.lines();
            System.out.println("User ID : " + element.getUserId());
            System.out.println("Source : " + element.getSource());
            System.out.println("Destination : " + element.getDestination());
            System.out.println("User Name:" + element.getUserName());
            System.out.println("Phone :" + element.getPhoneNumber());
            Lines.lines();
        }
    }

    ScannerUtil scannerUtil = ScannerUtil.getInstance();
    RouteHandler routeHandler = RouteHandler.getInstance();
    NotificationHandler notification = NotificationHandler.getNotificationInstance();

    public void VisitorEntry() {
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
                    notification.createNotifications(2, "New Route Request", scannerUtil.readLine(), uid, "Admin");
                    break;
                case 4:
                    System.out.println("USER Name : ");
                    String userName = scannerUtil.readLine();
                    System.out.println("PHONE : ");
                    String phoneNumber = scannerUtil.readLine();
                    System.out.println("SOURCE : ");
                    String source = scannerUtil.readLine();
                    System.out.println("DESTINATION : ");
                    String destination = scannerUtil.readLine();
                    Visitor visitor = new Visitor(uid, userName, phoneNumber, source, destination);
                    visitorHandler.addVisitor(uid, userName, phoneNumber, source, destination);
                    notification.createNotifications(0, "Apply Bus Pass", visitor, uid, "Admin");
                    break;
                default:
                    session = 'n';
                    break;
            }
        }
        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveVisitor();
        objectSaver.saveNotifications();
    }

    public Object getObject(){
        return visitor;
    }
}
