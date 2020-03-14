package com.atlas.controller;

import java.util.LinkedList;

public class Admin {
    boolean isAuthenticated = true;
    private static Admin admin;

    private Admin() {
    }

    private static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }


    /*public String assignUserRoute(String userId, int routeId)
    {
        int routeNum;
        Routes route = Routes.getInstance();
        routeNum = route.route.get();
        //route.assignUserRoute(userId, routeNum);
        return "Your updated Route number is :"+routeNum;
    }*/
}
