package com.atlas.persistance;

import java.io.Serializable;

public class ObjectRetreiver  implements Serializable {
    public Object getRoutesObj() {
        return new ObjectStore().retreiveObject("Route");
    }

    public Object getBusObj() {
        return new ObjectStore().retreiveObject("Bus");
    }

    public Object getBusPassObj() {
        return new ObjectStore().retreiveObject("BusPass");
    }

    public Object getNotificationObj() {
        return new ObjectStore().retreiveObject("Notification");
    }

    public Object getUserObj() {
        return new ObjectStore().retreiveObject("User");
    }

    public Object getVisitorObj() {
        return new ObjectStore().retreiveObject("Visitor");
    }
}
