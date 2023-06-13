package com.good.loginusingretrofit.models;

public class DataModalClass
{
   String t_id,t_name,t_location,status,t_email,t_password,t_number,type,image;

    public DataModalClass(String t_id, String t_name, String t_location, String status, String t_email, String t_password, String t_number, String type, String image) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.t_location = t_location;
        this.status = status;
        this.t_email = t_email;
        this.t_password = t_password;
        this.t_number = t_number;
        this.type = type;
        this.image = image;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_location() {
        return t_location;
    }

    public void setT_location(String t_location) {
        this.t_location = t_location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_number() {
        return t_number;
    }

    public void setT_number(String t_number) {
        this.t_number = t_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
