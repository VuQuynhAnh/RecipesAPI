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
public class NotificationType implements Serializable {

    private int id;
    private String name;
    private String description;
    private int status;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public NotificationType(int id, String name, String description, int status, Date createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public NotificationType(String name, String description, int status, Date createDate) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.createDate = createDate;
    }

    public NotificationType() {
    }
}
