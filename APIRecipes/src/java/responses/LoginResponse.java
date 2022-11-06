/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.io.Serializable;
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

    public LoginResponse(String message, int statusCode, UsersViewModel user) {
        super(user.getId(), user.getUserName(), user.getDisplayName(), user.getSex(), user.getAddress(), user.getPhoneNumber(), user.getPassword(), user.getEmail(), user.getJob(), user.getRole(), user.getAvatar(), user.getDescription(), user.getStatus(), user.getCreateDate(), user.getCreateUser(), user.getUpdateDate(), user.getUpdateUser(), user.getCreateUserDisplay(), user.getUpdateUserDisplay(), user.getTotalRecipe(), user.getTotalViews(), user.getTotalFollowOtherUser(), user.getTotalFollowedByOthersUser());
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
