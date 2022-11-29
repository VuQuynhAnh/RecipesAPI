/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.io.Serializable;
import java.util.List;

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
    private List<String> listTokenDevice;

    public NotificationViewModel(String notificationType, String description, int status, String createDate, int userId, List<String> listTokens) {
        this.notificationType = notificationType;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
        this.userId = userId;
        this.listTokenDevice = listTokens;
        this.id = 0;
        this.createUserDisplay = "";
        this.userDisplay = "";
    }

    public List<String> getListTokenDevice() {
        return listTokenDevice;
    }

    public void setListTokenDevice(List<String> listTokenDevice) {
        this.listTokenDevice = listTokenDevice;
    }

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
