/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Steps;
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
public class StepsDao implements IService<Steps, Steps, Integer> {

    Connection con = null;

    public StepsDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<Steps> getData() {
        return new ArrayList<>();
    }

    public List<Steps> getData(Integer recipeId) {
        List<Steps> listSteps = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListSteps(?)}");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Steps step = new Steps();
                step.setId(result.getInt("Id"));
                step.setRecipeId(result.getInt("RecipeId"));
                step.setStepNumber(result.getInt("StepNumber"));
                step.setDescription(result.getString("Description"));
                step.setStatus(result.getInt("Status"));
                listSteps.add(step);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StepsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSteps;
    }

    @Override
    public Steps getDataById(Integer id) {
        Steps step = new Steps();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetStepById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                step.setId(result.getInt("Id"));
                step.setRecipeId(result.getInt("RecipeId"));
                step.setStepNumber(result.getInt("StepNumber"));
                step.setDescription(result.getString("Description"));
                step.setStatus(result.getInt("Status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StepsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return step;
    }

    @Override
    public boolean insertData(Steps t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Steps(RecipeId, StepNumber, Description, Status) values (?,?,?,?)");
            statement.setInt(1, t.getRecipeId());
            statement.setInt(2, t.getStepNumber());
            statement.setString(3, t.getDescription());
            statement.setInt(4, 0);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StepsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(Steps t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Steps set StepNumber=?, Description=?, Status=? where Id=?");
            statement.setInt(1, t.getStepNumber());
            statement.setString(2, t.getDescription());
            statement.setInt(3, t.getStatus());
            statement.setInt(4, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StepsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteData(Integer id, int userId) {
        return false;
    }

    public boolean deleteData(Integer id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Steps set Status = 1 where Id=?");
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StepsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistSteps(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Steps where Id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
