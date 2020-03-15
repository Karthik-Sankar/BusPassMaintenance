package com.atlas.controller;

import com.atlas.models.Visitor;
import com.atlas.utils.ScannerUtil;


public class VisitorHandler {
    ScannerUtil scannerUtil = ScannerUtil.getInstance();
    RouteHandler routeHandler = RouteHandler.getInstance();
    NotificationHandler notification = NotificationHandler.getNotificationInstance();
    public void VisitorEntry()
    {
        System.out.println("Enter UserID : ");
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        String uid = scannerUtil.readLine();
        char session;
        session='y';
        while(session=='y') {
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
                    notification.createNotifications(0, "Apply Bus Pass", visitor, uid, "Admin");
                    break;
                default:
                    session = 'n';
                    break;
            }
        }
    }
}
