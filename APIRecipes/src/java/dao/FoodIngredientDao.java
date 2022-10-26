/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.FoodIngredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viewModel.FoodIngredientViewModel;

/**
 *
 * @author DELL
 */
public class FoodIngredientDao {

    Connection con = null;

    public FoodIngredientDao() {
        con = GetConnection.getConnect();
    }

    public List<FoodIngredientViewModel> getData(Integer recipeId) {
        List<FoodIngredientViewModel> listFoodIngredients = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call getFoodIngredient(?)}");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                FoodIngredientViewModel foodIngredient = new FoodIngredientViewModel();
                foodIngredient.setRecipeId(result.getInt("RecipeId"));
                foodIngredient.setIngredientId(result.getInt("IngredientId"));
                foodIngredient.setUnitOfMeasurement(result.getString("UnitOfMeasurement"));
                foodIngredient.setStatus(result.getInt("Status"));
                foodIngredient.setIngredientDisplay(result.getString("IngredientDisplay"));
                listFoodIngredients.add(foodIngredient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodIngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listFoodIngredients;
    }

    public FoodIngredient getDetailFoodIngredient(Integer recipeId, int ingredientId) {
        FoodIngredient foodIngredient = new FoodIngredient();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{select * from FoodIngredient where RecipeId=? and IngredientId=?}");
            statement.setInt(1, recipeId);
            statement.setInt(2, ingredientId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                foodIngredient.setRecipeId(result.getInt("RecipeId"));
                foodIngredient.setIngredientId(result.getInt("IngredientId"));
                foodIngredient.setUnitOfMeasurement(result.getString("UnitOfMeasurement"));
                foodIngredient.setStatus(result.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodIngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foodIngredient;
    }

    public boolean insertData(FoodIngredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into FoodIngredient(RecipeId, IngredientId, UnitOfMeasurement, Status) values (?,?,?,?)");
            statement.setInt(1, t.getRecipeId());
            statement.setInt(2, t.getIngredientId());
            statement.setString(3, t.getUnitOfMeasurement());
            statement.setInt(4, 0);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodIngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateData(FoodIngredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update FoodIngredient set UnitOfMeasurement=?, Status=? where RecipeId=? and IngredientId=?");
            statement.setString(1, t.getUnitOfMeasurement());
            statement.setInt(2, t.getStatus());
            statement.setInt(3, t.getRecipeId());
            statement.setInt(4, t.getIngredientId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodIngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteFoodIngredient(int recipeId, int ingredientId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update FoodIngredient set Status = 1 where RecipeId=? and IngredientId=?");
            statement.setInt(1, recipeId);
            statement.setInt(2, ingredientId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodIngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistFoodIngredient(int recipeId, int ingredientId) {
        if (recipeId <= 0 || ingredientId <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from FoodIngredient where RecipeId=? and IngredientId=? and Status = 0");
            statement.setInt(1, recipeId);
            statement.setInt(2, ingredientId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
