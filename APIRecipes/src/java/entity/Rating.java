/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Rating implements Serializable {

    private int id;
    private int recipeId;
    private int userId;
    private int rating;
    private Date createDate;

    public Rating(int id, int recipeId, int userId, int rating, Date createDate) {
        this.id = id;
        this.recipeId = recipeId;
        this.userId = userId;
        this.rating = rating;
        this.createDate = createDate;
    }

    public Rating(int recipeId, int userId, int rating, Date createDate) {
        this.recipeId = recipeId;
        this.userId = userId;
        this.rating = rating;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Rating() {
    }
}
