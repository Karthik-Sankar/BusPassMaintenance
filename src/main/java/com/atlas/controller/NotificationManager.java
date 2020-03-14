package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.utils.IDGenerator;


class BusPassApplyNotification implements Notification {
    String message;
    Visitor u;
    int NotifyId;
    String from;
    String to;

    BusPassApplyNotification(String message, Visitor u, String from, String to) {
        this.message = message;
        this.u = u;
        this.NotifyId = IDGenerator.getNotifyID();
        this.from = from;
        this.to = to;
    }

    public int getID() {
        return NotifyId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return "BUSPASSAPPLY";
    }

    public String getMessage() {
        return message;
    }

    public Visitor getObj() {
        return u;
    }
}

class BusPassCancelNotification implements Notification {
    String message;
    User u;
    int NotifyId;
    String from;
    String to;

    BusPassCancelNotification(String message, User u, String from, String to) {
        this.message = message;
        this.u = u;
        this.NotifyId = IDGenerator.getNotifyID();
        this.from = from;
        this.to = to;
    }

    public int getID() {
        return NotifyId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
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
    String userId;
    int NotifyId;
    String from;
    String to;

    ModifyRouteNotification(String message, Route r, String from, String to) {
        this.message = message;
        this.r = r;
        this.userId = userId;
        this.NotifyId = IDGenerator.getNotifyID();
        this.from = from;
        this.to = to;
    }

    public int getID() {
        return NotifyId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
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
    String route;
    String userId;
    int NotifyId;
    String from;
    String to;

    CreateNewRoute(String message, String route, String from, String to) {
        this.message = message;
        this.route = route;
        this.userId = userId;
        this.NotifyId = IDGenerator.getNotifyID();
        this.from = from;
        this.to = to;
    }

    public int getID() {
        return NotifyId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getType() {
        return "ROUTECREATE";
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return route;
    }
}


public class NotificationManager {
    public static Notification getNotificationType(int type, String message, Object o, String from, String to) {
        switch (type) {
            case 0:
                return new BusPassApplyNotification(message, (Visitor) o, from, to);
            case 1:
                return new BusPassCancelNotification(message, (User) o, from, to);
            case 2:
                return new CreateNewRoute(message, (String) o, from, to);
            case 3:
                return new ModifyRouteNotification(message, (Route) o, from, to);
            default:
                return null;
        }
    }
}
