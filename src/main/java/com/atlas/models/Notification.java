package com.atlas.models;

public interface Notification {
    int getID();

    String getFrom();

    String getTo();

    String getType();

    String getMessage();

    Object getObj();
}
