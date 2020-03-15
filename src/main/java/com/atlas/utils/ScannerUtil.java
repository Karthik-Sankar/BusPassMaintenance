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
        return Integer.parseInt(scan.nextLine());
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

