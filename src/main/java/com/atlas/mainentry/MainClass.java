package com.atlas.mainentry;

import com.atlas.controller.Routes;

import java.util.LinkedList;

public class MainClass {
    public static void main(String[] args) {
        Routes routes = Routes.getInstance();
        LinkedList<String> list = new LinkedList<String>();
        list.add("Beach Station");
        list.add("Marina");
        list.add("Thiruvanmiyur");
        routes.addRoutes("Royapuram", "Perungudi", list);
        MainClass2.addData();
        routes.addRoutes("Royapuram", "Perungudi", list);
        routes.displayRoute();
    }
}
