package com.atlas.persistance;

import java.io.*;

public class ObjectStore {
    public void saveObject(Object o, String fileName) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Creating Database Files: " + file.getAbsolutePath());
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(o);
                objectOutputStream.close();
                fileOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nAlert, Data storage error!");
        }
    }

    public Object retreiveObject(String fileName) {
        Object o = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Creating Database Files: " + file.getAbsolutePath());
            }
            else{
                FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                o = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nAlert, Data storage error!");
            return o;
        }
    }
}
