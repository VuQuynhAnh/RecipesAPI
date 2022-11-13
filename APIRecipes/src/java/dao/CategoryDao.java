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
public class CategoryDao {

    Connection con = null;

    public CategoryDao() {
        con = GetConnection.getConnect();
    }

    public List<CategoryViewModel> getData(String serverUrl) {
        List<CategoryViewModel> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllCategory()}");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CategoryViewModel categoryViewModel = new CategoryViewModel();
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setImage(serverUrl + result.getString("Image"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                categoryViewModel.setTotalRecipes(result.getInt("TotalRecipes"));
                listCategoryViewModels.add(categoryViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    public List<CategoryViewModel> getData(String serverUrl, String keyword, boolean isGetAll, boolean sortIdDESC, boolean sortNameASC, boolean sortTotalRecipeDESC, int pageIndex, int pageSize) {
        List<CategoryViewModel> listCategoryViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListCategory(?,?,?,?,?,?,?)}");
            keyword = keyword.equalsIgnoreCase("_") ? "" : keyword;
            pageIndex = pageIndex > 0 ? pageIndex : 1;
            pageSize = pageSize > 0 ? pageSize : 1;
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            statement.setBoolean(3, sortIdDESC);
            statement.setBoolean(4, sortNameASC);
            statement.setBoolean(5, sortTotalRecipeDESC);
            statement.setInt(6, pageIndex);
            statement.setInt(7, pageSize);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                CategoryViewModel categoryViewModel = new CategoryViewModel();
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setImage(serverUrl + result.getString("Image"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                categoryViewModel.setTotalRecipes(result.getInt("TotalRecipes"));
                listCategoryViewModels.add(categoryViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryViewModels;
    }

    public int countCategory(String keyword, boolean isGetAll) {
        int totalCategory = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountCategory(?,?)}");
            keyword = keyword.equalsIgnoreCase("_") ? "" : keyword;
            statement.setString(1, "%" + keyword + "%");
            statement.setBoolean(2, isGetAll);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                totalCategory = result.getInt("TotalCategory");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCategory;
    }

    public CategoryViewModel getDataById(String serverUrl, Integer id) {
        CategoryViewModel categoryViewModel = new CategoryViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetCategoryById(?)}");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                categoryViewModel.setId(result.getInt("Id"));
                categoryViewModel.setName(result.getString("Name"));
                categoryViewModel.setImage(serverUrl + result.getString("Image"));
                categoryViewModel.setStatus(result.getInt("Status"));
                categoryViewModel.setCreateDate(result.getDate("CreateDate"));
                categoryViewModel.setCreateUser(result.getInt("CreateUser"));
                categoryViewModel.setUpdateDate(result.getDate("UpdateDate"));
                categoryViewModel.setUpdateUser(result.getInt("UpdateUser"));
                categoryViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                categoryViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                categoryViewModel.setTotalRecipes(result.getInt("TotalRecipes"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoryViewModel;
    }

    public boolean insertData(Category t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("insert into Category(Name, Image, Status, CreateDate, CreateUser) values (?,?,?,?,?)");
            statement.setString(1, t.getName());
            statement.setString(2, t.getImage());
            statement.setInt(3, 0);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setInt(5, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateData(Category t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Category set Name=?, Image=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, t.getName());
            statement.setString(2, t.getImage());
            statement.setInt(3, t.getStatus());
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setInt(5, t.getUpdateUser());
            statement.setInt(6, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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
            statement = con.prepareCall("select * from Category where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }
}
