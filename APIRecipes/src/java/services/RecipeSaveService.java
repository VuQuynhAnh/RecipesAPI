/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.RecipeSaveDao;
import dao.RecipesDao;
import dao.UsersDao;
import entity.RecipesSave;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/recipeSave")
public class RecipeSaveService {

    RecipeSaveDao recipeSaveDao = null;
    RecipesDao recipesDao = null;
    UsersDao usersDao = null;

    public RecipeSaveService() {
        recipeSaveDao = new RecipeSaveDao();
        recipesDao = new RecipesDao();
        usersDao = new UsersDao();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String SaveRecipesToList(RecipesSave recipeSave) {
        if (recipeSave.getRecipeId() <= 0 || !recipesDao.checkExistRecipe(recipeSave.getRecipeId())) {
            return "Recipe with Id = " + recipeSave.getRecipeId() + " is not exist or deleted!";
        } else if (recipeSave.getUserId() <= 0 || !usersDao.checkExistUser(recipeSave.getUserId())) {
            return "User with Id = " + recipeSave.getUserId() + " is not exist or deleted!";
        }
        RecipesSave oldRecod = recipeSaveDao.getRecipesSave(recipeSave.getRecipeId(), recipeSave.getUserId());
        if (oldRecod.getStatus() <= 0 && oldRecod.getRecipeId() <= 0 && oldRecod.getUserId() <= 0) {
            if (recipeSaveDao.insertData(recipeSave.getRecipeId(), recipeSave.getUserId())) {
                return "Success!";
            }
        } else if (recipeSaveDao.reSaveData(recipeSave.getRecipeId(), recipeSave.getUserId())) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("recipeId") int recipeId, @QueryParam("userId") int userId) {
        if (!recipeSaveDao.checkExistRecipeSave(recipeId, userId)) {
            return "This item is not exist or delete!";
        } else if (recipeSaveDao.deleteData(recipeId, userId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
