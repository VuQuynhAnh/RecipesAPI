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

    public Notifications(int id, int userId, int notificationId, String description, int status, Date createDate, int createUser) {
        this.id = id;
        this.userId = userId;
        this.notificationId = notificationId;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    public Notifications(int userId, int notificationId, String description, int status, Date createDate, int createUser) {
        this.userId = userId;
        this.notificationId = notificationId;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
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

    public Notifications() {
    }
}
