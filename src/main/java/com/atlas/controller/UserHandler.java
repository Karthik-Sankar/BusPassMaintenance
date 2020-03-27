package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.BusPassConstants;
import com.atlas.utils.ColourMe;
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
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%55s", "Users Available") + ColourMe.ANSI_RESET);
            Lines.lines();
            Set<String> u = user.keySet();
            for (String u1 : u) {
                User element = user.get(u1);
                System.out.println("Buspass Info   : " + String.format("%25s", element.getBusPass()));
                System.out.println("Employee ID    : " + String.format("%25s", element.getUserId()) + "\t\t\t" + "UserName       : " + String.format("%25s", element.getUserName()));
                if (element.getRouteNum() != -1)
                    System.out.println("Phone Number   : " + String.format("%25s", element.getPhoneNumber()) + "\t\t\t" + "Route Number   : " + String.format("%25s", element.getRouteNum()));
                else
                    System.out.println("Phone Number   : " + String.format("%25s", element.getPhoneNumber()) + "\t\t\t" + "Route Number   : " + String.format("%25s", "User not tagged to a route currently!"));
                System.out.println("Address        : " + String.format("%25s", element.getAddress()));
                Lines.lines();
            }
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED + "No Users registered yet!!" + ColourMe.ANSI_RESET);
        }
    }

    public void displayUsers(User u) {
        if (user.containsKey(u.getUserId())) {
            RouteHandler routeHandler = RouteHandler.getInstance();
            Route r = routeHandler.route.get(u.getRouteNum());
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%55s", u.getUserId() + "\'s Profile") + ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println("Buspass Info   : " + String.format("%25s", u.getBusPass()));
            System.out.println("Employee ID    : " + String.format("%25s", u.getUserId()) + "\t\t\t" + "UserName       : " + String.format("%25s", u.getUserName()));
            System.out.println("Phone Number   : " + String.format("%25s", u.getPhoneNumber()) + "\t\t\t" + "Route Number   : " + String.format("%25s", u.getRouteNum()));
            if (r != null && r.getBus() != -1)
                System.out.println("Route          : " + String.format("%25s", r.getSource() + "-" + r.getDestination()) + "\t\t\t" + "Bus No         : " + String.format("%25s", r.getBus()));
            else if (r != null)
                System.out.println("Route          : " + String.format("%25s", r.getSource() + "-" + r.getDestination()) + "\t\t\t" + "Bus No         : " + ColourMe.ANSI_BRIGHT_RED + String.format("%25s", "No bus assigned yet!") + ColourMe.ANSI_RESET);
            else
                System.out.println("Route          : " + String.format("%25s", ColourMe.ANSI_BRIGHT_RED + String.format("%25s", "No route assigned to user!") + ColourMe.ANSI_RESET) + "\t\t\t" + "Bus No         : " + ColourMe.ANSI_BRIGHT_RED + String.format("%25s", "No bus assigned yet!") + ColourMe.ANSI_RESET);
            System.out.println("Address        : " + String.format("%25s", u.getAddress()));
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED + "Specified User is not available in the list!" + ColourMe.ANSI_RESET);
        }
    }

    public void updateUserRoute(String userId, int routeId) {
        RouteHandler routeHandler = RouteHandler.getInstance();
        if (user.containsKey(userId) && routeHandler.route.containsKey(routeId)) {
            BusHandler busHandler = BusHandler.getInstance();
            Bus b = busHandler.bus.get(routeHandler.route.get(routeId).getBus());
            if(b.getBusId()!=-1){
                if(b.getSeatFilled() <= b.getTotalCapacity()){
                    int oldr = user.get(userId).getRouteNum();
                    if(oldr!=-1){
                        if(busHandler.getBus(routeHandler.getBus(routeId))!=null){
                            busHandler.getBus(routeHandler.getBus(routeId)).deccrementSeatFilled();
                        }
                    }
                    user.get(userId).setRouteNum(routeId);
                    b.incrementSeatFilled();
                }
                else{
                    System.out.println(ColourMe.ANSI_RED+"No space available in the given route!"+ColourMe.ANSI_RESET);
                }
            }
            else{
                System.out.println(ColourMe.ANSI_RED+"cant assign user when there is no bus in route!"+ColourMe.ANSI_RESET);
            }
        } else {
            System.out.println("Invalid UserID/RouteID!!");
        }
    }


    public Object getObject() {
        return user;
    }

    public void UserEntry() {
        ObjectSaver objectSaver = new ObjectSaver();
        RouteHandler routeHandler = RouteHandler.getInstance();
        BusPassHandler busPassHandler = BusPassHandler.getInstance();
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
                    Lines.menulines();
                    System.out.println(ColourMe.ANSI_BRIGHT_YELLOW + "User Controls:" + ColourMe.ANSI_RESET);
                    Lines.menulines();
                    System.out.println(ColourMe.ANSI_BLUE);
                    System.out.println("1. View all the routes");
                    System.out.println("2. Cancel a bus pass");
                    System.out.println("3. Suspend a bus pass");
                    System.out.println("4. Activate bus pass");
                    System.out.println("5. Request for new route");
                    System.out.println("6. Update their contact details");
                    System.out.println("7. Get a snapshot of their details");
                    System.out.println("8. Submit feedback");
                    System.out.println("9. Notifications");
                    System.out.println("Press 0 key to go to main menu!");
                    System.out.println(ColourMe.ANSI_RESET);
                    ScannerUtil input = ScannerUtil.getInstance();
                    int choice = input.readInt();
                    switch (choice) {
                        case 1:
                            routeHandler.displayRoute();
                            break;
                        case 2:
                            if (busPassHandler.getBusPass(user.get(uname).getBusPass()).getBusPassStatus() != BusPassConstants.CANCEL) {
                                ns.createCancelPassNotification(uname, user.get(uname));
                                objectSaver.saveAll();
                                System.out.println(ColourMe.ANSI_GREEN + "Bus pass cancelled!!" + ColourMe.ANSI_RESET);
                                System.out.println(ColourMe.ANSI_RED + "Logging you out!" + ColourMe.ANSI_RESET);
                                session = 'n';
                            } else {
                                System.out.println(ColourMe.ANSI_RED + "User has no active bus pass!" + ColourMe.ANSI_RESET);
                            }
                            break;
                        case 3:
                            if (busPassHandler.getBusPass(user.get(uname).getBusPass()).getBusPassStatus() != BusPassConstants.SUSPEND) {
                                ns.createSuspendPassNotification(uname, user.get(uname));
                                System.out.println(ColourMe.ANSI_GREEN + "Bus pass suspended!!" + ColourMe.ANSI_RESET);
                                objectSaver.saveAll();
                            } else {
                                System.out.println(ColourMe.ANSI_RED + "User has no active bus pass!" + ColourMe.ANSI_RESET);
                            }
                            break;
                        case 4:
                            if (busPassHandler.getBusPass(user.get(uname).getBusPass()).getBusPassStatus() != BusPassConstants.ACTIVE) {
                                BusHandler busHandler = BusHandler.getInstance();
                                Route r = routeHandler.route.get(user.get(uname).getRouteNum());
                                Bus b = null;
                                if (r != null)
                                    b = busHandler.getBus(r.getBus());
                                if (b != null) {
                                    if(b.getSeatFilled()<b.getTotalCapacity()) {
                                        busPassHandler.busPass.get(user.get(uname).getBusPass()).setBusPassStatus(BusPassConstants.ACTIVE);
                                        b.incrementSeatFilled();
                                        ns.createNotification(uname,"Admin","User has activated their bus pass again!");
                                        System.out.println(ColourMe.ANSI_GREEN + "Bus pass activated!!" + ColourMe.ANSI_RESET);
                                        objectSaver.saveAll();
                                    }
                                    else{
                                        System.out.println(ColourMe.ANSI_RED + "No seat available currently!" + ColourMe.ANSI_RESET);
                                        break;
                                    }
                                }
                            } else {
                                System.out.println(ColourMe.ANSI_RED + "User has active bus pass already!" + ColourMe.ANSI_RESET);
                            }
                            break;
                        case 5:
                            System.out.println("Enter new route as (Source-Destination) :");
                            ns.createNotification(uname, "Admin", "Create a new route between " + scannerUtil.readLine());
                            System.out.println(ColourMe.ANSI_GREEN + "New route request placed!" + ColourMe.ANSI_RESET);
                            objectSaver.saveAll();
                            break;
                        case 6:
                            char session2 = 'y';
                            while (session2 == 'y') {
                                System.out.println(ColourMe.ANSI_BLUE);
                                System.out.println("Update User Details");
                                System.out.println("1. Update User Name");
                                System.out.println("2. Update Phone");
                                System.out.println("3. Update Address");
                                System.out.println("4. Change Password");
                                System.out.println("Press 0 key to go to previous menu!");
                                System.out.println(ColourMe.ANSI_RESET);
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
                                                System.out.println(ColourMe.ANSI_GREEN + "Password changed successfully!" + ColourMe.ANSI_RESET);
                                            } else {
                                                System.out.println(ColourMe.ANSI_RED + "Password mismatch try again!" + ColourMe.ANSI_RESET);
                                            }
                                        } else {
                                            session = 'n';
                                            session2 = 'n';
                                            System.out.println(ColourMe.ANSI_RED + "Wrong Password!\n Logging you out! \n Please login with correct password again!" + ColourMe.ANSI_RESET);
                                        }
                                        objectSaver.saveAll();
                                        break;
                                    case 0:
                                        session2 = 'n';
                                        break;
                                    default:
                                        System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                                }
                            }
                            break;
                        case 7:
                            displayUsers(getUser(uname));
                            break;
                        case 8:
                            System.out.println("Enter your single line feedback : ");
                            ns.createFeedback(uname, scannerUtil.readLine());
                            System.out.println(ColourMe.ANSI_GREEN + "Feedback sent to Admin!" + ColourMe.ANSI_RESET);
                            objectSaver.saveAll();
                            break;
                        case 9:
                            NotificationHandler notificationHandler = NotificationHandler.getNotificationInstance();
                            notificationHandler.ListNotification(uname);
                            objectSaver.saveAll();
                            break;
                        case 0:
                            session = 'n';
                            break;
                        default:
                            System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                    }
                }
            } else {
                System.out.println(ColourMe.ANSI_RED + "Incorrect password!" + ColourMe.ANSI_RESET);
            }
        } else {
            System.out.println(ColourMe.ANSI_RED + "Your Application Not Approved Yet!" + ColourMe.ANSI_RESET);
        }
    }
}
