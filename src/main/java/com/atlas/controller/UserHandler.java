package com.atlas.controller;

import com.atlas.models.BusPass;
import com.atlas.models.User;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;
import com.atlas.utils.ScannerUtil;

import java.util.HashMap;
import java.util.Set;

public class UserHandler {

    private static UserHandler users;
    HashMap<String, User> user;

    private UserHandler() {
        user = new HashMap<String, User>();
        initalize();
    }

    public void initalize(){
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getUserObj();
        if(o!=null) {
            HashMap<String, User> temp = (HashMap<String, User>) o;
            Set<String> keys = temp.keySet();
                for (String key : keys) {
                User u = temp.get(key);
                addUser(u.getBusPass(), u.getUserId(), u.getUserName(), u.getPhoneNumber(), u.getRouteNum());
            }
        }
    }

    public static UserHandler getInstance() {
        if (users == null) {
            users = new UserHandler();
        }
        return users;
    }

    public void addUser(BusPass bid, String userId, String userName, String phoneNumber, int routeNum) {
        User addUser = new User(bid, userId, userName, phoneNumber, routeNum);
        user.put(userId, addUser);
    }

    public User getUser(String userId) {
        return user.get(userId);
    }

    public void displayUsers() {
        System.out.println();
        Lines.lines();
        System.out.println("Users Available");
        Set<String> u = user.keySet();
        for (String u1 : u) {
            User element = user.get(u1);
            Lines.lines();
            System.out.println("Buspass Info : " + element.getBusPass());
            System.out.println("ID : " + element.getUserId());
            System.out.println("UserName : " + element.getUserName());
            System.out.println("Phone Number :" + element.getPhoneNumber());
            System.out.println("Route Number :" + element.getRouteNum());
            Lines.lines();
        }
    }
    public void displayUsers(User u) {
        System.out.println();
        Lines.lines();
        System.out.println("Users Available");
        Set<String> us = user.keySet();
        for (String u1 : us) {
            User element = user.get(u1);
            if(u.getUserId().equals(element.getUserId())) {
                Lines.lines();
                System.out.println("Buspass Info : " + element.getBusPass());
                System.out.println("ID : " + element.getUserId());
                System.out.println("UserName : " + element.getUserName());
                System.out.println("Phone Number :" + element.getPhoneNumber());
                System.out.println("Route Number :" + element.getRouteNum());
                Lines.lines();
            }
        }
    }

    public void updateUserRoute(String userId, int routeId) {
        user.get(userId).setRouteNum(routeId);
    }

    public Object getObject(){
        return user;
    }

    public void UserEntry(){
        RouteHandler routeHandler = RouteHandler.getInstance();
        BusHandler buses = BusHandler.getInstance();
        BusPassHandler busPasses = BusPassHandler.getInstance();
        UserHandler userHandler = UserHandler.getInstance();
        NotificationHandler ns = NotificationHandler.getNotificationInstance();
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        System.out.println("Enter Credentials : ");
        System.out.println("Username : ");
        String uname = scannerUtil.readLine();
        char session='y';
        while(session=='y') {
            System.out.println("User options");
            System.out.println("1. View all the routes");
            System.out.println("2. Request to cancel a bus pass");
            System.out.println("3. Request for new route");
            System.out.println("4. Update their contact details (Yet to implement)");
            System.out.println("5. Get a snapshot of their details");
            System.out.println("Press 0 key to close!");
            ScannerUtil input = ScannerUtil.getInstance();
            int choice = input.readInt();
            switch (choice) {
                case 1:
                    routeHandler.displayRoute();
                    break;
                case 2:
                    ns.createNotifications(1, "Cancel bus pass for "+uname, getUser(uname), uname, "Admin");
                    System.out.println("Admin notified to cancel pass!");
                    break;
                case 3:
                    System.out.println("Enter new route as (Source-Destination) :");
                    ns.createNotifications(2, "New Route Request", scannerUtil.readLine(), uname, "Admin");
                    break;
                case 4:
                    break;
                case 5:
                    displayUsers(getUser(uname));
                    break;
                default:
                    session='n';
                    break;
            }
        }
    }
}
