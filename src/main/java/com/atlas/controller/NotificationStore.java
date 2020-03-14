package com.atlas.controller;

import com.atlas.models.Notification;

import java.util.Iterator;
import java.util.LinkedList;

public class NotificationStore {
    private static NotificationStore notification;
    LinkedList<Notification> note;

    private NotificationStore() {
        note = new LinkedList<Notification>();
    }

    public static NotificationStore getNotificationInstance() {
        if (notification == null) {
            notification = new NotificationStore();
        }
        return notification;
    }

    public void createNotifications(int type, String message, Object o, String from, String to) {
        Notification n = NotificationManager.getNotificationType(type, message, o, from, to);
        note.add(n);
    }

    public void ListNotification(String to) {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("---------------------------------Notifications------------------------------");
        System.out.println("---------------------------------------------------------------------------");
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
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------");
    }
}
