/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ingredient;
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
import viewModel.IngredientViewModel;

/**
 *
 * @author DELL
 */
public class IngredientDao implements IService<Ingredient, IngredientViewModel, Integer> {

    Connection con = null;

    public IngredientDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<IngredientViewModel> getData() {
        List<IngredientViewModel> listIngredientViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllIngredient()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                IngredientViewModel ingredientViewModel = new IngredientViewModel();
                ingredientViewModel.setId(result.getInt("Id"));
                ingredientViewModel.setName(result.getString("Name"));
                ingredientViewModel.setStatus(result.getInt("Status"));
                ingredientViewModel.setCreateDate(result.getDate("CreateDate"));
                ingredientViewModel.setCreateUser(result.getInt("CreateUser"));
                ingredientViewModel.setUpdateDate(result.getDate("UpdateDate"));
                ingredientViewModel.setUpdateUser(result.getInt("UpdateUser"));
                ingredientViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                ingredientViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listIngredientViewModels.add(ingredientViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIngredientViewModels;
    }

    public List<IngredientViewModel> getData(String keyword, boolean isGetAll) {
        List<IngredientViewModel> listIngredientViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListIngredient(?,?)}");
            if (keyword.equalsIgnoreCase("_")) {
                keyword = "";
            }
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                IngredientViewModel ingredientViewModel = new IngredientViewModel();
                ingredientViewModel.setId(result.getInt("Id"));
                ingredientViewModel.setName(result.getString("Name"));
                ingredientViewModel.setStatus(result.getInt("Status"));
                ingredientViewModel.setCreateDate(result.getDate("CreateDate"));
                ingredientViewModel.setCreateUser(result.getInt("CreateUser"));
                ingredientViewModel.setUpdateDate(result.getDate("UpdateDate"));
                ingredientViewModel.setUpdateUser(result.getInt("UpdateUser"));
                ingredientViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                ingredientViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listIngredientViewModels.add(ingredientViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIngredientViewModels;
    }

    @Override
    public IngredientViewModel getDataById(Integer id) {
        IngredientViewModel ingredientViewModel = new IngredientViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetIngredientById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                ingredientViewModel.setId(result.getInt("Id"));
                ingredientViewModel.setName(result.getString("Name"));
                ingredientViewModel.setStatus(result.getInt("Status"));
                ingredientViewModel.setCreateDate(result.getDate("CreateDate"));
                ingredientViewModel.setCreateUser(result.getInt("CreateUser"));
                ingredientViewModel.setUpdateDate(result.getDate("UpdateDate"));
                ingredientViewModel.setUpdateUser(result.getInt("UpdateUser"));
                ingredientViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                ingredientViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredientViewModel;
    }

    @Override
    public boolean insertData(Ingredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Ingredient(Name, Status, CreateDate, CreateUser) values (?,?,?,?)");
            statement.setString(1, t.getName());
            statement.setInt(2, 0);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(Ingredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Ingredient set Name=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getName());
            statement.setInt(2, t.getStatus());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getUpdateUser());
            statement.setInt(5, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Ingredient set Status = 1, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistIngredient(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Ingredient where Id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
