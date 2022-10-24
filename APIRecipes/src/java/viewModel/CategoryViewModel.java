/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class CategoryViewModel implements Serializable {

    private int id;
    private String name;
    private int status;
    private Date createDate;
    private int createUser;
    private Date updateDate;
    private int updateUser;
    private String createUserDisplay;
    private String updateUserDisplay;

    public CategoryViewModel(int id, String name, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
    }

    public CategoryViewModel() {
    }

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

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public String getUpdateUserDisplay() {
        return updateUserDisplay;
    }

    public void setUpdateUserDisplay(String updateUserDisplay) {
        this.updateUserDisplay = updateUserDisplay;
    }
}
