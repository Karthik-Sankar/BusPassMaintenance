package com.atlas.controller;

import com.atlas.models.*;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.BusPassConstants;
import com.atlas.utils.ColourMe;
import com.atlas.utils.IDGenerator;


class BusPassApplyNotification implements NotificationBuilder {

    private Notifications notifications;
    private String From;
    private Visitor user;

    public BusPassApplyNotification(String From, Visitor user) {
        this.notifications = new Notifications();
        this.From = From;
        this.user = user;
    }

    public void buildID() {
        notifications.setID(IDGenerator.getNotifyID());
    }

    public void buildFrom() {
        notifications.setFrom(From);
    }

    public void buildTo() {
        notifications.setTo("Admin");
    }

    public void buildMessage() {
        notifications.setMessage(ColourMe.ANSI_GREEN + "New user " + user.getUserId() + " has applied for bus pass!" + ColourMe.ANSI_RESET);
    }

    public void buildSupportingObject() {
        //Creates visitor object for admin to use it while approve / reject application
        notifications.setSupportingObject(user);
        VisitorHandler visitorHandler = VisitorHandler.getInstance();
        visitorHandler.addVisitor(user.getUserId(), user.getPassword(), user.getUserName(), user.getPhoneNumber(), user.getAddress(), user.getRouteID());
        ObjectSaver saver = new ObjectSaver();
        saver.saveVisitor();
    }

    public Notifications getNotification() {
        return this.notifications;
    }
}

class BusPassCancelNotification implements NotificationBuilder {

    private Notifications notifications;
    private String From;
    private User user;

    public BusPassCancelNotification(String From, User user) {
        this.notifications = new Notifications();
        this.From = From;
        this.user = user;
    }

    public void buildID() {
        notifications.setID(IDGenerator.getNotifyID());
    }

    public void buildFrom() {
        notifications.setFrom(From);
    }

    public void buildTo() {
        notifications.setTo("Admin");
    }

    public void buildMessage() {
        notifications.setMessage(ColourMe.ANSI_RED + "User " + user.getUserName() + " has cancelled their bus pass! \n User Removed from subscriber list!" + ColourMe.ANSI_RESET);
    }

    public void buildSupportingObject() {
        //Cancel bus pass of the user and remove the user from database
        notifications.setSupportingObject(user);
        BusPassHandler busPassHandler = BusPassHandler.getInstance();
        busPassHandler.busPass.get(user.getBusPass()).setBusPassStatus(BusPassConstants.CANCEL);
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        Route r = routeHandler.route.get(user.getRouteNum());
        Bus b = null;
        if (r != null)
            b = busHandler.getBus(r.getBus());
        if (b != null)
            b.deccrementSeatFilled();
        UserHandler userHandler = UserHandler.getInstance();
        userHandler.user.remove(user.getUserId());
    }

    public Notifications getNotification() {
        return this.notifications;
    }
}

class BusPassSuspendNotification implements NotificationBuilder {

    private Notifications notifications;
    private String From;
    private User user;

    public BusPassSuspendNotification(String From, User user) {
        this.notifications = new Notifications();
        this.From = From;
        this.user = user;
    }

    public void buildID() {
        notifications.setID(IDGenerator.getNotifyID());
    }

    public void buildFrom() {
        notifications.setFrom(From);
    }

    public void buildTo() {
        notifications.setTo("Admin");
    }

    public void buildMessage() {
        notifications.setMessage(ColourMe.ANSI_YELLOW + "User " + user.getUserName() + " has suspended their bus pass!" + ColourMe.ANSI_RESET);
    }

    public void buildSupportingObject() {
        //Changing the bus pass's state from active to suspend! This will also release the seat used by
        notifications.setSupportingObject(user);
        BusPassHandler busPassHandler = BusPassHandler.getInstance();
        busPassHandler.busPass.get(user.getBusPass()).setBusPassStatus(BusPassConstants.SUSPEND);
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        Route r = routeHandler.route.get(user.getRouteNum());
        Bus b = null;
        if (r != null)
            b = busHandler.getBus(r.getBus());
        if (b != null)
            b.deccrementSeatFilled();
    }

    public Notifications getNotification() {
        return this.notifications;
    }
}


class PrimaryNotification implements NotificationBuilder {
    private Notifications notifications;
    private int ID;
    private String From;
    private String To;
    private String Message;

    public PrimaryNotification(String from, String to, String message) {
        this.notifications = new Notifications();
        this.ID = IDGenerator.getNotifyID();
        From = from;
        To = to;
        Message = message;
    }

    public void buildID() {
        notifications.setID(ID);
    }

    public void buildFrom() {
        notifications.setFrom(From);
    }

    public void buildTo() {
        notifications.setTo(To);
    }

    public void buildMessage() {
        notifications.setMessage(Message);
    }

    public void buildSupportingObject() {
        notifications.setSupportingObject(null);
    }

    public Notifications getNotification() {
        return this.notifications;
    }
}

class Feedback implements NotificationBuilder {
    private Notifications notifications;
    private int ID;
    private String From;
    private String Message;

    public Feedback(String from, String message) {
        this.notifications = new Notifications();
        this.ID = IDGenerator.getNotifyID();
        From = from;
        Message = message;
    }

    public void buildID() {
        notifications.setID(ID);
    }

    public void buildFrom() {
        notifications.setFrom(From);
    }

    public void buildTo() {
        notifications.setTo("Admin");
    }

    public void buildMessage() {
        notifications.setMessage(Message);
    }

    public void buildSupportingObject() {
        notifications.setSupportingObject(null);
    }

    public Notifications getNotification() {
        return this.notifications;
    }
}


public class NotificationManager {
    private NotificationBuilder notificationBuilder;

    public NotificationManager(NotificationBuilder notificationBuilder) {
        this.notificationBuilder = notificationBuilder;
    }

    public Notifications getNotification() {
        return this.notificationBuilder.getNotification();
    }

    public void makeNotification() {
        this.notificationBuilder.buildID();
        this.notificationBuilder.buildFrom();
        this.notificationBuilder.buildTo();
        this.notificationBuilder.buildMessage();
        this.notificationBuilder.buildSupportingObject();
    }
}
