/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import entity.Ingredient;
import entity.Steps;
import java.util.List;

/**
 *
 * @author DELL
 */
public class RecipeInputRequest {

    private RecipeInputItem recipe;
    private List<Steps> listSteps;
    private List<Ingredient> listInfgredients;

    public RecipeInputRequest(RecipeInputItem recipe, List<Steps> listSteps, List<Ingredient> listInfgredients) {
        this.recipe = recipe;
        this.listSteps = listSteps;
        this.listInfgredients = listInfgredients;
    }

    public RecipeInputRequest() {
    }

    public RecipeInputItem getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeInputItem recipe) {
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
