package com.good.loginusingretrofit.models;

public class RegisterModalClass
{
    Boolean success;
    String message;
    DataModalClass data;

    public DataModalClass getData() {
        return data;
    }

    public void setData(DataModalClass data) {
        this.data = data;

    }


    public RegisterModalClass(Boolean success, String message, DataModalClass data) {
        this.success = success;
        this.message = message;
        this.data=data;

    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
