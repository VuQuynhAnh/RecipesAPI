/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
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
import viewModel.CategoryViewModel;

/**
 *
 * @author DELL
 */
public class CategoryDao implements IService<Category, CategoryViewModel, Integer> {

    Connection con = null;

    public CategoryDao() {
        con = GetConnection.getConnect();
    }

    @Override
    public List<CategoryViewModel> getData() {
        List<CategoryViewModel> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllCategory()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CategoryViewModel categoryViewModel = new CategoryViewModel();
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listCategoryViewModels.add(categoryViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    public List<CategoryViewModel> getData(String keyword, boolean isGetAll) {
        List<CategoryViewModel> listCategoryViewModels = new ArrayList<>();
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
                CategoryViewModel categoryViewModel = new CategoryViewModel();
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listCategoryViewModels.add(categoryViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    @Override
    public CategoryViewModel getDataById(Integer id) {
        CategoryViewModel categoryViewModel = new CategoryViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetCategoryById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryViewModel;
    }

    @Override
    public boolean insertData(Category t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Category(Name, Status, CreateDate, CreateUser) values (?,?,?,?)");
            statement.setString(1, t.getName());
            statement.setInt(2, 0);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateData(Category t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Category set Name=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getName());
            statement.setInt(2, t.getStatus());
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setInt(4, t.getUpdateUser());
            statement.setInt(5, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Category set Status = 1, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setInt(2, userId);
            statement.setInt(3, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistCategory(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Category where Id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
