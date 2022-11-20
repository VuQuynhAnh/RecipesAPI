/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.jersey.multipart.FormDataParam;
import common.FolderNameConstant;
import common.NotificationTypeIdConstant;
import dao.CategoryDao;
import dao.FollowerDao;
import dao.IngredientDao;
import dao.NotificationDao;
import dao.NotificationTypeDao;
import dao.RatingDao;
import dao.RecipeSaveDao;
import dao.RecipesDao;
import dao.StepsDao;
import dao.UploadImageDao;
import dao.UsersDao;
import entity.Ingredient;
import entity.NotificationType;
import entity.Rating;
import entity.Steps;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import requests.RecipeFilterRequest;
import requests.RecipeInputData;
import responses.OutputResponse;
import responses.RecipeDetailResponse;
import responses.RecipeListResponse;
import viewModel.NotificationViewModel;
import viewModel.RecipesViewModel;
import viewModel.UsersViewModel;

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
    RatingDao ratingDao = null;
    UploadImageDao uploadImageDao = null;
    NotificationTypeDao notificationTypeDao = null;
    NotificationDao notificationDao = null;
    RecipeSaveDao recipeSaveDao = null;
    FollowerDao followerDao = null;
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    LocalDateTime dateTimeNow = LocalDateTime.now();

    public RecipesService() {
        recipesDao = new RecipesDao();
        userDao = new UsersDao();
        categoryDao = new CategoryDao();
        stepDao = new StepsDao();
        ingredientDao = new IngredientDao();
        ratingDao = new RatingDao();
        uploadImageDao = new UploadImageDao();
        notificationTypeDao = new NotificationTypeDao();
        notificationDao = new NotificationDao();
        recipeSaveDao = new RecipeSaveDao();
        followerDao = new FollowerDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RecipesViewModel> getRecipes(@Context UriInfo ui) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return recipesDao.getData(url);
    }

    @GET
    @Path("getSaveRecipe")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeListResponse getSaveRecipe(@Context UriInfo ui,
            @QueryParam("userId") int userId,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        List<RecipesViewModel> recipesViewModels = recipesDao.getSaveRecipe(url, userId, pageIndex, pageSize);
        int totalRecipe = recipesDao.countSaveRecipe(userId);
        int totalPage = totalRecipe / pageSize;
        if (totalRecipe % pageSize != 0) {
            totalPage += 1;
        }
        return new RecipeListResponse(totalPage, recipesViewModels);
    }

    @POST
    @Path("filterData")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RecipeListResponse getRecipes(@Context UriInfo ui, RecipeFilterRequest request) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        String listCatId = "";
        if (!request.getListCatId().isEmpty()) {
            listCatId += ",";
            for (int catId : request.getListCatId()) {
                listCatId += String.valueOf(catId) + ",";
            }
        }
        List<RecipesViewModel> recipesViewModels = recipesDao.getData(url, request, listCatId);
        int totalCategory = recipesDao.countRecipe(request, listCatId);
        int totalPage = totalCategory / request.getPageSize();
        if (totalCategory % request.getPageSize() != 0) {
            totalPage += 1;
        }
        return new RecipeListResponse(totalPage, recipesViewModels);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipesViewModel getRecipesById(@Context UriInfo ui, @QueryParam("id") int id) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return recipesDao.getDataById(url, id);
    }

    @GET
    @Path("getRecipe")
    @Produces(MediaType.APPLICATION_JSON)
    public RecipeDetailResponse getRecipesOutput(@Context UriInfo ui, @QueryParam("id") int id) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        RecipesViewModel recipe = recipesDao.getDataById(url, id);
        List<Steps> listSteps = stepDao.getData(id);
        List<Ingredient> listIngredients = ingredientDao.getData(id);
        return new RecipeDetailResponse(
                recipe,
                listSteps,
                listIngredients
        );
    }

    @POST
    @Path("/uploadImage")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String check(
            final @Context ServletConfig config,
            @FormDataParam("file") InputStream uploadedInputStream) {
        String path = config.getServletContext().getRealPath("/images");
        String fileName = "recipe_" + "Check" + "_" + dateTimeNow.format(formatDate);

        String urlResult = uploadImageDao.uploadImage(uploadedInputStream, path, FolderNameConstant.recipe, fileName);
        return urlResult;
    }

    @POST
    @Path("insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OutputResponse insert(final @Context ServletConfig config, RecipeInputData input) {
        String path = config.getServletContext().getRealPath("/images");
        // Validate Recipe input
        if (input.getRecipe().getName().trim().length() == 0) {
            return new OutputResponse("Recipe name is requied!");
        } else if (input.getRecipe().getServes() < 0) {
            return new OutputResponse("Serves is must more or equal 0!");
        } else if (!categoryDao.checkExistCategory(input.getRecipe().getCategoryId())) {
            return new OutputResponse("Category with id = " + input.getRecipe().getCategoryId() + " is not exist or deleted!");
        } else if (!userDao.checkExistUser(input.getRecipe().getAuthorId())) {
            return new OutputResponse("Author with id = " + input.getRecipe().getAuthorId() + " is not exist or blocked!");
        } else if (!userDao.checkExistUser(input.getRecipe().getCreateUser())) {
            return new OutputResponse("Recipe createUser with id = " + input.getRecipe().getCreateUser() + " is not exist or deleted!");
        }
        // Validate Steps
        int stepNumber = 1;
        for (Steps step : input.getListSteps()) {
            if (step.getDescription().trim().length() == 0) {
                return new OutputResponse("Step description at " + stepNumber + " is requied!");
            }
            step.setStepNumber(stepNumber);
            stepNumber += 1;
        }

        // Validate Ingredient
        int index = 1;
        for (Ingredient ingredient : input.getListInfgredients()) {
            if (ingredient.getUnitOfMeasurement().length() == 0) {
                return new OutputResponse("Ingredient UnitOfMeasurement at index = " + index + " is requied!");
            } else if (ingredient.getUnitOfMeasurement().length() > 250) {
                return new OutputResponse("Ingredient UnitOfMeasurement at index = " + index + " is too long, maxlength is 250 characters!");
            } else if (ingredient.getName().length() == 0) {
                return new OutputResponse("Ingredient Name at index = " + index + " is requied!");
            } else if (ingredient.getName().length() > 250) {
                return new OutputResponse("Ingredient Name at index = " + index + " is too long, maxlength is 250 characters!");
            }
            index += 1;
        }

        // Insert Recipe and return recipeId
        if (input.getRecipe().getImage().length() > 0) {
            String fileName = "recipe_" + input.getRecipe().getCreateUser() + "_" + dateTimeNow.format(formatDate);
            input.getRecipe().setImage(uploadImageDao.uploadImage(input.getRecipe().getImage(), path, FolderNameConstant.recipe, fileName));
        }
        int recipeId = recipesDao.insertRecipe(input.getRecipe());
        if (recipeId > 0) {

            int saveSuccess = 0;
            // Insert Steps
            for (Steps step : input.getListSteps()) {
                step.setRecipeId(recipeId);
                step.setStatus(0);
                if (!stepDao.insertData(step)) {
                    saveSuccess += 1;
                    break;
                }
            }

            // Insert Ingredient
            for (Ingredient ingredient : input.getListInfgredients()) {
                ingredient.setRecipeId(recipeId);
                ingredient.setStatus(0);
                if (!ingredientDao.insertData(ingredient)) {
                    saveSuccess += 1;
                    break;
                }
            }
            if (saveSuccess == 0) {
                List<NotificationViewModel> notificationViewModels = sendNotificationCreateRecipe(input.getRecipe().getAuthorId());
                return new OutputResponse("Success!", notificationViewModels);
            }
        }
        return new OutputResponse("Failed");
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OutputResponse update(final @Context ServletConfig config, RecipeInputData input) {
        String path = config.getServletContext().getRealPath("/images");
        // Validate recipe
        if (input.getRecipe().getName().trim().length() == 0) {
            return new OutputResponse("Recipe name is requied!");
        } else if (input.getRecipe().getServes() < 0) {
            return new OutputResponse("Serves is must more or equal 0!");
        } else if (input.getRecipe().getStatus() < 0) {
            return new OutputResponse("Status is must more or equal 0!");
        } else if (!categoryDao.checkExistCategory(input.getRecipe().getCategoryId())) {
            return new OutputResponse("Category with id = " + input.getRecipe().getCategoryId() + " is not exist or deleted!");
        } else if (!userDao.checkExistUser(input.getRecipe().getAuthorId())) {
            return new OutputResponse("Author with id = " + input.getRecipe().getAuthorId() + " is not exist or blocked!");
        } else if (recipesDao.getDataById("", input.getRecipe().getId()).getId() <= 0) {
            return new OutputResponse("Recipes width id = " + input.getRecipe().getId() + " is not exist!");
        } else if (!userDao.checkExistUser(input.getRecipe().getUpdateUser())) {
            return new OutputResponse("Recipe updateUser with id = " + input.getRecipe().getUpdateUser() + " is not exist or deleted!");
        }

        // Validate Steps
        int index = 1;
        for (Steps step : input.getListSteps()) {
            if (step.getDescription().trim().length() == 0) {
                return new OutputResponse("Step description at " + index + " is requied!");
            }
            step.setStepNumber(index);
            index += 1;
        }

        // Validate Ingredient
        index = 1;
        for (Ingredient foodIngredient : input.getListInfgredients()) {
            if (foodIngredient.getUnitOfMeasurement().length() == 0) {
                return new OutputResponse("Ingredient UnitOfMeasurement at index = " + index + " is requied!");
            } else if (foodIngredient.getUnitOfMeasurement().length() > 250) {
                return new OutputResponse("Ingredient UnitOfMeasurement at index = " + index + " is too long, maxlength is 250 characters!");
            } else if (foodIngredient.getName().length() == 0) {
                return new OutputResponse("Ingredient Name at index = " + index + " is requied!");
            } else if (foodIngredient.getName().length() > 250) {
                return new OutputResponse("Ingredient Name at index = " + index + " is too long, maxlength is 250 characters!");
            }
            index += 1;
        }

        // Check end insert image to server
        if (input.getRecipe().getImage().length() > 0 && !input.getRecipe().getImage().contains(FolderNameConstant.recipe)) {
            String fileName = "recipe_" + input.getRecipe().getCreateUser() + "_" + dateTimeNow.format(formatDate);
            input.getRecipe().setImage(uploadImageDao.uploadImage(input.getRecipe().getImage(), path, FolderNameConstant.recipe, fileName));
        }
        if (recipesDao.updateData(input.getRecipe())) {
            int saveSuccess = 0;

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
                    if (!stepDao.deleteData(stepDB.getId())) {
                        saveSuccess += 1;
                    }
                }
            }
            int stepNumber = 1;
            for (Steps step : input.getListSteps()) {
                step.setRecipeId(input.getRecipe().getId());
                step.setStatus(0);
                step.setStepNumber(stepNumber);

                // if stepId <= 0 => insert new step to DB
                if (step.getId() <= 0) {
                    if (!stepDao.insertData(step)) {
                        saveSuccess += 1;
                        break;
                    }
                } // if stepId > 0 => update step
                else {
                    if (!stepDao.updateData(step)) {
                        saveSuccess += 1;
                        break;
                    }
                }
                stepNumber += 1;
            }

            // Update Ingredient
            // Check Exist Step item in DB
            List<Ingredient> listIngredientInDB = ingredientDao.getData(input.getRecipe().getId());
            for (Ingredient ingredientDB : listIngredientInDB) {
                int countExistInList = 0;

                // For loop and check if item in DB exist in list input then continue
                for (Ingredient ingredientInput : input.getListInfgredients()) {
                    if (ingredientInput.getId() == ingredientDB.getId()) {
                        countExistInList += 1;
                        break;
                    }
                }
                // if item in DB not exist in list input, delete this item in DB
                if (countExistInList == 0) {
                    if (!ingredientDao.deleteData(ingredientDB.getId())) {
                        saveSuccess += 1;
                    }
                }
            }
            for (Ingredient ingredient : input.getListInfgredients()) {
                ingredient.setRecipeId(input.getRecipe().getId());
                ingredient.setStatus(0);

                // if ingredientId <= 0 => insert new Ingredient to DB
                if (ingredient.getId() <= 0) {
                    if (!ingredientDao.insertData(ingredient)) {
                        saveSuccess += 1;
                    }
                } // if ingredientId > 0 => update Ingredient
                else {
                    if (!ingredientDao.updateData(ingredient)) {
                        saveSuccess += 1;
                    }
                }
            }
            if (saveSuccess == 0) {
                List<NotificationViewModel> notificationViewModels = sendNotificationUpdateRecipe(input.getRecipe().getId(), input.getRecipe().getName());
                return new OutputResponse("Success!", notificationViewModels);
            }
        }
        return new OutputResponse("Failed!");
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("id") int id, @QueryParam("userId") int userId) {
        if (!recipesDao.checkExistRecipe(id)) {
            return "Recipes width id = " + id + " is not exist or deleted!";
        } else if (recipesDao.deleteData(id, userId)) {
            return "Success!";
        }
        return "Failed!";
    }

    @POST
    @Path("rating")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OutputResponse insert(Rating rating) {
        if (rating.getRating() < 0 || rating.getRating() > 5) {
            return new OutputResponse("Rating min is 0 and max is 5!");
        } else if (!recipesDao.checkExistRecipe(rating.getRecipeId())) {
            return new OutputResponse("Recipe with id = " + rating.getRecipeId() + " is not exist or deleted!");
        }
        UsersViewModel userModel = userDao.getDataById("", rating.getUserId());
        if (userModel.getId() <= 0 || userModel.getStatus() == 1) {
            return new OutputResponse("User with id = " + rating.getUserId() + " is not exist or deleted!");
        } else if (ratingDao.insertData(rating)) {
            List<NotificationViewModel> notificationViewModels = new ArrayList<>();
            notificationViewModels.add(sendNotificationRatingRecipe(userModel.getUserName(), rating.getUserId(), rating.getRecipeId()));
            return new OutputResponse("Success!", notificationViewModels);
        }
        return new OutputResponse("Failed!");
    }

    private List<NotificationViewModel> sendNotificationUpdateRecipe(int recipeId, String recipeName) {
        List<NotificationViewModel> notificationViewModels = new ArrayList<>();
        int notificationTypeId = NotificationTypeIdConstant.updateRecipe;
        NotificationType notificationType = notificationTypeDao.getDataById(notificationTypeId);
        String content = notificationType.getDescription();
        String typeName = notificationType.getName();

        if (content.contains("[recipeName]")) {
            content = content.replace("[recipeName]", recipeName);
        }
        String createTime = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
        List<Integer> listUserId = recipeSaveDao.getListUserIdByRecipeId(recipeId);
        for (Integer userId : listUserId) {
            if (notificationDao.insertData(userId, notificationTypeId, content, 0)) {
                notificationViewModels.add(new NotificationViewModel(typeName, content, 0, createTime, userId));
            }
        }
        return notificationViewModels;
    }

    private List<NotificationViewModel> sendNotificationCreateRecipe(int authorId) {
        List<NotificationViewModel> notificationViewModels = new ArrayList<>();
        int notificationTypeId = NotificationTypeIdConstant.updateRecipe;
        NotificationType notificationType = notificationTypeDao.getDataById(notificationTypeId);
        String content = notificationType.getDescription();
        String typeName = notificationType.getName();

        if (content.contains("[userDisplay]")) {
            content = content.replace("[userDisplay]", userDao.getDataById("", authorId).getDisplayName());
        }
        String createTime = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
        List<UsersViewModel> listUsers = userDao.getListFollowedByOthersUser("", authorId, 1, Integer.MAX_VALUE);
        for (UsersViewModel model : listUsers) {
            if (notificationDao.insertData(model.getId(), notificationTypeId, content, 0)) {
                notificationViewModels.add(new NotificationViewModel(typeName, content, 0, createTime, model.getId()));
            }
        }
        return notificationViewModels;
    }

    private NotificationViewModel sendNotificationRatingRecipe(String userDisplay, int userId, int recipeId) {
        int notificationTypeId = NotificationTypeIdConstant.ratingRecipe;
        NotificationType notificationType = notificationTypeDao.getDataById(notificationTypeId);
        String content = notificationType.getDescription();
        String typeName = notificationType.getName();

        if (content.contains("[userDisplay]")) {
            content = content.replace("[userDisplay]", userDisplay);
        }
        if (content.contains("[recipeName]")) {
            content = content.replace("[recipeName]", recipesDao.getDataById("", recipeId).getName());
        }
        String createTime = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
        if (notificationDao.insertData(userId, notificationTypeId, content, 0)) {
            return new NotificationViewModel(typeName, content, 0, createTime, userId);
        }
        return new NotificationViewModel();
    }
}
