/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.List;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
public class RecipeListResponse {

    private int totalPage;
    private List<RecipesViewModel> recipesViewModels;

    public RecipeListResponse() {
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public RecipeListResponse(int totalPage, List<RecipesViewModel> recipesViewModels) {
        this.totalPage = totalPage;
        this.recipesViewModels = recipesViewModels;
    }

    public List<RecipesViewModel> getRecipesViewModels() {
        return recipesViewModels;
    }

    public void setRecipesViewModels(List<RecipesViewModel> recipesViewModels) {
        this.recipesViewModels = recipesViewModels;
    }
}
