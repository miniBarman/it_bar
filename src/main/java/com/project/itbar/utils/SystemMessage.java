package com.project.itbar.utils;

public class SystemMessage {
    private String text;
    private Constants.MessageType type;

    public SystemMessage() {
    }

    public SystemMessage(Constants.MessageType type, String text) {
        this.text = text;
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public Constants.MessageType getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(Constants.MessageType type) {
        this.type = type;
    }
}
