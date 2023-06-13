package com.good.loginusingretrofit.models;

public class AdminTopicsSubModalClass {
    String id,s_id,topics,status,completion,date;

    public AdminTopicsSubModalClass(String id, String s_id, String topics, String status, String completion, String date) {
        this.id = id;
        this.s_id = s_id;
        this.topics = topics;
        this.status = status;
        this.completion = completion;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompletion() {
        return completion;
    }

    public void setCompletion(String completion) {
        this.completion = completion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
