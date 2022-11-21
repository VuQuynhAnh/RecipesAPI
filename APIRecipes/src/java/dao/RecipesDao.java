/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Recipes;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import requests.RecipeFilterRequest;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
public class RecipesDao {

    Connection con = null;

    public RecipesDao() {
        con = GetConnection.getConnect();
    }

    public List<RecipesViewModel> getData(String serverUrl) {
        List<RecipesViewModel> listRecipeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllRecipes()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RecipesViewModel recipeViewModel = new RecipesViewModel();
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setCategoryId(result.getInt("CategoryId"));
                recipeViewModel.setAuthorId(result.getInt("AuthorId"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setOrigin(result.getString("Origin"));
                recipeViewModel.setServes(result.getInt("Serves"));
                recipeViewModel.setCalories(result.getFloat("Calories"));
                recipeViewModel.setCarbo(result.getFloat("Carbo"));
                recipeViewModel.setFat(result.getFloat("Fat"));
                recipeViewModel.setProtein(result.getFloat("Protein"));
                recipeViewModel.setImage("");
                if (result.getString("Image") != null && result.getString("Image").length() > 1) {
                    recipeViewModel.setImage(serverUrl + result.getString("Image"));
                }
                recipeViewModel.setTotalViews(result.getInt("TotalViews"));
                recipeViewModel.setCookTime(result.getString("CookTime"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                recipeViewModel.setCategoryDisplay(result.getString("CategoryDisplay"));
                recipeViewModel.setAuthor(result.getString("Author"));
                recipeViewModel.setAuthorAvatar("");
                if (result.getString("AuthorAvatar") != null && result.getString("AuthorAvatar").length() > 1) {
                    recipeViewModel.setAuthorAvatar(serverUrl + result.getString("AuthorAvatar"));
                }
                recipeViewModel.setAvgRating(result.getDouble("AvgRating"));
                recipeViewModel.setTotalRating(result.getInt("TotalRating"));
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    public List<RecipesViewModel> getSaveRecipe(String serverUrl, int userId, int pageIndex, int pageSize) {
        List<RecipesViewModel> listRecipeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetSaveRecipes(?,?,?)}");
            statement.setInt(1, userId);
            statement.setInt(2, pageIndex);
            statement.setInt(3, pageSize);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RecipesViewModel recipeViewModel = new RecipesViewModel();
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setCategoryId(result.getInt("CategoryId"));
                recipeViewModel.setAuthorId(result.getInt("AuthorId"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setOrigin(result.getString("Origin"));
                recipeViewModel.setServes(result.getInt("Serves"));
                recipeViewModel.setCalories(result.getFloat("Calories"));
                recipeViewModel.setCarbo(result.getFloat("Carbo"));
                recipeViewModel.setFat(result.getFloat("Fat"));
                recipeViewModel.setProtein(result.getFloat("Protein"));
                recipeViewModel.setImage("");
                if (result.getString("Image") != null && result.getString("Image").length() > 1) {
                    recipeViewModel.setImage(serverUrl + result.getString("Image"));
                }
                recipeViewModel.setTotalViews(result.getInt("TotalViews"));
                recipeViewModel.setCookTime(result.getString("CookTime"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                recipeViewModel.setCategoryDisplay(result.getString("CategoryDisplay"));
                recipeViewModel.setAuthor(result.getString("Author"));
                recipeViewModel.setAuthorAvatar("");
                if (result.getString("AuthorAvatar") != null && result.getString("AuthorAvatar").length() > 1) {
                    recipeViewModel.setAuthorAvatar(serverUrl + result.getString("AuthorAvatar"));
                }
                recipeViewModel.setAvgRating(result.getDouble("AvgRating"));
                recipeViewModel.setTotalRating(result.getInt("TotalRating"));
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    public int countSaveRecipe(int userId) {
        int totalRecipe = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountSaveRecipes(?)}");
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                totalRecipe = result.getInt("TotalRecipe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRecipe;
    }

    public List<RecipesViewModel> getData(String serverUrl, RecipeFilterRequest request, String listCatId) {
        List<RecipesViewModel> listRecipeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListRecipes(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString(1, request.getKeyword());
            statement.setString(2, listCatId);
            statement.setInt(3, request.getAuthorId());
            statement.setString(4, request.getName());
            statement.setString(5, request.getOrigin());
            statement.setString(6, request.getIngredient());
            statement.setInt(7, request.getMinServer());
            statement.setInt(8, request.getMaxServer());
            statement.setInt(9, request.getMinTotalViews());
            statement.setInt(10, request.getMaxTotalViews());
            statement.setInt(11, request.getMinTotalRating());
            statement.setInt(12, request.getMaxTotalRating());
            statement.setInt(13, request.getMinAvgRating());
            statement.setInt(14, request.getMaxAvgRating());
            statement.setFloat(15, request.getMinCalories());
            statement.setFloat(16, request.getMaxCalories());
            statement.setFloat(17, request.getMinFat());
            statement.setFloat(18, request.getMaxFat());
            statement.setFloat(19, request.getMinProtein());
            statement.setFloat(20, request.getMaxProtein());
            statement.setFloat(21, request.getMinCarbo());
            statement.setFloat(22, request.getMaxCarbo());
            statement.setString(23, request.getCookTime());
            statement.setInt(24, request.getStatus());
            statement.setBoolean(25, request.isSortByIdDESC());
            statement.setBoolean(26, request.isSortByNameASC());
            statement.setBoolean(27, request.isSortByServesASC());
            statement.setBoolean(28, request.isSortByServesDESC());
            statement.setBoolean(29, request.isSortByTotalViewDESC());
            statement.setBoolean(30, request.isSortByAvgRatingDESC());
            statement.setBoolean(31, request.isSortByAvgRatingDESC());
            statement.setBoolean(32, request.isSortByCaloriesDESC());
            statement.setBoolean(33, request.isSortByFatDESC());
            statement.setBoolean(34, request.isSortByProteinDESC());
            statement.setBoolean(35, request.isSortByCarbo());
            statement.setInt(36, request.getPageIndex());
            statement.setInt(37, request.getPageSize());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RecipesViewModel recipeViewModel = new RecipesViewModel();
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setCategoryId(result.getInt("CategoryId"));
                recipeViewModel.setAuthorId(result.getInt("AuthorId"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setOrigin(result.getString("Origin"));
                recipeViewModel.setServes(result.getInt("Serves"));
                recipeViewModel.setCalories(result.getFloat("Calories"));
                recipeViewModel.setCarbo(result.getFloat("Carbo"));
                recipeViewModel.setFat(result.getFloat("Fat"));
                recipeViewModel.setProtein(result.getFloat("Protein"));
                recipeViewModel.setImage("");
                if (result.getString("Image") != null && result.getString("Image").length() > 1) {
                    recipeViewModel.setImage(serverUrl + result.getString("Image"));
                }
                recipeViewModel.setTotalViews(result.getInt("TotalViews"));
                recipeViewModel.setCookTime(result.getString("CookTime"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                recipeViewModel.setCategoryDisplay(result.getString("CategoryDisplay"));
                recipeViewModel.setAuthor(result.getString("Author"));
                recipeViewModel.setAuthorAvatar("");
                if (result.getString("AuthorAvatar") != null && result.getString("AuthorAvatar").length() > 1) {
                    recipeViewModel.setAuthorAvatar(serverUrl + result.getString("AuthorAvatar"));
                }
                recipeViewModel.setAvgRating(result.getDouble("AvgRating"));
                recipeViewModel.setTotalRating(result.getInt("TotalRating"));
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    public int countRecipe(RecipeFilterRequest request, String listCatId) {
        int totalRecipe = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountRecipeFilter(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString(1, request.getKeyword());
            statement.setString(2, listCatId);
            statement.setInt(3, request.getAuthorId());
            statement.setString(4, request.getName());
            statement.setString(5, request.getOrigin());
            statement.setString(6, request.getIngredient());
            statement.setInt(7, request.getMinServer());
            statement.setInt(8, request.getMaxServer());
            statement.setInt(9, request.getMinTotalViews());
            statement.setInt(10, request.getMaxTotalViews());
            statement.setInt(11, request.getMinTotalRating());
            statement.setInt(12, request.getMaxTotalRating());
            statement.setInt(13, request.getMinAvgRating());
            statement.setInt(14, request.getMaxAvgRating());
            statement.setFloat(15, request.getMinCalories());
            statement.setFloat(16, request.getMaxCalories());
            statement.setFloat(17, request.getMinFat());
            statement.setFloat(18, request.getMaxFat());
            statement.setFloat(19, request.getMinProtein());
            statement.setFloat(20, request.getMaxProtein());
            statement.setFloat(21, request.getMinCarbo());
            statement.setFloat(22, request.getMaxCarbo());
            statement.setString(23, request.getCookTime());
            statement.setInt(24, request.getStatus());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                totalRecipe += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRecipe;
    }

    public RecipesViewModel getDataById(String serverUrl, Integer id) {
        RecipesViewModel recipeViewModel = new RecipesViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetRecipeById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setCategoryId(result.getInt("CategoryId"));
                recipeViewModel.setAuthorId(result.getInt("AuthorId"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setOrigin(result.getString("Origin"));
                recipeViewModel.setServes(result.getInt("Serves"));
                recipeViewModel.setCalories(result.getFloat("Calories"));
                recipeViewModel.setCarbo(result.getFloat("Carbo"));
                recipeViewModel.setFat(result.getFloat("Fat"));
                recipeViewModel.setProtein(result.getFloat("Protein"));
                recipeViewModel.setImage("");
                if (result.getString("Image") != null && result.getString("Image").length() > 1) {
                    recipeViewModel.setImage(serverUrl + result.getString("Image"));
                }
                recipeViewModel.setTotalViews(result.getInt("TotalViews"));
                recipeViewModel.setCookTime(result.getString("CookTime"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                recipeViewModel.setCategoryDisplay(result.getString("CategoryDisplay"));
                recipeViewModel.setAuthor(result.getString("Author"));
                recipeViewModel.setAuthorAvatar("");
                if (result.getString("AuthorAvatar") != null && result.getString("AuthorAvatar").length() > 1) {
                    recipeViewModel.setAuthorAvatar(serverUrl + result.getString("AuthorAvatar"));
                }
                recipeViewModel.setAvgRating(result.getDouble("AvgRating"));
                recipeViewModel.setTotalRating(result.getInt("TotalRating"));
                // If have Recipe and Recipe is not delete
                if (result.getInt("Id") > 0 && result.getInt("status") == 0) {
                    statement = con.prepareCall("update Recipes set TotalViews=? where Id=?");
                    int totalView = result.getInt("TotalViews") + 1;
                    statement.setInt(1, totalView);
                    statement.setInt(2, result.getInt("Id"));
                    statement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipeViewModel;
    }

    public int insertRecipe(Recipes t) {
        PreparedStatement statement;
        if (t.getImage().length() == 1) {
            t.setImage("");
        }
        try {
            statement = con.prepareCall("insert into Recipes(CategoryId, AuthorId, Name, Origin, Serves, Calories, Fat, Protein, Carbo, Image, TotalViews, CookTime, Status, CreateDate, CreateUser) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, t.getCategoryId());
            statement.setInt(2, t.getAuthorId());
            statement.setString(3, t.getName());
            statement.setString(4, t.getOrigin());
            statement.setInt(5, t.getServes());
            statement.setFloat(6, t.getCalories());
            statement.setFloat(7, t.getFat());
            statement.setFloat(8, t.getProtein());
            statement.setFloat(9, t.getCarbo());
            statement.setString(10, t.getImage());
            statement.setInt(11, 1);
            statement.setString(12, t.getCookTime());
            statement.setInt(13, 0);
            statement.setDate(14, Date.valueOf(LocalDate.now()));
            statement.setInt(15, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return getIdRecipeAfterInsert(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private int getIdRecipeAfterInsert(Recipes t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetRecipeIdAfterInsert(?,?,?,?,?,?,?,?)}");
            statement.setInt(1, t.getCategoryId());
            statement.setInt(2, t.getAuthorId());
            statement.setString(3, t.getName());
            statement.setInt(4, t.getServes());
            statement.setFloat(5, t.getCalories());
            statement.setFloat(6, t.getFat());
            statement.setFloat(7, t.getProtein());
            statement.setFloat(8, t.getCarbo());

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("Id");
            }
        } catch (SQLException ex) {
            return 0;
        }
        return 0;
    }

    public boolean updateData(Recipes t) {
        PreparedStatement statement;
        if (t.getImage().length() > 1) {
            try {
                statement = con.prepareCall("update Recipes set CategoryId=?, AuthorId=?, Name=?, Origin=?, Serves=?, Calories=?, Fat=?, Protein=?, Carbo=?, Image=?, CookTime=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setInt(1, t.getCategoryId());
                statement.setInt(2, t.getAuthorId());
                statement.setString(3, t.getName());
                statement.setString(4, t.getOrigin());
                statement.setInt(5, t.getServes());
                statement.setFloat(6, t.getCalories());
                statement.setFloat(7, t.getFat());
                statement.setFloat(8, t.getProtein());
                statement.setFloat(9, t.getCarbo());
                statement.setString(10, t.getImage());
                statement.setString(11, t.getCookTime());
                statement.setInt(12, t.getStatus());
                statement.setDate(13, Date.valueOf(LocalDate.now()));
                statement.setInt(14, t.getUpdateUser());
                statement.setInt(15, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                statement = con.prepareCall("update Recipes set CategoryId=?, AuthorId=?, Name=?, Origin=?, Serves=?, Calories=?, Fat=?, Protein=?, Carbo=?, CookTime=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setInt(1, t.getCategoryId());
                statement.setInt(2, t.getAuthorId());
                statement.setString(3, t.getName());
                statement.setString(4, t.getOrigin());
                statement.setInt(5, t.getServes());
                statement.setFloat(6, t.getCalories());
                statement.setFloat(7, t.getFat());
                statement.setFloat(8, t.getProtein());
                statement.setFloat(9, t.getCarbo());
                statement.setString(10, t.getCookTime());
                statement.setInt(11, t.getStatus());
                statement.setDate(12, Date.valueOf(LocalDate.now()));
                statement.setInt(13, t.getUpdateUser());
                statement.setInt(14, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean deleteData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Recipes set Status = 1, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistRecipe(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Recipes where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
