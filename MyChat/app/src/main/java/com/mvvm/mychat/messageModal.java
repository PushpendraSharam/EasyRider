package com.mvvm.mychat;

public class messageModal
{
    String messageId,senderId,message,time,imageName;

    public messageModal() {
    }

    public messageModal(String messageId, String senderId, String message, String time, String imageName) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.message = message;
        this.time = time;
        this.imageName = imageName;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
