package com.atlas.controller;

import com.atlas.models.BusPass;
import com.atlas.models.User;

import java.util.HashMap;
import java.util.Set;

public class UserHandler {

    private static UserHandler users;
    HashMap<String,User> user = new HashMap<String,User>();
    private UserHandler(){}
    public static UserHandler getInstance(){
        if(users==null){
            users = new UserHandler();
        }
        return users;
    }

    public void addUser(BusPass bid, String userId, String userName, String phoneNumber, int routeNum){
        User addUser=new User(bid,userId, userName, phoneNumber,routeNum);
        user.put(userId,addUser);
    }

    public User getUser(String userId){
        return user.get(userId);
    }
    public void displayUsers() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("------------------------------Users Available------------------------------");
        Set<String> u = user.keySet();
        for (String u1 : u) {
            User element = user.get(u1);
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("Buspass Info : " + element.getBusPass());
            System.out.println("ID : " + element.getUserId());
            System.out.println("UserName : " + element.getUserName());
            System.out.println("Phone Number :" + element.getPhoneNumber());
            System.out.println("Route Number :" + element.getRouteNum());
            System.out.println("---------------------------------------------------------------------------");

        }
    }

//    public void updateUserRoute(String uId,int rNum){
//        for (User element : user) {
//            if(element.getUserId().equals(uId)){
//                element.setRouteNum(rNum);
//            }
//        }
//    }


}
