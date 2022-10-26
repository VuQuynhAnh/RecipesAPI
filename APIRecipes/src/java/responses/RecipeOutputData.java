/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import entity.Steps;
import java.util.List;
import viewModel.FoodIngredientViewModel;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
public class RecipeOutputData {

    private RecipesViewModel recipesViewModel;
    private List<Steps> listSteps;
    private List<FoodIngredientViewModel> listFoodInfgredients;

    public RecipeOutputData() {
    }

    public RecipeOutputData(RecipesViewModel recipesViewModel, List<Steps> listSteps, List<FoodIngredientViewModel> listFoodInfgredients) {
        this.recipesViewModel = recipesViewModel;
        this.listSteps = listSteps;
        this.listFoodInfgredients = listFoodInfgredients;
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

    public List<FoodIngredientViewModel> getListFoodInfgredients() {
        return listFoodInfgredients;
    }

    public void setListFoodInfgredients(List<FoodIngredientViewModel> listFoodInfgredients) {
        this.listFoodInfgredients = listFoodInfgredients;
    }

}
