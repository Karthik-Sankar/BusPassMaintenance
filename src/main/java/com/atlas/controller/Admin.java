package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.Lines;
import com.atlas.utils.ScannerUtil;

import java.util.Set;

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
        //Authenticating user as admin
        if (uname.equals("admin") && passwd.equals("admin123")) {
            isAuthenticated = true;
        } else {
            System.out.println(ColourMe.ANSI_RED + "You are not an administrator!" + ColourMe.ANSI_RESET);
            return;
        }
        //displaying error alerts
        routeHandler.getNoBusRoutes();
        char session = 'y';
        while (session == 'y') {
            Lines.menulines();
            System.out.println(ColourMe.ANSI_BRIGHT_YELLOW + "Admin Controls:" + ColourMe.ANSI_RESET);
            Lines.menulines();
            System.out.print(ColourMe.ANSI_BLUE);
            System.out.println("1: Notification");
            System.out.println("2: Approve/Reject Application");
            System.out.println("3: Route Operations");
            System.out.println("4: List User details");
            System.out.println("5: Assign user to route");
            System.out.println("6: Bus Operations");
            System.out.println("7: Reporting options");
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
                                //If there are no buses available then we cant create a route. Logic to create a bus before route
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
                                    buses.listBuses();
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
                    //Assigning user to a new route if their route got removed
                    System.out.println("Enter user id :");
                    String userid = scannerUtil.readLine();
                    System.out.println("Available routes :");
                    if (routeHandler.availableValidRoutes()) {
                        System.out.println("Enter route id :");
                        int rid = scannerUtil.readInt();
                        userHandler.updateUserRoute(userid, rid);
                    } else {
                        System.out.println(ColourMe.ANSI_RED + "No free routes!" + ColourMe.ANSI_RESET);
                    }
                    break;
                case 6:
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
                            case 0:
                                session5 = 'n';
                                break;
                            default:
                                System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
                                break;
                        }
                    }
                    break;
                case 7:
                    char session7 = 'y';
                    while (session7 == 'y') {
                        System.out.println(ColourMe.ANSI_BLUE);
                        System.out.println("Reporting Options");
                        System.out.println("1. Finance Team Report");
                        System.out.println("2. Vehicle summary");
                        System.out.println("Press 0 key to go to previous menu!");
                        System.out.println(ColourMe.ANSI_RESET);
                        int choice7 = scannerUtil.readInt();
                        switch (choice7) {
                            case 1:
                                generateFinanceTeamReport();
                                break;
                            case 2:
                                generateVehicleSummary();
                                break;
                            case 0:
                                session7 = 'n';
                                break;
                            default:
                                System.out.println(ColourMe.ANSI_RED + "Invalid option!" + ColourMe.ANSI_RESET);
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
        //Visitor details will be stored once a visitor applies for a bus pass, we use that to approve / reject a bus pass.
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
                //checking if we can accomodate a new user
                if (b.getSeatFilled() + 1 <= b.getTotalCapacity()) {
                    b.incrementSeatFilled();
                    busPasses.addBusPass(busPassID, routeID, userID, b.getBusId());
                    userHandler.addUser(busPassID, userID, paswd, usname, phone, addr, routeID);
                    //removing visitor once added as user
                    visitorHandler.visitor.remove(userId);
                    NotificationHandler handler = NotificationHandler.getNotificationInstance();
                    handler.createNotification("Amazon Transport", userID, ColourMe.ANSI_GREEN + "Your bus pass has been approved!" + ColourMe.ANSI_RESET);
                    handler.clearNotification(handler.getVisitorNotificationID(userID));
                    System.out.println(ColourMe.ANSI_GREEN + "User " + v.getUserName() + " Application Approved" + ColourMe.ANSI_RESET);
                } else {
                    System.out.println(ColourMe.ANSI_RED + "No seats available in the route!" + ColourMe.ANSI_RESET);
                }
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
            handler.clearNotification(handler.getVisitorNotificationID(userId2));
            System.out.println(ColourMe.ANSI_RED + "User " + userId2 + " Application Rejected" + ColourMe.ANSI_RESET);
        } else {
            System.out.println(ColourMe.ANSI_RED + "No such user applied!" + ColourMe.ANSI_RESET);
        }
    }

    private void generateFinanceTeamReport() {
        UserHandler userHandler = UserHandler.getInstance();
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        if (!userHandler.user.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%70s", "Finance Report on Transport Utilization") + ColourMe.ANSI_RESET);
            Lines.lines();
            System.out.println(String.format("%12s", "Employee ID") + "\t" + String.format("%25s", "Bus Type") + "\t" + String.format("%25s", "Route Number") + "\t" + String.format("%25s", "Number of Stops"));
            Lines.lines();
            Set<String> u = userHandler.user.keySet();
            for (String u1 : u) {
                User element = userHandler.user.get(u1);
                if (element.getRouteNum() != -1) {
                    Bus b = busHandler.getBus(routeHandler.getBus(element.getRouteNum()));
                    if (b != null) {
                        if (b.getBusId() != -1) {
                            System.out.println(String.format("%12s", element.getUserId()) + "\t" + String.format("%25s", b.getBusType()) + "\t" + String.format("%25s", element.getRouteNum()) + "\t" + String.format("%25s", routeHandler.route.get(element.getRouteNum()).getStops().size()));
                        }
                    }
                }
                Lines.lines();
            }
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED + "No Users registered yet!!" + ColourMe.ANSI_RESET);
        }
    }

    private void generateVehicleSummary() {
        UserHandler userHandler = UserHandler.getInstance();
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        int ac = 0;
        int non_ac = 0;
        int other = 0;
        if (!busHandler.bus.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%55s", "Vehicle Type Summary") + ColourMe.ANSI_RESET);
            Lines.lines();
            Set<Integer> keys = busHandler.bus.keySet();
            for (Integer key : keys) {
                Bus bp = busHandler.bus.get(key);
                if (bp.getBusType().equals("AC")) {
                    ac++;
                } else if (bp.getBusType().equals("NON-AC")) {
                    non_ac++;
                } else {
                    other++;
                }
            }
            System.out.println(String.format("%17s", "AC Buses") + "\t" + String.format("%25s", ac) + " Nos");
            System.out.println(String.format("%17s", "Non-AC Buses") + "\t" + String.format("%25s", non_ac) + " Nos");
            System.out.println(String.format("%17s", "Other Type Buses") + "\t" + String.format("%25s", other) + " Nos");
            Lines.lines();
        } else {
            System.out.println(ColourMe.ANSI_RED + "No buses added yet!" + ColourMe.ANSI_RESET);
            System.out.println();
        }
    }
}
