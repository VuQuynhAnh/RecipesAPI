/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
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
import viewModel.UsersViewModel;

/**
 *
 * @author DELL
 */
public class UsersDao implements IService<Users, UsersViewModel, Integer> {

    Connection con = null;

    public UsersDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<UsersViewModel> getData() {
        List<UsersViewModel> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllCategory()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                UsersViewModel userViewModel = new UsersViewModel();
                userViewModel.setId(result.getInt("Id"));
                userViewModel.setUserName(result.getString("UserName"));
                userViewModel.setDisplayName(result.getString("DisplayName"));
                userViewModel.setSex(result.getInt("Sex"));
                userViewModel.setAddress(result.getString("Address"));
                userViewModel.setPhoneNumber(result.getString("PhoneNumber"));
                userViewModel.setEmail(result.getString("Email"));
                userViewModel.setJob(result.getString("Job"));
                userViewModel.setRole(result.getInt("Role"));
                userViewModel.setAvatar(result.getString("Avatar"));
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listCategoryViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    public List<UsersViewModel> getData(String keyword, boolean isGetAll) {
        List<UsersViewModel> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListCategory(?,?)}");
            if (keyword.equalsIgnoreCase("_")) {
                keyword = "";
            }
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                UsersViewModel userViewModel = new UsersViewModel();
                userViewModel.setId(result.getInt("Id"));
                userViewModel.setUserName(result.getString("UserName"));
                userViewModel.setDisplayName(result.getString("DisplayName"));
                userViewModel.setSex(result.getInt("Sex"));
                userViewModel.setAddress(result.getString("Address"));
                userViewModel.setPhoneNumber(result.getString("PhoneNumber"));
                userViewModel.setEmail(result.getString("Email"));
                userViewModel.setJob(result.getString("Job"));
                userViewModel.setRole(result.getInt("Role"));
                userViewModel.setAvatar(result.getString("Avatar"));
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listCategoryViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    @Override
    public UsersViewModel getDataById(Integer id) {
        UsersViewModel userViewModel = new UsersViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetCategoryById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                userViewModel.setId(result.getInt("Id"));
                userViewModel.setUserName(result.getString("UserName"));
                userViewModel.setDisplayName(result.getString("DisplayName"));
                userViewModel.setSex(result.getInt("Sex"));
                userViewModel.setAddress(result.getString("Address"));
                userViewModel.setPhoneNumber(result.getString("PhoneNumber"));
                userViewModel.setEmail(result.getString("Email"));
                userViewModel.setJob(result.getString("Job"));
                userViewModel.setRole(result.getInt("Role"));
                userViewModel.setAvatar(result.getString("Avatar"));
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userViewModel;
    }

    @Override
    public boolean insertData(Users t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Users(UserName, Status, CreateDate, CreateUser) values (?,?,?,?)");
            statement.setString(1, t.getUserName());
            statement.setInt(2, 0);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(Users t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Users set UserName=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getUserName());
            statement.setInt(2, t.getStatus());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getUpdateUser());
            statement.setInt(5, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Users set Status = 1, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistUser(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Users where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
