/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Rating;
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
public class RatingDao {

    Connection con = null;

    public RatingDao() {
        con = GetConnection.getConnect();
    }

    public List<Rating> getDataByRecipeId(Integer recipeId) {
        List<Rating> listRatings = new ArrayList();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetRatingByRecipeId(?)}");
            statement.setInt(1, recipeId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Rating rating = new Rating();
                rating.setId(result.getInt("Id"));
                rating.setRecipeId(result.getInt("RecipeId"));
                rating.setUserId(result.getInt("UserId"));
                rating.setRating(result.getInt("Rating"));
                rating.setCreateDate(result.getDate("CreateDate"));
                listRatings.add(rating);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RatingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRatings;
    }

    public boolean insertData(Rating t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Rating(RecipeId, UserId, Rating, CreateDate) values (?,?,?,?)");
            statement.setInt(1, t.getRecipeId());
            statement.setInt(2, t.getUserId());
            statement.setInt(3, t.getRating());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RatingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Rating t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Rating set Rating=?, CreateDate=? where UserId=? and RecipeId=?");
            statement.setInt(1, t.getRating());
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setInt(3, t.getUserId());
            statement.setInt(4, t.getRecipeId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RatingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistRating(int userId, int recipeId) {
        if (userId <= 0) {
            return false;
        } else if (recipeId <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Rating where UserId=? and RecipeId=?");
            statement.setInt(1, userId);
            statement.setInt(2, recipeId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
