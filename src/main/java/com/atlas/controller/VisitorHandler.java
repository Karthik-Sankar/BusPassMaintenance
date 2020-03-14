package com.atlas.controller;

import com.atlas.models.Visitor;
import com.atlas.utils.ScannerUtil;


public class VisitorHandler {
    ScannerUtil scannerUtil = ScannerUtil.getInstance();
//    1. View Routes
//    2. View Seat Availability %
//    3. Request New Route Addition
//    4. Apply Bus Pass

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
                    Routes routes = Routes.getInstance();
                    routes.displayRoute();
                    break;
                case 2:
                    Routes routes2 = Routes.getInstance();
                    routes2.routeCapacityStatus();
                    break;
                case 3:
                    NotificationStore notification = NotificationStore.getNotificationInstance();
                    System.out.println("Enter new route as (Source-Destination) :");
                    notification.createNotifications(2, "New Route Request", scannerUtil.readLine(), uid, "Admin");
                    System.out.println("");
                    break;
                case 4:
                    NotificationStore notification2 = NotificationStore.getNotificationInstance();
                    System.out.println("USER Name : ");
                    String userName = scannerUtil.readLine();
                    System.out.println("PHONE : ");
                    String phoneNumber = scannerUtil.readLine();
                    System.out.println("SOURCE : ");
                    String source = scannerUtil.readLine();
                    System.out.println("DESTINATION : ");
                    String destination = scannerUtil.readLine();
                    Visitor visitor = new Visitor(uid, userName, phoneNumber, source, destination);
                    notification2.createNotifications(0, "Apply Bus Pass", visitor, uid, "Admin");
                    break;
                default:
                    session = 'n';
                    break;
            }
        }
    }
}
