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
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listRecipeViewModels.add(recipeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRecipeViewModels;
    }

    public List<RecipesViewModel> getData(String keyword, boolean isGetAll) {
        List<RecipesViewModel> listRecipeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListIngredients(?,?)}");
            if (keyword.equalsIgnoreCase("_")) {
                keyword = "";
            }
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                RecipesViewModel recipeViewModel = new RecipesViewModel();
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
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
            statement = con.prepareCall("{call GetRecipesById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                recipeViewModel.setId(result.getInt("Id"));
                recipeViewModel.setName(result.getString("Name"));
                recipeViewModel.setStatus(result.getInt("Status"));
                recipeViewModel.setCreateDate(result.getDate("CreateDate"));
                recipeViewModel.setCreateUser(result.getInt("CreateUser"));
                recipeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                recipeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                recipeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                recipeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
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
            statement = con.prepareCall("insert into Recipes(Name, Status, CreateDate, CreateUser) values (?,?,?,?)");
            statement.setString(1, t.getName());
            statement.setInt(2, 0);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getCreateUser());
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
            statement = con.prepareCall("update Recipes set Name=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getName());
            statement.setInt(2, t.getStatus());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getUpdateUser());
            statement.setInt(5, t.getId());
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
}
