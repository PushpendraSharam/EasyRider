package com.good.loginusingretrofit.models;

public class ProfileModalClass
{
    String success;
    String message;
    ProfileDetailModalClass data;

    public ProfileModalClass(String success, String message, ProfileDetailModalClass data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProfileDetailModalClass getData() {
        return data;
    }

    public void setData(ProfileDetailModalClass data) {
        this.data = data;
    }
}
