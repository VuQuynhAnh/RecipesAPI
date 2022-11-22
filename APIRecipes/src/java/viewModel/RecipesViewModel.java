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
    private String authorAvatar;
    private boolean isSaveRecipe;

    public RecipesViewModel() {
    }

    public boolean isIsSaveRecipe() {
        return isSaveRecipe;
    }

    public void setIsSaveRecipe(boolean isSaveRecipe) {
        this.isSaveRecipe = isSaveRecipe;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
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
