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
public class Recipes implements Serializable {

    private int id;
    private int categoryId;
    private int authorId;
    private String name;
    private String origin;
    private int serves;
    private float calories;
    private float fat;
    private float protein;
    private float carbo;
    private String image;
    private int totalViews;
    private String cookTime;
    private int status;
    private Date createDate;
    private int createUser;
    private Date updateDate;
    private int updateUser;

    public float getCalories() {
        return calories;
    }

    public Recipes(int id, int categoryId, int authorId, String name, String origin, int serves, float calories, float fat, float protein, float carbo, String image, int totalViews, String cookTime, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.id = id;
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.serves = serves;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbo = carbo;
        this.image = image;
        this.totalViews = totalViews;
        this.cookTime = cookTime;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
    }

    public Recipes(int categoryId, int authorId, String name, String origin, int serves, float calories, float fat, float protein, float carbo, String image, int totalViews, String cookTime, int status, Date createDate, int createUser, Date updateDate, int updateUser) {
        this.categoryId = categoryId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.serves = serves;
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbo = carbo;
        this.image = image;
        this.totalViews = totalViews;
        this.cookTime = cookTime;
        this.status = status;
        this.createDate = createDate;
        this.createUser = createUser;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
    }

    public Recipes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getFat() {
        return fat;
    }

    public void setFat(float fat) {
        this.fat = fat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getCarbo() {
        return carbo;
    }

    public void setCarbo(float carbo) {
        this.carbo = carbo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }
}
