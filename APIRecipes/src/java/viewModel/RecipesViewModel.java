/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.Recipes;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class RecipesViewModel extends Recipes implements Serializable {

    private String categoryDisplay;
    private String author;
    private String createUserDisplay;
    private String updateUserDisplay;

    public RecipesViewModel(int id, int categoryId, int userId, String name, String origin, int serves, String image, int totalViews, String cookTime, int status, Date createDate, int createUser, Date updateDate, int updateUser,String categoryDisplay, String author, String createUserDisplay, String updateUserDisplay) {
        super(id, categoryId, userId, name, origin, serves, image, totalViews, cookTime, status, createDate, createUser, updateDate, updateUser);
        this.categoryDisplay = categoryDisplay;
        this.author = author;
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
    }

    public RecipesViewModel() {
    }

    public String getCategoryDisplay() {
        return categoryDisplay;
    }

    public void setCategoryDisplay(String categoryDisplay) {
        this.categoryDisplay = categoryDisplay;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
