/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.RecipesSave;
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

/**
 *
 * @author DELL
 */
public class RecipeSaveDao {

    Connection con = null;

    public RecipeSaveDao() {
        con = GetConnection.getConnect();
    }

    public boolean insertData(int recipeId, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into RecipesSave(RecipeId, UserId, Status, CreateDate) values (?,?,?,?)");
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            statement.setInt(3, 0);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean reSaveData(int recipeId, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update RecipesSave set Status=0, CreateDate=? where RecipeId=? and UserId=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, recipeId);
            statement.setInt(3, userId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteData(int recipeId, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update RecipesSave set Status = 1 where RecipeId=? and UserId=?");
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistRecipeSave(int recipeId, int userId) {
        if (recipeId <= 0 || userId <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from RecipesSave where RecipeId=? and UserId=? and Status = 0");
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public RecipesSave getRecipesSave(int recipeId, int userId) {
        RecipesSave recipeSave = new RecipesSave();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from RecipesSave where RecipeId=? and UserId=?");
            statement.setInt(1, recipeId);
            statement.setInt(2, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                recipeSave.setRecipeId(recipeId);
                recipeSave.setUserId(userId);
                recipeSave.setStatus(result.getInt("Status"));
                recipeSave.setCreateDate(result.getDate("CreateDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipeSave;
    }

    public List<Integer> getListUserIdByRecipeId(int recipeId) {
        List<Integer> listData = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from RecipesSave where RecipeId=? and Status=0");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                listData.add(result.getInt("UserId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
}
