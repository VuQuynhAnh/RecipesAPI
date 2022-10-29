/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.LoginDevice;
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
public class LoginDeviceDao {

    Connection con = null;

    public LoginDeviceDao() {
        con = GetConnection.getConnect();
    }

    public List<LoginDevice> getData(int userId) {
        List<LoginDevice> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllLoginDevice(?)}");
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                LoginDevice device = new LoginDevice();
                device.setId(result.getInt("Id"));
                device.setDeviceName(result.getString("DeviceName"));
                device.setUserId(result.getInt("UserId"));
                device.setStatus(result.getInt("Status"));
                device.setLastLoginDate(result.getDate("LastLoginDate"));
                device.setLastLogoutDate(result.getDate("LastLogoutDate"));
                listCategoryViewModels.add(device);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    public LoginDevice getDataById(Integer userId, String deviceName) {
        LoginDevice device = new LoginDevice();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetDetailLoginDevice(?,?)}");
            statement.setInt(1, userId);
            statement.setString(2, deviceName);

            ResultSet result = statement.executeQuery();
            if (result.next()) {
                device.setId(result.getInt("Id"));
                device.setDeviceName(result.getString("DeviceName"));
                device.setUserId(result.getInt("UserId"));
                device.setStatus(result.getInt("Status"));
                device.setLastLoginDate(result.getDate("LastLoginDate"));
                device.setLastLogoutDate(result.getDate("LastLogoutDate"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return device;
    }

    public boolean fristLogin(String deivceName, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into LoginDevice(DeviceName, UserId, Status, LastLoginDate, LastLogoutDate) values (?,?,?,?,?)");
            statement.setString(1, deivceName);
            statement.setInt(2, userId);
            statement.setInt(3, 0);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setDate(5, null);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean reLogin(String deivceName, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update LoginDevice set LastLoginDate=?, Status=0 where DeviceName=? and UserId=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setString(2, deivceName);
            statement.setInt(3, userId);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean logout(Integer userId, String deviceName) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update LoginDevice set Status = 1, LastLogoutDate=? where UserId=? and DeviceName=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setString(3, deviceName);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
