package com.atlas.mainentry;

import com.atlas.controller.NotificationStore;
import com.atlas.controller.Routes;
import com.atlas.controller.UserHandler;
import com.atlas.models.Route;
import com.atlas.utils.ScannerUtil;

import java.util.LinkedList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        /*Routes routes = Routes.getInstance();
        LinkedList<String> list = new LinkedList<String>();
        list.add("Beach Station");
        list.add("Marina");
        list.add("Thiruvanmiyur");
        routes.addRoutes("Royapuram", "Perungudi", list, "12:35PM", "3hrs");
        MainClass2.addData();
        routes.addRoutes("Royapuram", "Perungudi", list, "12:35PM", "3hrs");
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
        notifications.ListNotification();*/
        char session='y';
        while(session=='y') {
            System.out.println("Login Options");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Visitor");
            System.out.println("Press any key to close!");
            ScannerUtil input = ScannerUtil.getInstance();
            int choice = input.readInt();
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    session='n';
                    break;
            }
        }
    }
}
