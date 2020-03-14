package com.atlas.mainentry;

import com.atlas.controller.NotificationStore;
import com.atlas.controller.Routes;
import com.atlas.models.Route;

import java.util.LinkedList;

public class MainClass {
    public static void main(String[] args) {
        Routes routes = Routes.getInstance();
        LinkedList<String> list = new LinkedList<String>();
        list.add("Beach Station");
        list.add("Marina");
        list.add("Thiruvanmiyur");
        routes.addRoutes("Royapuram", "Perungudi", list, "12:35PM", "3hrs");
        MainClass2.addData();
        routes.addRoutes("Royapuram", "Perungudi", list,"12:35PM", "3hrs");
        routes.displayRoute();

        NotificationStore notifications = NotificationStore.getNotificationInstance();
        Route r = routes.route.get("Royapuram");
        notifications.createNotifications(0, "Apply Bus Pass", null);
        notifications.createNotifications(2, "New Route request", r);
        notifications.createNotifications(1, "Cancel Bus Pass", null);
        notifications.createNotifications(3, "Update route request", r);
        notifications.ListNotification();
    }
}
