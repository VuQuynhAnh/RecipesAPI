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
    private int totalRecipe;
    private int totalFollowOtherUser;
    private int totalFollowedByOthersUser;

    public int getTotalRecipe() {
        return totalRecipe;
    }

    public void setTotalRecipe(int totalRecipe) {
        this.totalRecipe = totalRecipe;
    }

    public int getTotalFollowOtherUser() {
        return totalFollowOtherUser;
    }

    public void setTotalFollowOtherUser(int totalFollowOtherUser) {
        this.totalFollowOtherUser = totalFollowOtherUser;
    }

    public int getTotalFollowedByOthersUser() {
        return totalFollowedByOthersUser;
    }

    public void setTotalFollowedByOthersUser(int totalFollowedByOthersUser) {
        this.totalFollowedByOthersUser = totalFollowedByOthersUser;
    }

    public UsersViewModel() {
    }

    public UsersViewModel(int id, String userName, String displayName, int sex, String address, String phoneNumber, String password, String email, String job, int role, String avatar, String description, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay, int totalRecipe, int totalFollowOtherUser, int totalFollowedByOthersUser) {
        super(id, userName, displayName, sex, address, phoneNumber, password, email, job, role, avatar, description, status, createDate, createUser, updateDate, updateUser);
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
        this.totalRecipe = totalRecipe;
        this.totalFollowOtherUser = totalFollowOtherUser;
        this.totalFollowedByOthersUser = totalFollowedByOthersUser;
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
