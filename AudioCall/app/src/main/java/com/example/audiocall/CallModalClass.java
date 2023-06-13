package com.example.audiocall;

public class CallModalClass
{
    String sender,reciver,senderImage,senderName;

    public CallModalClass() {
    }

    public CallModalClass(String sender, String reciver, String senderImage, String senderName) {
        this.sender = sender;
        this.reciver = reciver;
        this.senderImage = senderImage;
        this.senderName = senderName;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getSenderImage() {
        return senderImage;
    }

    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
