package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.utils.Lines;

import java.util.Iterator;
import java.util.LinkedList;

public class NotificationHandler {
    private static NotificationHandler notification;
    LinkedList<Notification> note;

    private NotificationHandler() {
        note = new LinkedList<Notification>();
    }

    public static NotificationHandler getNotificationInstance() {
        if (notification == null) {
            notification = new NotificationHandler();
        }
        return notification;
    }

    public void createNotifications(int type, String message, Object o, String from, String to) {
        Notification n = NotificationManager.getNotificationType(type, message, o, from, to);
        note.add(n);
    }

    public void ListNotification(String to) {
        System.out.println();
        Lines.lines();
        System.out.println("Notifications for "+to);
        Lines.lines();
        Iterator<Notification> itr = note.iterator();
        while (itr.hasNext()) {
            Notification notify = itr.next();
            if(to==notify.getTo()) {
                System.out.println("Message ID : " + notify.getID());
                System.out.println("Message From : " + notify.getFrom());
                System.out.println("Message To : " + notify.getTo());
                System.out.println("Message Type : " + notify.getType());
                System.out.println("Message : " + notify.getMessage());
                System.out.println("Supporting Data : " + notify.getObj());
            }
        }
        Lines.lines();
    }
}
