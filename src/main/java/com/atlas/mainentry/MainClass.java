package com.atlas.mainentry;

import com.atlas.controller.NotificationStore;
import com.atlas.controller.Routes;
import com.atlas.controller.UserHandler;
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
        UserHandler user = UserHandler.getInstance();
        user.addUser(null, "sankarth", "Karthikeyan Sankar", "908632273", 1);
        notifications.createNotifications(0, "Apply Bus Pass", user.getUser("sankarth"));
        notifications.createNotifications(2, "New Route request", "Tharamani-Velacherry");
        notifications.createNotifications(1, "Cancel Bus Pass", null);
        notifications.createNotifications(3, "Update route request", r);
        user.displayUsers();
        notifications.ListNotification();
    }
}
