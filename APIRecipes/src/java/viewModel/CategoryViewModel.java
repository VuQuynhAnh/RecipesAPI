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
    private int totalRecipes;

    public CategoryViewModel(int id, String name, String image, int status, Date createDate, int createUser, Date updateDate, int updateUser, String createUserDisplay, String updateUserDisplay, int totalRecipes) {
        super(id, name, image, status, createDate, createUser, updateDate, updateUser);
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
        this.totalRecipes = totalRecipes;
    }

    public int getTotalRecipes() {
        return totalRecipes;
    }

    public void setTotalRecipes(int totalRecipes) {
        this.totalRecipes = totalRecipes;
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
