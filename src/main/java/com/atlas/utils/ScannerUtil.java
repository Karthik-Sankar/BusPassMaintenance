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
        return scan.nextInt();
    }

    public String readLine() {
        return scan.next();
    }

    public float readFloat() {
        return scan.nextFloat();
    }

    public double readDouble() {
        return scan.nextDouble();
    }
}

