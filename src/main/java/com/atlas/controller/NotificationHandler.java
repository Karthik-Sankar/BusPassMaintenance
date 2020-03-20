package com.atlas.controller;

import com.atlas.models.Bus;
import com.atlas.models.Notification;
import com.atlas.persistance.ObjectRetreiver;
import com.atlas.utils.Lines;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class NotificationHandler {
    private static NotificationHandler notification;
    LinkedList<Notification> note;

    private NotificationHandler() {
        note = new LinkedList<Notification>();
        initalize();
    }

    public static NotificationHandler getNotificationInstance() {
        if (notification == null) {
            notification = new NotificationHandler();
        }
        return notification;
    }

    public void initalize(){
        ObjectRetreiver retreiver = new ObjectRetreiver();
        Object o = retreiver.getNotificationObj();
        if(o!=null) {
            LinkedList<Notification> temp = (LinkedList<Notification>) o;
            Iterator<Notification> itr = temp.iterator();
            while(itr.hasNext()) {
                Notification n = itr.next();
                note.add(n);
            }
        }
    }

    public void createNotifications(int type, String message, Object o, String from, String to) {
        Notification n = NotificationManager.getNotificationType(type, message, o, from, to);
        note.add(n);
    }

    public void ListNotification(String to) {
        if(!note.isEmpty()) {
            System.out.println();
            Lines.lines();
            System.out.println("Notifications for " + to);
            Lines.lines();
            Iterator<Notification> itr = note.iterator();
            while (itr.hasNext()) {
                Notification notify = itr.next();
                if (to.equals(notify.getTo())) {
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
        else{
            System.out.println("There is No Notification to display");
        }
    }
    public void clearNotification(int id){
        if(note.contains(id)){
            note.remove(id);
            System.out.println("Notification Removed for the ID - "+id);
        }
        else{
            System.out.println("Incorrect notification ID");
        }
    }
    public void clearAllNotification(){
        note.clear();
        System.out.println("Cleared all the Notifications");
    }

    public Object getObject(){
        return note;
    }
}
