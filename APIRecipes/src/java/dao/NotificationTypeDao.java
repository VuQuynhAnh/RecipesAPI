/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.NotificationType;
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
import viewModel.NotificationTypeViewModel;

/**
 *
 * @author DELL
 */
public class NotificationTypeDao implements IService<NotificationType, NotificationTypeViewModel, Integer> {

    Connection con = null;

    public NotificationTypeDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<NotificationTypeViewModel> getData() {
        List<NotificationTypeViewModel> listNotificationTypeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllNotificationType()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                NotificationTypeViewModel notificationTypeViewModel = new NotificationTypeViewModel();
                notificationTypeViewModel.setId(result.getInt("Id"));
                notificationTypeViewModel.setName(result.getString("Name"));
                notificationTypeViewModel.setDescription(result.getString("Description"));
                notificationTypeViewModel.setStatus(result.getInt("Status"));
                notificationTypeViewModel.setCreateDate(result.getDate("CreateDate"));
                notificationTypeViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationTypeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                notificationTypeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                notificationTypeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationTypeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listNotificationTypeViewModels.add(notificationTypeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNotificationTypeViewModels;
    }

    public List<NotificationTypeViewModel> getData(String keyword, boolean isGetAll) {
        List<NotificationTypeViewModel> listNotificationTypeViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListNotificationType(?,?)}");
            if (keyword.equalsIgnoreCase("_")) {
                keyword = "";
            }
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                NotificationTypeViewModel notificationTypeViewModel = new NotificationTypeViewModel();
                notificationTypeViewModel.setId(result.getInt("Id"));
                notificationTypeViewModel.setName(result.getString("Name"));
                notificationTypeViewModel.setDescription(result.getString("Description"));
                notificationTypeViewModel.setStatus(result.getInt("Status"));
                notificationTypeViewModel.setCreateDate(result.getDate("CreateDate"));
                notificationTypeViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationTypeViewModel.setUpdateDate(result.getDate("UpdateDate"));
                notificationTypeViewModel.setUpdateUser(result.getInt("UpdateUser"));
                notificationTypeViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationTypeViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listNotificationTypeViewModels.add(notificationTypeViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNotificationTypeViewModels;
    }

    @Override
    public NotificationTypeViewModel getDataById(Integer id) {
        NotificationTypeViewModel notificationViewModel = new NotificationTypeViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetNotificationTypeById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                notificationViewModel.setId(result.getInt("Id"));
                notificationViewModel.setName(result.getString("Name"));
                notificationViewModel.setDescription(result.getString("Description"));
                notificationViewModel.setStatus(result.getInt("Status"));
                notificationViewModel.setCreateDate(result.getDate("CreateDate"));
                notificationViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationViewModel.setUpdateDate(result.getDate("UpdateDate"));
                notificationViewModel.setUpdateUser(result.getInt("UpdateUser"));
                notificationViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notificationViewModel;
    }

    @Override
    public boolean insertData(NotificationType t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into NotificationType(Id, Name, Description, Status, CreateDate, CreateUser) values (?,?,?,?,?,?)");
            statement.setInt(1, t.getId());
            statement.setString(2, t.getName());
            statement.setString(3, t.getDescription());
            statement.setInt(4, 0);
            statement.setDate(5, Date.valueOf(LocalDate.now()));
            statement.setInt(6, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(NotificationType t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update NotificationType set Name=?, Description=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getName());
            statement.setString(2, t.getDescription());
            statement.setInt(3, t.getStatus());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setInt(5, t.getUpdateUser());
            statement.setInt(6, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update NotificationType set Status = 1, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationTypeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistNotificationType(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from NotificationType where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
