/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.Users;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class UsersViewModel extends Users implements Serializable {

    private String createUserDisplay;
    private String updateUserDisplay;

    public UsersViewModel() {
    }

    public UsersViewModel(int id, String userName, String displayName, int sex, String address, String phoneNumber, String password, String email, String job, int role, String avatar, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay) {
        super(id, userName, displayName, sex, address, phoneNumber, password, email, job, role, avatar, description, status, createDate, createUser, updateDate, updateUser);
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
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
