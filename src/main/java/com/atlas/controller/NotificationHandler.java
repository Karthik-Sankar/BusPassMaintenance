package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;
import com.atlas.utils.NotifyConstants;

import java.util.HashMap;
import java.util.Set;

public class NotificationHandler {
    private static NotificationHandler notification;
    HashMap<Integer, Notification> note;

    private NotificationHandler() {
        note = new HashMap<Integer, Notification>();
        initalize();
    }

    public static NotificationHandler getNotificationInstance() {
        if (notification == null) {
            notification = new NotificationHandler();
        }
        return notification;
    }

    public void initalize() {
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getNotificationObj();
        if (o != null) {
            HashMap<Integer, Notification> temp = (HashMap<Integer, Notification>) o;
            Set<Integer> keys = temp.keySet();
            for (Integer key : keys) {
                Notification n = temp.get(key);
                createNotifications(n.getType(), n.getMessage(), n.getObj(), n.getFrom(), n.getTo());
            }
        }
    }

    public void createNotifications(int type, String message, Object o, String from, String to) {
        Notification n = NotificationManager.getNotificationType(type, message, o, from, to);
        note.put(n.getID(), n);
    }

    public void createBusPassApplicationNotification(String message, String UserID, Visitor visitor) {
        Notification n = NotificationManager.getNotificationType(NotifyConstants.ApplyBusPass, message, visitor, UserID, "Admin");
        note.put(n.getID(), n);
    }

    public void cancelBusPassNotification(User user) {
        Notification n = NotificationManager.getNotificationType(NotifyConstants.CancelBusPass, "User " + user.getUserId() + " has cancelled their Bus pass!", user, user.getUserId(), "Admin");
        note.put(n.getID(), n);
    }

    public void suspendBusPassNotification(User user) {
        Notification n = NotificationManager.getNotificationType(NotifyConstants.SuspendBusPass, "User " + user.getUserId() + " has suspended their Bus pass!", user, user.getUserId(), "Admin");
        note.put(n.getID(), n);
    }

    public void createFeedBack(String feedback, User user) {
        Notification n = NotificationManager.getNotificationType(NotifyConstants.Feedback, feedback, user, user.getUserId(), "Admin");
        note.put(n.getID(), n);
    }

    public void ListNotification(String to) {
        if (!note.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println("Notifications for " + to);
            Lines.lines();
            Set<Integer> keys = note.keySet();
            for (Integer key : keys) {
                Notification notify = note.get(key);
                if (to.equals(notify.getTo())) {
                    System.out.println("Message ID : " + notify.getID());
                    System.out.println("Message From : " + notify.getFrom());
                    System.out.println("Message To : " + notify.getTo());
                    System.out.println("Message Type : " + notify.getType());
                    System.out.println("Message : " + notify.getMessage());
                    System.out.println("Supporting Data : " + notify.getObj());
                    Lines.lines();
                }
            }
            Lines.lines();
        } else {
            System.out.println("There is No Notification to display");
        }
    }

    public void clearNotification(int id) {
        if (note.containsKey(id)) {
            note.remove(id);
            System.out.println("Notification Removed for the ID - " + id);
            System.out.println("Notification Deleted!");
        } else {
            System.out.println("Incorrect notification ID");
        }
    }

    public void clearAllNotification() {
        note.clear();
        System.out.println("Cleared all the Notifications");
    }

    public Object getObject() {
        return note;
    }
}
