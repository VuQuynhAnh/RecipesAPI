/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import entity.FoodIngredient;
import entity.Recipes;
import entity.Steps;
import java.util.List;

/**
 *
 * @author DELL
 */
public class RecipeInputData {

    private Recipes recipe;
    private List<Steps> listSteps;
    private List<FoodIngredient> listFoodInfgredients;

    public RecipeInputData(Recipes recipe, List<Steps> listSteps, List<FoodIngredient> listFoodInfgredients) {
        this.recipe = recipe;
        this.listSteps = listSteps;
        this.listFoodInfgredients = listFoodInfgredients;
    }

    public RecipeInputData() {
    }

    public Recipes getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipes recipe) {
        this.recipe = recipe;
    }

    public List<Steps> getListSteps() {
        return listSteps;
    }

    public void setListSteps(List<Steps> listSteps) {
        this.listSteps = listSteps;
    }

    public List<FoodIngredient> getListFoodInfgredients() {
        return listFoodInfgredients;
    }

    public void setListFoodInfgredients(List<FoodIngredient> listFoodInfgredients) {
        this.listFoodInfgredients = listFoodInfgredients;
    }

}
