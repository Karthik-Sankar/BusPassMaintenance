package com.atlas.models;

public interface Notification {
    String getType();

    String getMessage();

    Object getObj();
}
