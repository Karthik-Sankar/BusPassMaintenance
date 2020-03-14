package com.atlas.controller;

public class Admin {
    boolean isAuthenticated=;
    private static Admin admin;
    private admin(){}

    private static Admin getInstance()
    {
        if(adminobj==null)
        {
            adminobj = new admin();
        }
        return adminobj;
    }


    public String routeAddOrChangeRequest(String userId, String source)
    {
        int routeNum;
        Routes route = Routes.getInstance();
        routeNum = route.addRoutes(source);
        route.assignUserRoute(userId, routeNum);
        return "Your updated Route number is :"+routeNum;
    }
}
