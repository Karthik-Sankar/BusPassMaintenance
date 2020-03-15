package com.atlas.persistance;

import java.io.*;

public class ObjectStore {
    public void saveObject(Object o, String fileName){
        try{
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            if(file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(o);
                System.out.println("Session data stored!");
                objectOutputStream.close();
                fileOutputStream.close();
            }
            else{
                System.out.println("\nAlert, Data storage error! - File doesn't exist - "+file.getAbsolutePath());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\nAlert, Data storage error!");
        }
    }

    public Object retreiveObject(String fileName){
        Object o=null;
        try{
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            if(file.exists() && file.length()!=0) {
                FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                o = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
            else{
                System.out.println("\nAlert, Data storage error! - File doesn't exist - "+file.getAbsolutePath());
            }
            return o;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\nAlert, Data storage error!");
            return o;
        }
    }
}
