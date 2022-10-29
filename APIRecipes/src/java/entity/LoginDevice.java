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
public class LoginDevice implements Serializable {

    private int id;
    private String deviceName;
    private int userId;
    private int status;
    private Date lastLoginDate;
    private Date lastLogoutDate;

    public LoginDevice(int id, String deviceName, int userId, int status, Date lastLoginDate, Date lastLogoutDate) {
        this.id = id;
        this.deviceName = deviceName;
        this.userId = userId;
        this.status = status;
        this.lastLoginDate = lastLoginDate;
        this.lastLogoutDate = lastLogoutDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoginDevice() {
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLogoutDate() {
        return lastLogoutDate;
    }

    public void setLastLogoutDate(Date lastLogoutDate) {
        this.lastLogoutDate = lastLogoutDate;
    }
}
