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
public class Ingredient implements Serializable {

    private int id;
    private int recipeId;
    private String name;
    private String unitOfMeasurement;
    private int status;

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient() {
    }

    public Ingredient(int id, int recipeId, String name, String unitOfMeasurement, int status) {
        this.id = id;
        this.recipeId = recipeId;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.status = status;
    }

}
