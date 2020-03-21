package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.Route;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.utils.BusPassConstants;
import com.atlas.utils.IDGenerator;
import com.atlas.utils.NotifyConstants;


class BusPassApplyNotification implements Notification {
    String message;
    Visitor v;
    int NotifyId;
    String from;
    String to;

    BusPassApplyNotification(String message, Visitor v, String from, String to) {
        this.message = message;
        this.v = v;
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
        return NotifyConstants.ApplyBusPass;
    }

    public String getMessage() {
        return message;
    }

    public Visitor getObj() {
        return v;
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
        u.getBusPass().setBusPassStatus(BusPassConstants.CANCEL);
        u.getBusPass().getBus().deccrementSeatFilled();
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
        return NotifyConstants.CancelBusPass;
    }

    public String getMessage() {
        return message;
    }

    public User getObj() {
        return u;
    }
}

class BusPassSuspendNotification implements Notification {
    String message;
    User u;
    int NotifyId;
    String from;
    String to;

    BusPassSuspendNotification(String message, User u, String from, String to) {
        this.message = message;
        this.u = u;
        this.NotifyId = IDGenerator.getNotifyID();
        this.from = from;
        this.to = to;
        u.getBusPass().setBusPassStatus(BusPassConstants.SUSPEND);
        u.getBusPass().getBus().deccrementSeatFilled();
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
        return NotifyConstants.SuspendBusPass;
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
        return NotifyConstants.ModifyRoute;
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
        return NotifyConstants.NewRoute;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return route;
    }
}

class Feedback implements Notification {
    String message;
    Object o;
    int NotifyId;
    String from;
    String to;

    Feedback(String message, Object o, String from, String to) {
        this.message = message;
        this.o = o;
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
        return NotifyConstants.Feedback;
    }

    public String getMessage() {
        return message;
    }

    public Object getObj() {
        return o;
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
            case NotifyConstants.SuspendBusPass:
                return new BusPassSuspendNotification(message, (User)o, from, to);
            case NotifyConstants.Feedback:
                return new Feedback(message, o, from, to);
            default:
                return null;
        }
    }
}
