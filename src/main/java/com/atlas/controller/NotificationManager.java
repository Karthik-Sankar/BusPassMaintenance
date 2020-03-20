package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.NotifyConstants;


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

    public int getType() {
        return 0;
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

    public int getType() {
        return 1;
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
    int NotifyId;
    String from;
    String to;

    ModifyRouteNotification(String message, Route r, String from, String to) {
        this.message = message;
        this.r = r;
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

    public int getType() {
        return 3;
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
    int NotifyId;
    String from;
    String to;

    CreateNewRoute(String message, String route, String from, String to) {
        this.message = message;
        this.route = route;
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

    public int getType() {
        return 2;
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
            case NotifyConstants.ApplyBusPass:
                return new BusPassApplyNotification(message, (Visitor) o, from, to);
            case NotifyConstants.CancelBusPass:
                return new BusPassCancelNotification(message, (User) o, from, to);
            case NotifyConstants.NewRoute:
                return new CreateNewRoute(message, (String) o, from, to);
            case NotifyConstants.ModifyRoute:
                return new ModifyRouteNotification(message, (Route) o, from, to);
            default:
                return null;
        }
    }
}
