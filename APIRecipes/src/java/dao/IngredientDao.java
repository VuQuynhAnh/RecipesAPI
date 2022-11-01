/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class IngredientDao {

    Connection con = null;

    public IngredientDao() {
        con = GetConnection.getConnect();
    }

    public List<Ingredient> getData(Integer recipeId) {
        List<Ingredient> listIngredients = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call getIngredient(?)}");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setRecipeId(result.getInt("RecipeId"));
                ingredient.setId(result.getInt("Id"));
                ingredient.setUnitOfMeasurement(result.getString("UnitOfMeasurement"));
                ingredient.setStatus(result.getInt("Status"));
                ingredient.setName(result.getString("Name"));
                listIngredients.add(ingredient);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listIngredients;
    }

    public boolean insertData(Ingredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Ingredient(RecipeId, Name, UnitOfMeasurement, Status) values (?,?,?,?)");
            statement.setInt(1, t.getRecipeId());
            statement.setString(2, t.getName());
            statement.setString(3, t.getUnitOfMeasurement());
            statement.setInt(4, 0);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateData(Ingredient t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Ingredient set Name=?, UnitOfMeasurement=?, Status=? where Id=?");
            statement.setString(1, t.getName());
            statement.setString(2, t.getUnitOfMeasurement());
            statement.setInt(3, t.getStatus());
            statement.setInt(4, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteData(int id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Ingredient set Status = 1 where Id=?");
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistFoodIngredient(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Ingredient where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
