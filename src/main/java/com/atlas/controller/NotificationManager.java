package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.Route;
import com.atlas.models.User;

class BusPassApplyNotification implements Notification {
    String message;
    User u;
    BusPassApplyNotification(String message, User u){
        this.message = message;
        this.u = u;
    }
    public String getType() {
        return "BUSPASSAPPLY";
    }

    public String getMessage() {
        return message;
    }

    public User getObj() {
        return u;
    }
}

class BusPassCancelNotification implements Notification {
    String message;
    User u;
    BusPassCancelNotification(String message, User u){
        this.message = message;
        this.u = u;
    }
    public String getType() {
        return "BUSPASSCANCEL";
    }

    public String getMessage() {
        return message;
    }

    public User getObj() {
        return u;
    }
}


class ModifyRouteNotification implements Notification {
    String message;
    Route r;
    ModifyRouteNotification(String message, Route r){
        this.message = message;
        this.r = r;
    }
    public String getType() {
        return "ROUTEMODIFY";
    }

    public String getMessage() {
        return message;
    }

    public Route getObj() {
        return r;
    }
}

class CreateNewRoute implements Notification {
    String message;
    Route r;
    CreateNewRoute(String message, Route r){
        this.message = message;
        this.r = r;
    }
    public String getType() {
        return "ROUTECREATE";
    }

    public String getMessage() {
        return message;
    }

    public Route getObj() {
        return r;
    }
}


public class NotificationManager {
    public static Notification getNotificationType(int type, String message, Object o){
        switch(type){
            case 0:
                return new BusPassApplyNotification(message, (User)o);
            case 1:
                return new BusPassCancelNotification(message, (User)o);
            case 2:
                return  new CreateNewRoute(message, (Route)o);
            case 3:
                return new ModifyRouteNotification(message, (Route)o);
            default:
                return null;
        }
    }
}
