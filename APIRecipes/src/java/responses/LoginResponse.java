/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.io.Serializable;
import java.sql.Date;
import viewModel.UsersViewModel;

/**
 *
 * @author DELL
 */
public class LoginResponse extends UsersViewModel implements Serializable {

    private String message;
    private int statusCode;

    public LoginResponse() {
    }

    public LoginResponse(String message, int statusCode, int id, String userName, String displayName, int sex, String address, String phoneNumber, String password, String email, String job, int role, String avatar, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay) {
        super(id, userName, displayName, sex, address, phoneNumber, password, email, job, role, avatar, description, status, createDate, createUser, updateDate, updateUser, createUserDisplay, updateUserDisplay);
        this.message = message;
        this.statusCode = statusCode;
    }
    
     public LoginResponse(String message, int statusCode, UsersViewModel user) {
        super(user.getId(), user.getUserName(), user.getDisplayName(), user.getSex(), user.getAddress(), user.getPhoneNumber(), user.getPassword(), user.getEmail(), user.getJob(), user.getRole(), user.getAvatar(), user.getDescription(), user.getStatus(), user.getCreateDate(), user.getCreateUser(), user.getUpdateDate(), user.getUpdateUser(), user.getCreateUserDisplay(), user.getUpdateUserDisplay());
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
