/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class FoodIngredient implements Serializable {

    private int recipeId;
    private int ingredientId;
    private String unitOfMeasurement;
    private int status;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FoodIngredient(int recipeId, int ingredientId, String unitOfMeasurement, int status) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.unitOfMeasurement = unitOfMeasurement;
        this.status = status;
    }

    public FoodIngredient() {
    }
}
