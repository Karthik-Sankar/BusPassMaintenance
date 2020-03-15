package com.atlas.mainentry;

import com.atlas.controller.*;
import com.atlas.models.Route;
import com.atlas.models.Visitor;
import com.atlas.utils.ScannerUtil;

import java.util.LinkedList;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        char session='y';
        while(session=='y') {
            System.out.println("Login Options");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Visitor");
            System.out.println("Press 0 key to close!");
            ScannerUtil input = ScannerUtil.getInstance();
            int choice = input.readInt();
            switch (choice) {
                case 1:
                    Admin admin = new Admin();
                    admin.AdminEntry();
                    break;
                case 2:
                    UserHandler.getInstance().UserEntry();
                    break;
                case 3:
                    VisitorHandler.getInstance().VisitorEntry();
                    break;
                default:
                    session='n';
                    break;
            }
        }
    }
}
