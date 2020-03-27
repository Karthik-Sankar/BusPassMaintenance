package com.atlas.mainentry;

import com.atlas.controller.Admin;
import com.atlas.controller.UserHandler;
import com.atlas.controller.VisitorHandler;
import com.atlas.utils.ColourMe;
import com.atlas.utils.ScannerUtil;

public class MainClass {
    public static void main(String[] args) {
        char session = 'y';
        while (session == 'y') {
            System.out.println(ColourMe.ANSI_BRIGHT_YELLOW);
            System.out.println("Login Options");
            System.out.println(ColourMe.ANSI_RESET);
            System.out.println(ColourMe.ANSI_BLUE);
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Visitor");
            System.out.print("Press 0 key to close!");
            System.out.println(ColourMe.ANSI_RESET);
            ScannerUtil input = ScannerUtil.getInstance();
            int choice = input.readInt();
            switch (choice) {
                case 1:
                    new Admin().AdminEntry();
                    break;
                case 2:
                    UserHandler.getInstance().UserEntry();
                    break;
                case 3:
                    VisitorHandler.getInstance().VisitorEntry();
                    break;
                case 0:
                    session = 'n';
                    break;
                default:
                    System.out.println(ColourMe.ANSI_BRIGHT_RED + "Invalid option!" + ColourMe.ANSI_RESET);
            }
        }
    }
}
