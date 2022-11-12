/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Notifications;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
public class NotificationDao {

    Connection con = null;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public NotificationDao() {
        con = GetConnection.getConnect();
    }

    public List<NotificationViewModel> getData() {
        List<NotificationViewModel> listNotificationViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllNotifications()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                NotificationViewModel notificationViewModel = new NotificationViewModel();
                notificationViewModel.setId(result.getInt("Id"));
                notificationViewModel.setUserId(result.getInt("UserId"));
                notificationViewModel.setNotificationId(result.getInt("NotificationId"));
                notificationViewModel.setDescription(result.getString("Description"));
                notificationViewModel.setStatus(result.getInt("Status"));
                notificationViewModel.setCreateDate(simpleDateFormat.format(result.getTimestamp("CreateDate")));
                notificationViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationViewModel.setUserDisplay(result.getString("UserDisplay"));
                listNotificationViewModels.add(notificationViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNotificationViewModels;
    }

    public List<NotificationViewModel> getNotificationByUserId(int userId, int status) {
        List<NotificationViewModel> listNotificationViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListNotificationByUserId(?,?)}");
            statement.setInt(1, userId);
            statement.setInt(2, status);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                NotificationViewModel notificationViewModel = new NotificationViewModel();
                notificationViewModel.setId(result.getInt("Id"));
                notificationViewModel.setUserId(result.getInt("UserId"));
                notificationViewModel.setNotificationId(result.getInt("NotificationId"));
                notificationViewModel.setDescription(result.getString("Description"));
                notificationViewModel.setStatus(result.getInt("Status"));
                notificationViewModel.setCreateDate(simpleDateFormat.format(result.getTimestamp("CreateDate")));
                notificationViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationViewModel.setUserDisplay(result.getString("UserDisplay"));
                listNotificationViewModels.add(notificationViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNotificationViewModels;
    }

    public List<NotificationViewModel> getNotificationByCreateUserId(int createUserId, int status) {
        List<NotificationViewModel> listNotificationViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListNotificationByCreateUserId(?,?)}");
            statement.setInt(1, createUserId);
            statement.setInt(2, status);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                NotificationViewModel notificationViewModel = new NotificationViewModel();
                notificationViewModel.setId(result.getInt("Id"));
                notificationViewModel.setUserId(result.getInt("UserId"));
                notificationViewModel.setNotificationId(result.getInt("NotificationId"));
                notificationViewModel.setDescription(result.getString("Description"));
                notificationViewModel.setStatus(result.getInt("Status"));
                notificationViewModel.setCreateDate(simpleDateFormat.format(result.getTimestamp("CreateDate")));
                notificationViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationViewModel.setUserDisplay(result.getString("UserDisplay"));
                listNotificationViewModels.add(notificationViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNotificationViewModels;
    }

    public NotificationViewModel getDataById(Integer id) {
        NotificationViewModel notificationViewModel = new NotificationViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetNotificationById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                notificationViewModel.setId(result.getInt("Id"));
                notificationViewModel.setUserId(result.getInt("UserId"));
                notificationViewModel.setNotificationId(result.getInt("NotificationId"));
                notificationViewModel.setDescription(result.getString("Description"));
                notificationViewModel.setStatus(result.getInt("Status"));
                notificationViewModel.setCreateDate(simpleDateFormat.format(result.getTimestamp("CreateDate")));
                notificationViewModel.setCreateUser(result.getInt("CreateUser"));
                notificationViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                notificationViewModel.setUserDisplay(result.getString("UserDisplay"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notificationViewModel;
    }

    public boolean insertData(Notifications t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Notifications(UserId, NotificationId, Description, Status, CreateUser) values (?,?,?,?,?)");
            statement.setInt(1, t.getUserId());
            statement.setInt(2, t.getNotificationId());
            statement.setString(3, t.getDescription());
            statement.setInt(4, 0);
            statement.setInt(5, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean readNotification(Integer id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Notifications set status=1 where Id=?");
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean unReadNotification(Integer id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Notifications set status=0 where Id=?");
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteNotification(Integer id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("delete from Notifications where Id=?");
            statement.setInt(1, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistNotification(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Notifications where Id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
