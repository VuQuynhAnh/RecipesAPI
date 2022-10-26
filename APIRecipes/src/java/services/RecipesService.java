/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CategoryDao;
import dao.FoodIngredientDao;
import dao.IngredientDao;
import dao.RecipesDao;
import dao.StepsDao;
import dao.UsersDao;
import entity.FoodIngredient;
import entity.Recipes;
import entity.Steps;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import requests.RecipeFilterRequest;
import requests.RecipeInputData;
import responses.RecipeOutputData;
import viewModel.FoodIngredientViewModel;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/recipes")
public class RecipesService {

    RecipesDao recipesDao = null;
    UsersDao userDao = null;
    CategoryDao categoryDao = null;
    StepsDao stepDao = null;
    IngredientDao ingredientDao = null;
    FoodIngredientDao foodIngredientDao = null;

    public RecipesService() {
        recipesDao = new RecipesDao();
        userDao = new UsersDao();
        categoryDao = new CategoryDao();
        stepDao = new StepsDao();
        ingredientDao = new IngredientDao();
        foodIngredientDao = new FoodIngredientDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecipesViewModel> getRecipes() {
        return recipesDao.getData();
    }

    @POST
    @Path("filterData")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<RecipesViewModel> getRecipes(RecipeFilterRequest request) {
        return recipesDao.getData(request);
    }

    @GET
    @Path("detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipesViewModel getRecipesById(@PathParam("id") int id) {
        return recipesDao.getDataById(id);
    }

    @GET
    @Path("getRecipe/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeOutputData getRecipesOutput(@PathParam("id") int id) {
        RecipesViewModel recipe = recipesDao.getDataById(id);
        List<Steps> listSteps = stepDao.getData(id);
        List<FoodIngredientViewModel> listFoodIngredients = foodIngredientDao.getData(id);

        return new RecipeOutputData(
                recipe,
                listSteps,
                listFoodIngredients
        );
    }

    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(RecipeInputData input) {
        // Validate Recipe input
        if (input.getRecipe().getName().trim().length() == 0) {
            return "Recipe name is requied!";
        } else if (input.getRecipe().getServes() < 0) {
            return "Serves is must more or equal 0!";
        } else if (!categoryDao.checkExistCategory(input.getRecipe().getCategoryId())) {
            return "Category with id = " + input.getRecipe().getCategoryId() + " is not exist or deleted!";
        } else if (!userDao.checkExistUser(input.getRecipe().getAuthorId())) {
            return "Author with id = " + input.getRecipe().getAuthorId() + " is not exist or deleted!";
        }
        // Validate Steps
        int stepNumber = 1;
        for (Steps step : input.getListSteps()) {
            if (step.getDescription().trim().length() == 0) {
                return "Step description at " + stepNumber + " is requied!";
            }
            step.setStepNumber(stepNumber);
            stepNumber += 1;
        }

        // Validate FoodIngredient
        int index = 1;
        for (FoodIngredient foodIngredient : input.getListFoodInfgredients()) {
            if (foodIngredient.getUnitOfMeasurement().length() == 0) {
                return "FoodIngredient UnitOfMeasurement at index = " + index + " is requied!";
            } else if (foodIngredient.getUnitOfMeasurement().length() > 250) {
                return "FoodIngredient UnitOfMeasurement at index = " + index + " is too long, maxlength is 250 characters!";
            } else if (!ingredientDao.checkExistIngredient(foodIngredient.getIngredientId())) {
                return "FoodIngredient IngredientId at index = " + index + " is not exist or deleted!";
            }
            index += 1;
        }

        // Insert Recipe and return recipeId
        int recipeId = recipesDao.insertRecipe(input.getRecipe());
        if (recipeId > 0) {

            // Insert Steps
            for (Steps step : input.getListSteps()) {
                step.setRecipeId(recipeId);
                step.setStatus(0);
                stepDao.insertData(step);
            }

            // Insert FoodIngredient
            for (FoodIngredient foodIngredient : input.getListFoodInfgredients()) {
                foodIngredient.setRecipeId(recipeId);
                foodIngredient.setStatus(0);
                foodIngredientDao.insertData(foodIngredient);
            }
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(RecipeInputData input) {
        // Validate recipe
        if (input.getRecipe().getName().trim().length() == 0) {
            return "Recipe name is requied!";
        } else if (input.getRecipe().getServes() < 0) {
            return "Serves is must more or equal 0!";
        } else if (input.getRecipe().getStatus() < 0) {
            return "Status is must more or equal 0!";
        } else if (!categoryDao.checkExistCategory(input.getRecipe().getCategoryId())) {
            return "Category with id = " + input.getRecipe().getCategoryId() + " is not exist or deleted!";
        } else if (!userDao.checkExistUser(input.getRecipe().getAuthorId())) {
            return "Author with id = " + input.getRecipe().getAuthorId() + " is not exist or deleted!";
        } else if (recipesDao.getDataById(input.getRecipe().getId()).getId() <= 0) {
            return "Recipes width id = " + input.getRecipe().getId() + " is not exist!";
        }
        // Validate Steps
        int index = 1;
        for (Steps step : input.getListSteps()) {
            if (step.getDescription().trim().length() == 0) {
                return "Step description at " + index + " is requied!";
            }
            step.setStepNumber(index);
            index += 1;
        }

        // Validate FoodIngredient
        index = 1;
        for (FoodIngredient foodIngredient : input.getListFoodInfgredients()) {
            if (foodIngredient.getUnitOfMeasurement().length() == 0) {
                return "FoodIngredient UnitOfMeasurement at index = " + index + " is requied!";
            } else if (foodIngredient.getUnitOfMeasurement().length() > 250) {
                return "FoodIngredient UnitOfMeasurement at index = " + index + " is too long, maxlength is 250 characters!";
            } else if (!ingredientDao.checkExistIngredient(foodIngredient.getIngredientId())) {
                return "FoodIngredient IngredientId at index = " + index + " is not exist or deleted!";
            }
            index += 1;
        }
        if (recipesDao.updateData(input.getRecipe())) {
            // Update Steps
            // Check Exist Step item in DB
            List<Steps> listStepInDB = stepDao.getData(input.getRecipe().getId());
            for (Steps stepDB : listStepInDB) {
                int countExistInList = 0;

                // For loop and check if item in DB exist in list input then continue
                for (Steps stepInput : input.getListSteps()) {
                    if (stepInput.getId() == stepDB.getId()) {
                        countExistInList += 1;
                        break;
                    }
                }
                // if item in DB not exist in list input, delete this item in DB
                if (countExistInList == 0) {
                    stepDao.deleteData(stepDB.getId());
                }
            }
            int stepNumber = 1;
            for (Steps step : input.getListSteps()) {
                step.setRecipeId(input.getRecipe().getId());
                step.setStatus(0);
                step.setStepNumber(stepNumber);

                // if stepId <= 0 => insert new step to DB
                if (step.getId() <= 0) {
                    stepDao.insertData(step);
                } // if stepId > 0 => update step
                else {
                    stepDao.updateData(step);
                }
                stepNumber += 1;
            }

            // Update FoodIngredient
            // Check Exist Step item in DB
            List<FoodIngredientViewModel> listFoodIngerdient = foodIngredientDao.getData(input.getRecipe().getId());
            for (FoodIngredientViewModel itemDB : listFoodIngerdient) {
                int countExistInList = 0;

                // For loop and check if item in DB exist in list input then continue
                for (FoodIngredient itemInput : input.getListFoodInfgredients()) {
                    if (itemInput.getIngredientId() == itemDB.getIngredientId()) {
                        countExistInList += 1;
                        break;
                    }
                }
                // if item in DB not exist in list input, delete this item in DB
                if (countExistInList == 0) {
                    foodIngredientDao.deleteFoodIngredient(input.getRecipe().getId(), itemDB.getIngredientId());
                }
            }
            for (FoodIngredient foodIngredient : input.getListFoodInfgredients()) {
                foodIngredient.setStatus(0);
                foodIngredient.setRecipeId(input.getRecipe().getId());
                // If exist in DB => update item
                if (foodIngredientDao.checkExistFoodIngredient(input.getRecipe().getId(), foodIngredient.getIngredientId())) {
                    foodIngredientDao.updateData(foodIngredient);
                } // If not exist in DB => insert new to DB
                else {
                    foodIngredientDao.insertData(foodIngredient);
                }
            }
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete/{id}/{deleteId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id, @PathParam("deleteId") int deleteId) {
        if (!recipesDao.checkExistRecipe(id)) {
            return "Recipes width id = " + id + " is not exist or deleted!";
        } else if (recipesDao.deleteData(id, deleteId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
