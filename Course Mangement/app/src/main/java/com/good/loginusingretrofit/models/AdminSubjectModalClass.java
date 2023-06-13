package com.good.loginusingretrofit.models;

public class AdminSubjectModalClass
{
    String status;
    AdminSubjectsSubModalClass data[];

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AdminSubjectsSubModalClass[] getData() {
        return data;
    }

    public void setData(AdminSubjectsSubModalClass[] data) {
        this.data = data;
    }
}
