package com.atlas.controller;

import com.atlas.models.*;
import com.atlas.persistance.ObjectSaver;
import com.atlas.utils.BusPassConstants;
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
        notifications.setMessage("New user "+user.getUserId()+" has applied for bus pass!");
    }

    public void buildSupportingObject() {
        notifications.setSupportingObject(user);
        VisitorHandler visitorHandler = VisitorHandler.getInstance();
        visitorHandler.addVisitor(user.getUserId(), user.getPassword(), user.getUserName(), user.getPhoneNumber(), user.getAddress(), user.getRouteID());
        ObjectSaver saver = new ObjectSaver();
        saver.saveVisitor();
    }

    public Notifications getNotification(){
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
        notifications.setMessage("User "+ user.getUserName()+" has cancelled their bus pass! \n User Removed from subscriber list!");
    }

    public void buildSupportingObject() {
        notifications.setSupportingObject(user);
        BusPassHandler busPassHandler = BusPassHandler.getInstance();
        busPassHandler.busPass.get(user.getBusPass()).setBusPassStatus(BusPassConstants.CANCEL);
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        busHandler.bus.get(routeHandler.getBus(user.getRouteNum())).deccrementSeatFilled();
        UserHandler userHandler = UserHandler.getInstance();
        userHandler.user.remove(user.getUserId());
    }

    public Notifications getNotification(){
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
        notifications.setFrom("Admin");
    }

    public void buildMessage() {
        notifications.setMessage("User "+ user.getUserName()+" has suspended their bus pass!");
    }

    public void buildSupportingObject() {
        notifications.setSupportingObject(user);
        BusPassHandler busPassHandler = BusPassHandler.getInstance();
        busPassHandler.busPass.get(user.getBusPass()).setBusPassStatus(BusPassConstants.CANCEL);
        BusHandler busHandler = BusHandler.getInstance();
        RouteHandler routeHandler = RouteHandler.getInstance();
        busHandler.bus.get(routeHandler.getBus(user.getRouteNum())).deccrementSeatFilled();
    }

    public Notifications getNotification(){
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

    public Notifications getNotification(){
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
    public Notifications getNotification(){
        return this.notifications;
    }
}


public class NotificationManager {
    private NotificationBuilder notificationBuilder;
    public NotificationManager(NotificationBuilder notificationBuilder){
        this.notificationBuilder = notificationBuilder;
    }
    public Notifications getNotification(){
        return this.notificationBuilder.getNotification();
    }
    public void makeNotification(){
        this.notificationBuilder.buildID();
        this.notificationBuilder.buildFrom();
        this.notificationBuilder.buildTo();
        this.notificationBuilder.buildMessage();
        this.notificationBuilder.buildSupportingObject();
    }
}
