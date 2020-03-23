package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.BusPass;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.ColourMe;
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
            user = temp;
        }
    }

    public static UserHandler getInstance() {
        if (users == null) {
            users = new UserHandler();
        }
        return users;
    }

    public void addUser(int bid, String userId, String password, String userName, String phoneNumber, String address, int routeNum) {
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
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s","Users Available")+ColourMe.ANSI_RESET);
            Lines.lines();
            Set<String> u = user.keySet();
            for (String u1 : u) {
                User element = user.get(u1);
                System.out.println("Buspass Info   : " + String.format("%25s",element.getBusPass()));
                System.out.println("Employee ID    : " + String.format("%25s",element.getUserId())+"\t\t\t"+"UserName       : " + String.format("%25s",element.getUserName()));
                System.out.println("Phone Number   : " + String.format("%25s",element.getPhoneNumber())+"\t\t\t"+"Route Number   : " + String.format("%25s",element.getRouteNum()));
                System.out.println("Address        : "+String.format("%25s",element.getAddress()));
                Lines.lines();
            }
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"No Users registered yet!!"+ColourMe.ANSI_RESET);
        }
    }

    public void displayUsers(User u) {
        if (user.containsKey(u.getUserId())) {
            RouteHandler routeHandler = RouteHandler.getInstance();
            Route r = routeHandler.route.get(u.getRouteNum());
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN+String.format("%55s",u.getUserId()+"\'s Profile")+ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println("Buspass Info   : " + String.format("%25s",u.getBusPass()));
            System.out.println("Employee ID    : " + String.format("%25s",u.getUserId())+"\t\t\t"+"UserName       : " + String.format("%25s",u.getUserName()));
            System.out.println("Phone Number   : " + String.format("%25s",u.getPhoneNumber())+"\t\t\t"+"Route Number   : " + String.format("%25s",u.getRouteNum()));
            if(r.getBus()!=-1)
            System.out.println("Route          : " + String.format("%25s",r.getSource()+"-"+r.getDestination())+"\t\t\t"+"Bus No         : "+String.format("%25s", r.getBus()));
            else
            System.out.println("Route          : " + String.format("%25s",r.getSource()+"-"+r.getDestination())+"\t\t\t"+"Bus No         : "+ColourMe.ANSI_BRIGHT_RED+String.format("%25s", "No bus assigned yet!")+ColourMe.ANSI_RESET);
            System.out.println("Address        : " + String.format("%25s",u.getAddress()));
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED+"Specified User is not available in the list!!"+ColourMe.ANSI_RESET);
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

    public void assignRouteToUser(String userId, int routeId) {

    }

    public Object getObject() {
        return user;
    }

    public void UserEntry() {
        ObjectSaver objectSaver = new ObjectSaver();
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
                            ns.createCancelPassNotification(uname, user.get(uname));
                            System.out.println("Bus pass cancelled!!");
                            objectSaver.saveAll();
                            break;
                        case 3:
                            ns.createSuspendPassNotification(uname, user.get(uname));
                            System.out.println("Bus pass suspended!!");
                            objectSaver.saveAll();
                            break;
                        case 4:
                            System.out.println("Enter new route as (Source-Destination) :");
                            ns.createNotification(uname,"Admin", "Create a new route between "+scannerUtil.readLine());
                            System.out.println("New route request placed!");
                            objectSaver.saveAll();
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
                                        objectSaver.saveAll();
                                        break;
                                    case 2:
                                        System.out.println("Enter New Phone :");
                                        user.setPhoneNumber(scannerUtil.readLine());
                                        objectSaver.saveAll();
                                        break;
                                    case 3:
                                        System.out.println("Enter Address in a single line :");
                                        user.setAddress(scannerUtil.readLine());
                                        objectSaver.saveAll();
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
                                        objectSaver.saveAll();
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
                            ns.createFeedback(uname, scannerUtil.readLine());
                            System.out.println("Feedback sent to Admin!");
                            objectSaver.saveAll();
                            break;
                        case 8:
                            NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                            notificationHandler.ListNotification(uname);
                            objectSaver.saveAll();
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
    }
}
