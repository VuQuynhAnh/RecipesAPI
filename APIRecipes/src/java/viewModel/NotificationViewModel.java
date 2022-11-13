/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.Notifications;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class NotificationViewModel implements Serializable {

    private int id;
    private String notificationType;
    private String description;
    private int status;
    private String createDate;
    private String createUserDisplay;
    private String userDisplay;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public NotificationViewModel() {
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public String getUserDisplay() {
        return userDisplay;
    }

    public void setUserDisplay(String userDisplay) {
        this.userDisplay = userDisplay;
    }
}
