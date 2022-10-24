/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.Category;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class CategoryViewModel extends Category implements Serializable {

    private String createUserDisplay;
    private String updateUserDisplay;

    public CategoryViewModel(int id, String name, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay) {
        super(id, name, status, createDate, createUser, updateDate, updateUser);
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
    }

    public CategoryViewModel() {
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
