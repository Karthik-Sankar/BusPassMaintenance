package com.atlas.utils;

import java.util.Scanner;

public class ScannerUtil {
    private static ScannerUtil scanner;
    static Scanner scan;

    private ScannerUtil() {
        scan = new Scanner(System.in);
    }

    public static ScannerUtil getInstance() {
        if (scanner == null) {
            scanner = new ScannerUtil();
        }
        return scanner;
    }

    public int readInt() {
        int val = -1;
        try {
            val = Integer.parseInt(scan.nextLine());
        }
        catch (NumberFormatException e){
            System.out.println(ColourMe.ANSI_RED+"Please enter a number! Try again : "+ColourMe.ANSI_RESET);
            readInt();
        }
        return val;
    }

    public String readLine() {
        return scan.nextLine();
    }


    public float readFloat() {
        return Float.parseFloat(scan.nextLine());
    }

    public double readDouble() {
        return Double.parseDouble(scan.nextLine());
    }
}

