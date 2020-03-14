package com.atlas.mainentry;

import com.atlas.controller.Routes;

import java.util.LinkedList;

public class MainClass2 {
    public static void addData(){
        Routes routes = Routes.getInstance();
        LinkedList<String> list = new LinkedList<String>();
        list.add("Guindy");
        list.add("Velachery");
        list.add("Tharamani");
        routes.addRoutes("Vadapalani", "Perungudi", list, "06:35PM", "6hrs");
    }
}
