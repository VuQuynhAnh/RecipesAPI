/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import entity.Ingredient;
import entity.Steps;
import java.util.List;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
public class RecipeOutputData {

    private RecipesViewModel recipesViewModel;
    private List<Steps> listSteps;
    private List<Ingredient> listIngredients;

    public RecipeOutputData() {
    }

    public RecipesViewModel getRecipesViewModel() {
        return recipesViewModel;
    }

    public void setRecipesViewModel(RecipesViewModel recipesViewModel) {
        this.recipesViewModel = recipesViewModel;
    }

    public List<Steps> getListSteps() {
        return listSteps;
    }

    public void setListSteps(List<Steps> listSteps) {
        this.listSteps = listSteps;
    }

    public List<Ingredient> getListIngredients() {
        return listIngredients;
    }

    public void setListIngredients(List<Ingredient> listIngredients) {
        this.listIngredients = listIngredients;
    }

    public RecipeOutputData(RecipesViewModel recipesViewModel, List<Steps> listSteps, List<Ingredient> listIngredients) {
        this.recipesViewModel = recipesViewModel;
        this.listSteps = listSteps;
        this.listIngredients = listIngredients;
    }

}
