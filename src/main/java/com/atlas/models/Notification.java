package com.atlas.models;

import java.io.Serializable;

public interface Notification extends Serializable {
    void setID(int ID);

    void setFrom(String From);

    void setTo(String To);

    void setMessage(String Message);

    void setSupportingObject(Object SupportingParameters);
}
