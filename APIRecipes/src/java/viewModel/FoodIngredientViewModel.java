/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.FoodIngredient;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class FoodIngredientViewModel extends FoodIngredient implements Serializable {

    private String ingredientDisplay;

    public FoodIngredientViewModel(int recipeId, int ingredientId, String unitOfMeasurement, int status, String ingredientDisplay) {
        super(recipeId, ingredientId, unitOfMeasurement, status);
        this.ingredientDisplay = ingredientDisplay;
    }

    public FoodIngredientViewModel() {
    }

    public String getIngredientDisplay() {
        return ingredientDisplay;
    }

    public void setIngredientDisplay(String ingredientDisplay) {
        this.ingredientDisplay = ingredientDisplay;
    }

}
