package com.atlas.controller;

import com.atlas.models.BusPass;
import com.atlas.models.User;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.Lines;
import com.atlas.utils.NotifyConstants;
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

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getUserObj();
        if (o != null) {
            HashMap<String, User> temp = (HashMap<String, User>) o;
            Set<String> keys = temp.keySet();
            for (String key : keys) {
                User u = temp.get(key);
                addUser(u.getBusPass(), u.getUserId(), u.getPassword(), u.getUserName(), u.getPhoneNumber(), u.getAddress(), u.getRouteNum());
            }
        }
    }

    public static UserHandler getInstance() {
        if (users == null) {
            users = new UserHandler();
        }
        return users;
    }

    public void addUser(BusPass bid, String userId, String password, String userName, String phoneNumber, String address, int routeNum) {
        User addUser = new User(bid, userId, password, userName, phoneNumber, address, routeNum);
        user.put(userId, addUser);
    }

    public User getUser(String userId) {
        return user.get(userId);
    }

    public void displayUsers() {
        if (!user.isEmpty()) {
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
        } else {
            System.out.println("No Users registered yet!!");
        }
    }

    public void displayUsers(User u) {
        if (user.containsKey(u.getUserId())) {
            System.out.println();
            Lines.lines();
            System.out.println("Users Available");
            Set<String> us = user.keySet();
            for (String u1 : us) {
                User element = user.get(u1);
                if (u.getUserId().equals(element.getUserId())) {
                    Lines.lines();
                    System.out.println("Buspass Info : " + element.getBusPass());
                    System.out.println("ID : " + element.getUserId());
                    System.out.println("UserName : " + element.getUserName());
                    System.out.println("Phone Number :" + element.getPhoneNumber());
                    System.out.println("Route Number :" + element.getRouteNum());
                    Lines.lines();
                }
            }
        } else {
            System.out.println("Specified User is not available in the list!!");
        }
    }

    public void updateUserRoute(String userId, int routeId) {
        RouteHandler routeHandler = RouteHandler.getInstance();
        if (user.containsKey(userId) && routeHandler.route.containsKey(routeId)) {
            user.get(userId).setRouteNum(routeId);
        } else {
            System.out.println("Invalid UserID/RouteID!!");
        }
    }

    public Object getObject() {
        return user;
    }

    public void UserEntry() {
        RouteHandler routeHandler = RouteHandler.getInstance();
        NotificationHandler ns = NotificationHandler.getNotificationInstance();
        ScannerUtil scannerUtil = ScannerUtil.getInstance();
        System.out.println("Enter Credentials : ");
        System.out.println("Username : ");
        String uname = scannerUtil.readLine();
        //System.out.println(user.get(uname).getPassword());
        System.out.println("Password : ");
        String password = scannerUtil.readLine();
        if (user.containsKey(uname)) {
            if (user.get(uname).getPassword().equals(password)) {
                char session = 'y';
                while (session == 'y') {
                    System.out.println("User options");
                    System.out.println("1. View all the routes");
                    System.out.println("2. Cancel a bus pass");
                    System.out.println("3. Suspend a bus pass");
                    System.out.println("4. Request for new route");
                    System.out.println("5. Update their contact details");
                    System.out.println("6. Get a snapshot of their details");
                    System.out.println("7. Submit feedback");
                    System.out.println("8. Notifications");
                    System.out.println("Press 0 key to go to main menu!");
                    ScannerUtil input = ScannerUtil.getInstance();
                    int choice = input.readInt();
                    switch (choice) {
                        case 1:
                            routeHandler.displayRoute();
                            break;
                        case 2:
                            ns.cancelBusPassNotification(user.get(uname));
                            System.out.println("Bus pass cancelled!!");
                            break;
                        case 3:
                            ns.suspendBusPassNotification(user.get(uname));
                            System.out.println("Bus pass suspended!!");
                            break;
                        case 4:
                            System.out.println("Enter new route as (Source-Destination) :");
                            ns.createNotifications(NotifyConstants.NewRoute, "New Route Request", scannerUtil.readLine(), uname, "Admin");
                            System.out.println("New route request placed!");
                            break;
                        case 5:
                            char session2 = 'y';
                            while (session2 == 'y') {
                                System.out.println("Update User Details");
                                System.out.println("1. Update User Name");
                                System.out.println("2. Update Phone");
                                System.out.println("3. Update Address");
                                System.out.println("4. Change Password");
                                System.out.println("Press 0 key to go to previous menu!");
                                int choice2 = scannerUtil.readInt();
                                User user = getUser(uname);
                                switch (choice2) {
                                    case 1:
                                        System.out.println("Enter New User Name :");
                                        user.setUserName(scannerUtil.readLine());
                                        break;
                                    case 2:
                                        System.out.println("Enter New Phone :");
                                        user.setPhoneNumber(scannerUtil.readLine());
                                        break;
                                    case 3:
                                        System.out.println("Enter Address in a single line :");
                                        user.setAddress(scannerUtil.readLine());
                                        break;
                                    case 4:
                                        System.out.println("Enter your old password : ");
                                        String oldPassword = scannerUtil.readLine();
                                        if (user.getPassword().equals(oldPassword)) {
                                            System.out.println("Enter New Password :");
                                            String newPassword = scannerUtil.readLine();
                                            System.out.println("Confirm New Password :");
                                            String newCPassword = scannerUtil.readLine();
                                            if (newPassword.equals(newCPassword)) {
                                                user.setPassword(newPassword);
                                                System.out.println("Password changed successfully!");
                                            } else {
                                                System.out.println("Password mismatch try again!");
                                            }
                                        } else {
                                            session = 'n';
                                            session2 = 'n';
                                            System.out.println("Wrong Password!\n Logging you out! \n Please login with correct password again!");
                                        }
                                        break;
                                    case 0:
                                        session2 = 'n';
                                        System.out.println("Press 0 key to go to previous menu!");
                                        break;
                                    default:
                                        System.out.println("Invalid option!");
                                }
                            }
                            break;
                        case 6:
                            displayUsers(getUser(uname));
                            break;
                        case 7:
                            System.out.println("Enter your single line feedback : ");
                            ns.createFeedBack(scannerUtil.readLine(), getUser(uname));
                            System.out.println("Feedback sent to Admin!");
                            break;
                        case 8:
                            NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                            notificationHandler.ListNotification(uname);
                            break;
                        case 0:
                            session = 'n';
                            break;
                        default:
                            System.out.println("Invalid option!");
                    }
                }
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Your Application Not Approved Yet!");
        }
        ObjectSaver objectSaver = new ObjectSaver();
        objectSaver.saveAll();
    }
}
