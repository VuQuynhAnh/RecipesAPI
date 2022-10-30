/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Followers;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class FollowerDao {

    Connection con = null;

    public FollowerDao() {
        con = GetConnection.getConnect();
    }

    public boolean insertData(int userId, int followerId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Followers(UserId, FollowerId, Status, CreateDate) values (?,?,?,?)");
            statement.setInt(1, userId);
            statement.setInt(2, followerId);
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

    public boolean reFollower(int userId, int followerId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Followers set Status=0, CreateDate=? where UserId=? and FollowerId=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, followerId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteData(int userId, int followerId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Followers set Status = 1 where UserId=? and FollowerId=?");
            statement.setInt(1, userId);
            statement.setInt(2, followerId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistFollowerItem(int userId, int followerId) {
        if (userId <= 0 || followerId <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Followers where UserId=? and FollowerId=? and Status = 0");
            statement.setInt(1, userId);
            statement.setInt(2, followerId);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public Followers getFollowerItem(int userId, int followerId) {
        Followers recipeSave = new Followers();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Followers where UserId=? and FollowerId=?");
            statement.setInt(1, userId);
            statement.setInt(2, followerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                recipeSave.setFollowerId(followerId);
                recipeSave.setUserId(userId);
                recipeSave.setStatus(result.getInt("Status"));
                recipeSave.setCreateDate(result.getDate("CreateDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecipeSaveDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recipeSave;
    }

}
