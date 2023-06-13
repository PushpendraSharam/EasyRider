package com.good.loginusingretrofit.models;

public class TeacherDetailModalClass {
    String message;
    TeacherModalClass data[];

    public TeacherDetailModalClass(String message, TeacherModalClass[] data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TeacherModalClass[] getData() {
        return data;
    }

    public void setData(TeacherModalClass[] data) {
        this.data = data;
    }
}
