package com.atlas.models;

import java.io.Serializable;

public class Notifications implements Notification, Serializable {

    private int ID;
    private String From;
    private String To;
    private String Message;
    private Object SupportingParameters;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFrom(String From) {
        this.From = From;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public void setSupportingObject(Object SupportingParameters) {
        this.SupportingParameters = SupportingParameters;
    }

    public int getID() {
        return ID;
    }

    public String getFrom() {
        return From;
    }

    public String getTo() {
        return To;
    }

    public String getMessage() {
        return Message;
    }

    public Object getSupportingParameters() {
        return SupportingParameters;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "ID=" + ID +
                ", From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", Message='" + Message + '\'' +
                ", SupportingParameters=" + SupportingParameters +
                '}';
    }
}
