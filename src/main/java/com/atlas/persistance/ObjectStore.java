package com.atlas.persistance;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectStore {
    public static void saveObject(Object o, String fileName){
        try{
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            if(file.exists()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(o);
                System.out.println("Session data stored!");
            }
            else{
                System.out.println("\nAlert, Data storage error! - File doesn't exist");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("\nAlert, Data storage error!");
        }
    }
}
