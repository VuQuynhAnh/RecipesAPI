/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CategoryDao;
import dao.RecipesDao;
import dao.UsersDao;
import entity.Recipes;
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
import requestModel.RecipeRequest;
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

    public RecipesService() {
        recipesDao = new RecipesDao();
        userDao = new UsersDao();
        categoryDao = new CategoryDao();
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
    public List<RecipesViewModel> getRecipes(RecipeRequest request) {
        return recipesDao.getData(request);
    }

    @GET
    @Path("detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipesViewModel getRecipesById(@PathParam("id") int id) {
        return recipesDao.getDataById(id);
    }

    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Recipes recipe) {
        if (!categoryDao.checkExistCategory(recipe.getCategoryId())) {
            return "Category is not exist!";
        } else if (!userDao.checkExistUser(recipe.getAuthorId())) {
            return "Author is not exist!";
        } else if (recipe.getName().trim().length() == 0) {
            return "Recipe name is requied!";
        } else if (recipe.getServes() < 0) {
            return "Serves is must more or equal 0!";
        } else if (recipe.getStatus() < 0) {
            return "Status is must more or equal 0!";
        } else if (recipesDao.insertData(recipe)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Recipes recipe) {
        if (!categoryDao.checkExistCategory(recipe.getCategoryId())) {
            return "Category is not exist!";
        } else if (!userDao.checkExistUser(recipe.getAuthorId())) {
            return "Author is not exist!";
        } else if (recipe.getName().trim().length() == 0) {
            return "Recipe name is requied!";
        } else if (recipe.getServes() < 0) {
            return "Serves is must more or equal 0!";
        } else if (recipe.getStatus() < 0) {
            return "Status is must more or equal 0!";
        } else if (!recipesDao.checkExistRecipe(recipe.getId())) {
            return "Can not find Recipes width id = " + recipe.getId() + "!";
        } else if (recipesDao.updateData(recipe)) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete/{id}/{deleteId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id, @PathParam("deleteId") int deleteId) {
        if (recipesDao.checkExistRecipe(id)) {
            return "Can not find Recipes width id = " + id + "!";
        } else if (recipesDao.deleteData(id, deleteId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
