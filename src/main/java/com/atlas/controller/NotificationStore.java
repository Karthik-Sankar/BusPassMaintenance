package com.atlas.controller;

import com.atlas.models.Notification;
import com.atlas.models.Route;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class NotificationStore {
    private static NotificationStore notification;
    LinkedList<Notification> note;
    private NotificationStore(){
        note = new LinkedList<Notification>();
    }

    public static NotificationStore getNotificationInstance() {
        if(notification==null){
            notification = new NotificationStore();
        }
        return notification;
    }

    public void createNotifications(int type,String message, Object o){
        Notification n = NotificationManager.getNotificationType(type,message,o);
        note.add(n);
    }

    public void ListNotification(){
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("---------------------------------Notification-------------------------------");
        System.out.println("---------------------------------------------------------------------------");
        Iterator<Notification> itr = note.iterator();
        while(itr.hasNext()){
            Notification notify = itr.next();
            System.out.println("Message Type : "+notify.getType());
            System.out.println("Message : "+notify.getMessage());
            System.out.println("Supporting Data : "+notify.getObj());
        }
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------");
    }
}
