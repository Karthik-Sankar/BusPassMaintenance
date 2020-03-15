package com.atlas.models;

import java.io.Serializable;

public interface Notification extends Serializable {
    int getID();

    String getFrom();

    String getTo();

    int getType();

    String getMessage();

    Object getObj();
}
