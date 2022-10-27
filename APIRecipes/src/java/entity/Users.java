/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Users implements Serializable {

    private int id;
    private String userName;
    private String displayName;
    private int sex;
    private String address;
    private String phoneNumber;
    private String password;
    private String email;
    private String job;
    private int role;
    private String avatar;
    private String description;
    private int status;
    private Date createDate;
    private int createUser;
    private Date updateDate;
    private int updateUser;

    public Users(String userName, String displayName, int sex, String address, String phoneNumber, String password, String email, String job, int role, String avatar, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.userName = userName;
        this.displayName = displayName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.job = job;
        this.role = role;
        this.avatar = avatar;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
    }

    public Users(int id, String userName, String displayName, int sex, String address, String phoneNumber, String password, String email, String job, int role, String avatar, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.id = id;
        this.userName = userName;
        this.displayName = displayName;
        this.sex = sex;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.job = job;
        this.role = role;
        this.avatar = avatar;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public Users() {
    }

}
