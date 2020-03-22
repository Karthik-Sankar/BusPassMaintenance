package com.atlas.models;

import java.io.Serializable;

public interface NotificationBuilder extends Serializable {
    void buildID();

    void buildFrom();

    void buildTo();

    void buildMessage();

    void buildSupportingObject();

    Notifications getNotification();
}

