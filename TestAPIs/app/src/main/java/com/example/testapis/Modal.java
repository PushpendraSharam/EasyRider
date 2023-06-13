package com.example.testapis;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Modal {

    String success;

    public Modal(String success) {
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
