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
    private double avgRating;
    private int totalRating;

    public RecipesViewModel(int id, int categoryId, int authorId, String name, String origin, int serves, float calories, float fat, float protein, float carbo, String image, int totalViews, String cookTime, int status, Date createDate, int createUser, Date updateDate, int updateUser,String categoryDisplay, String author, String createUserDisplay, String updateUserDisplay, double avgRating, int totalRating) {
        super(id, categoryId, authorId, name, origin, serves, calories, fat, protein, carbo, image, totalViews, cookTime, status, createDate, createUser, updateDate, updateUser);
        this.categoryDisplay = categoryDisplay;
        this.author = author;
        this.createUserDisplay = createUserDisplay;
        this.updateUserDisplay = updateUserDisplay;
        this.avgRating = avgRating;
        this.totalRating = totalRating;
    }

    public double getAvgRating() {
        return avgRating;
    }
    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public int getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(int totalRating) {
        this.totalRating = totalRating;
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
