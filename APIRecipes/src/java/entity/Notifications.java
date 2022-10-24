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
public class Notifications implements Serializable {

    private int id;
    private int userId;
    private int notificationId;
    private String description;
    private int status;
    private Date createDate;
    private int createUser;
    private Date updateDate;
    private int updateUser;

    public Notifications(int id, int userId, int notificationId, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.id = id;
        this.userId = userId;
        this.notificationId = notificationId;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
    }

    public Notifications(int userId, int notificationId, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.userId = userId;
        this.notificationId = notificationId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
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

    public Notifications() {
    }
}
