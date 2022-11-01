/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import entity.Ingredient;
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
    private List<Ingredient> listInfgredients;

    public RecipeInputData(Recipes recipe, List<Steps> listSteps, List<Ingredient> listInfgredients) {
        this.recipe = recipe;
        this.listSteps = listSteps;
        this.listInfgredients = listInfgredients;
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

    public List<Ingredient> getListInfgredients() {
        return listInfgredients;
    }

    public void setListInfgredients(List<Ingredient> listInfgredients) {
        this.listInfgredients = listInfgredients;
    }

}
