package com.atlas.persistance;

import com.atlas.controller.*;
import com.atlas.utils.IDGenerator;

import java.io.Serializable;

public class ObjectSaver implements Serializable {
    public void saveRoutes() {
        new ObjectStore().saveObject(RouteHandler.getInstance().getObject(), "Route");
    }

    public void saveBus() {
        new ObjectStore().saveObject(BusHandler.getInstance().getObject(), "Bus");
    }

    public void saveBusPass() {
        new ObjectStore().saveObject(BusPassHandler.getInstance().getObject(), "BusPass");
    }

    public void saveNotifications() {
        new ObjectStore().saveObject(NotificationHandler.getNotificationInstance().getObject(), "Notification");
    }

    public void saveUser() {
        new ObjectStore().saveObject(UserHandler.getInstance().getObject(), "User");
    }

    public void saveVisitor() {
        new ObjectStore().saveObject(VisitorHandler.getInstance().getObject(), "Visitor");
    }

    public void saveIDS(){ new ObjectStore().saveObject(IDGenerator.getInstance().getObject(), "IDS"); }

    public void saveAll() {
        saveBus();
        saveBusPass();
        saveNotifications();
        saveRoutes();
        saveUser();
        saveVisitor();
        //saveIDS();
    }


}
