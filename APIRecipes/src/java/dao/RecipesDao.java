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
import requestModel.RecipeRequest;
import viewModel.RecipesViewModel;

/**
 *
 * @author DELL
 */
public class RecipesDao implements IService<Recipes, RecipesViewModel, Integer> {

    Connection con = null;

    public RecipesDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<RecipesViewModel> getData() {
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
                recipeViewModel.setImage(result.getString("Image"));
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
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    public List<RecipesViewModel> getData(RecipeRequest request) {
        List<RecipesViewModel> listRecipeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListRecipes(?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setString(1, request.getKeyword());
            statement.setInt(2, request.getCatId());
            statement.setInt(3, request.getAuthorId());
            statement.setString(4, request.getName());
            statement.setString(5, request.getOrigin());
            statement.setInt(6, request.getMinServer());
            statement.setInt(7, request.getMaxServer());
            statement.setInt(8, request.getMinTotalViews());
            statement.setInt(9, request.getMaxTotalViews());
            statement.setString(10, request.getCookTime());
            statement.setInt(11, request.getStatus());

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RecipesViewModel recipeViewModel = new RecipesViewModel();
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setCategoryId(result.getInt("CategoryId"));
                recipeViewModel.setAuthorId(result.getInt("AuthorId"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setOrigin(result.getString("Origin"));
                recipeViewModel.setServes(result.getInt("Serves"));
                recipeViewModel.setImage(result.getString("Image"));
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
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    @Override
    public RecipesViewModel getDataById(Integer id) {
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
                recipeViewModel.setImage(result.getString("Image"));
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

    @Override
    public boolean insertData(Recipes t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Recipes(CategoryId, AuthorId, Name, Origin, Serves, Image, TotalViews, CookTime, Status, CreateDate, CreateUser) values (?,?,?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, t.getCategoryId());
            statement.setInt(2, t.getAuthorId());
            statement.setString(3, t.getName());
            statement.setString(4, t.getOrigin());
            statement.setInt(5, t.getServes());
            statement.setString(6, t.getImage());
            statement.setInt(7, 1);
            statement.setString(8, t.getCookTime());
            statement.setInt(9, 0);
            statement.setDate(10, Date.valueOf(LocalDate.now()));
            statement.setInt(11, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(Recipes t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Recipes set CategoryId=?, AuthorId=?, Name=?, Origin=?, Serves=?, Image=?, CookTime=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setInt(1, t.getCategoryId());
            statement.setInt(2, t.getAuthorId());
            statement.setString(3, t.getName());
            statement.setString(4, t.getOrigin());
            statement.setInt(5, t.getServes());
            statement.setString(6, t.getImage());
            statement.setString(7, t.getCookTime());
            statement.setInt(8, t.getStatus());
            statement.setDate(9, Date.valueOf(LocalDate.now()));
            statement.setInt(10, t.getUpdateUser());
            statement.setInt(11, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
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
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Recipes where Id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
