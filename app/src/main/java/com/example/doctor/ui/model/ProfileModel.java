package com.example.doctor.ui.model;

import java.util.List;

/**
 * Created by tejas on 11/7/17.
 */

public class ProfileModel {

    /**
     * id : 10
     * password : pbkdf2_sha256$36000$SxRenpFcjxC8$Bb4/uurZh0oH99fXQz7Kr78Rn2BbTfhgslERhm5x3vI=
     * last_login : 2017-07-10T17:35:39.712600Z
     * is_superuser : false
     * username : ted
     * first_name :
     * last_name :
     * email : ted.bhatia@gmail.com
     * is_staff : false
     * is_active : true
     * date_joined : 2017-07-07T11:23:15.063770Z
     * dob : null
     * address : null
     * phone_number : null
     * blood_group : null
     * gender : null
     * profile_pic : null
     * groups : []
     * user_permissions : []
     */

    private int id;
    private String password;
    private String last_login;
    private boolean is_superuser;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private boolean is_staff;
    private boolean is_active;
    private String date_joined;
    private Object dob;
    private Object address;
    private Object phone_number;
    private Object blood_group;
    private Object gender;
    private Object profile_pic;
    private List<?> groups;
    private List<?> user_permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public boolean isIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Object phone_number) {
        this.phone_number = phone_number;
    }

    public Object getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(Object blood_group) {
        this.blood_group = blood_group;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Object profile_pic) {
        this.profile_pic = profile_pic;
    }

    public List<?> getGroups() {
        return groups;
    }

    public void setGroups(List<?> groups) {
        this.groups = groups;
    }

    public List<?> getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(List<?> user_permissions) {
        this.user_permissions = user_permissions;
    }
}
