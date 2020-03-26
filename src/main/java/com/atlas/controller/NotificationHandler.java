package com.atlas.controller;

import com.atlas.models.NotificationBuilder;
import com.atlas.models.Notifications;
import com.atlas.models.User;
import com.atlas.models.Visitor;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.ColourMe;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.Set;

public class NotificationHandler {
    private static NotificationHandler notification;
    HashMap<Integer, Notifications> note;

    private NotificationHandler() {
        note = new HashMap<Integer, Notifications>();
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
            HashMap<Integer, Notifications> temp = (HashMap<Integer, Notifications>) o;
            note = temp;
        }
    }

    public void createApplyPassNotification(String from, Visitor v) {
        NotificationBuilder notificationBuilder = new BusPassApplyNotification(from, v);
        NotificationManager manager = new NotificationManager(notificationBuilder);
        manager.makeNotification();
        Notifications notifications = manager.getNotification();
        note.put(notifications.getID(), notifications);
    }

    public void createCancelPassNotification(String from, User u) {
        NotificationBuilder notificationBuilder = new BusPassCancelNotification(from, u);
        NotificationManager manager = new NotificationManager(notificationBuilder);
        manager.makeNotification();
        Notifications notifications = manager.getNotification();
        note.put(notifications.getID(), notifications);
    }

    public void createSuspendPassNotification(String from, User u) {
        NotificationBuilder notificationBuilder = new BusPassSuspendNotification(from, u);
        NotificationManager manager = new NotificationManager(notificationBuilder);
        manager.makeNotification();
        Notifications notifications = manager.getNotification();
        note.put(notifications.getID(), notifications);
    }

    public void createFeedback(String from, String message) {
        NotificationBuilder notificationBuilder = new Feedback(from, message);
        NotificationManager manager = new NotificationManager(notificationBuilder);
        manager.makeNotification();
        Notifications notifications = manager.getNotification();
        note.put(notifications.getID(), notifications);
    }

    public void createNotification(String from, String to, String message) {
        NotificationBuilder notificationBuilder = new PrimaryNotification(from, to, message);
        NotificationManager manager = new NotificationManager(notificationBuilder);
        manager.makeNotification();
        Notifications notifications = manager.getNotification();
        note.put(notifications.getID(), notifications);
    }

    public void ListNotification(String to) {
        if (!note.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println(ColourMe.ANSI_BRIGHT_CYAN + String.format("%55s", "Notifications for " + to) + ColourMe.ANSI_RESET);
            Lines.lines();
            Set<Integer> keys = note.keySet();
            for (Integer key : keys) {
                Notifications notify = note.get(key);
                if (to.equals(notify.getTo())) {
                    System.out.println("Message ID : " + notify.getID());
                    System.out.println("Message From : " + notify.getFrom());
                    System.out.println("Message To : " + notify.getTo());
                    System.out.println("Message : " + notify.getMessage());
                    if (notify.getSupportingParameters() != null) {
                        System.out.println("Supporting Data : " + notify.getSupportingParameters());
                    }
                    Lines.lines();
                }
            }
            Lines.lines();
            System.out.println();
        } else {
            System.out.println(ColourMe.ANSI_BRIGHT_RED + "There is No Notification to display" + ColourMe.ANSI_RESET);
        }
    }

    public void clearNotification(int id) {
        if (note.containsKey(id)) {
            note.remove(id);
            System.out.println(ColourMe.ANSI_GREEN + "Notification Removed for the ID - " + id + "" + ColourMe.ANSI_RESET);
        } else {
            System.out.println(ColourMe.ANSI_RED + "Incorrect notification ID" + ColourMe.ANSI_RESET);
        }
    }

    public void clearAllNotification() {
        note.clear();
        System.out.println(ColourMe.ANSI_GREEN + "Cleared all the Notifications" + ColourMe.ANSI_RESET);
    }

    public Object getObject() {
        return note;
    }
}
