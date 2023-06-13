package com.good.loginusingretrofit.models;

public class AllTopicsModalClass
{
    String status;
    TopicsModalClass data[];

    public AllTopicsModalClass(String status, TopicsModalClass[] data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TopicsModalClass[] getData() {
        return data;
    }

    public void setData(TopicsModalClass[] data) {
        this.data = data;
    }
}
