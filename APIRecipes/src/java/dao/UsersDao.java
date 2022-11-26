/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Users;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import requests.UserFilterRequest;
import viewModel.UsersViewModel;

/**
 *
 * @author DELL
 */
public class UsersDao {

    Connection con = null;

    public UsersDao() {
        con = GetConnection.getConnect();
    }

    public List<UsersViewModel> getData(String serverUrl) {
        List<UsersViewModel> listUserViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetAllUsers()}");
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
                userViewModel.setAvatar("");
                if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                    userViewModel.setAvatar(serverUrl + result.getString("Avatar"));
                }
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                userViewModel.setTotalRecipe(result.getInt("TotalRecipe"));
                userViewModel.setTotalViews(result.getInt("TotalViews"));
                userViewModel.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                userViewModel.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                userViewModel.setIsFollowerUser(false);
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    public List<UsersViewModel> getData(String serverUrl, UserFilterRequest request) {
        List<UsersViewModel> listUsersViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListUsers(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            if (request.getKeyword().equalsIgnoreCase("_")) {
                request.setKeyword("");
            }
            int pageIndex = request.getPageIndex() > 0 ? request.getPageIndex() : 1;
            int pageSize = request.getPageSize() > 0 ? request.getPageSize() : 1;
            statement.setString(1, request.getKeyword());
            statement.setString(2, request.getEmail());
            statement.setString(3, request.getPhoneNumber());
            statement.setString(4, request.getDisplayName());
            statement.setString(5, request.getUserName());
            statement.setInt(6, request.getSex());
            statement.setInt(7, request.getRole());
            statement.setInt(8, request.getStatus());
            statement.setBoolean(9, request.isSortByIdDESC());
            statement.setBoolean(10, request.isSortByTotalRecipeDESC());
            statement.setBoolean(11, request.isSortByTotalFollowOtherUserDESC());
            statement.setBoolean(12, request.isSortByTotalFollowedByOthersUserDESC());
            statement.setBoolean(13, request.isSortByTotalViewsDESC());
            statement.setInt(14, pageIndex);
            statement.setInt(15, pageSize);
            statement.setInt(16, request.getLoginUserId());
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
                userViewModel.setAvatar("");
                if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                    userViewModel.setAvatar(serverUrl + result.getString("Avatar"));
                }
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                userViewModel.setTotalRecipe(result.getInt("TotalRecipe"));
                userViewModel.setTotalViews(result.getInt("TotalViews"));
                userViewModel.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                userViewModel.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                userViewModel.setIsFollowerUser(result.getInt("CheckFollow") > 0);
                listUsersViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsersViewModels;
    }

    public int countUserFilter(UserFilterRequest request) {
        int totalUsers = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountUsers(?,?,?,?,?,?,?,?)}");
            if (request.getKeyword().equalsIgnoreCase("_")) {
                request.setKeyword("");
            }
            statement.setString(1, request.getKeyword());
            statement.setString(2, request.getEmail());
            statement.setString(3, request.getPhoneNumber());
            statement.setString(4, request.getDisplayName());
            statement.setString(5, request.getUserName());
            statement.setInt(6, request.getSex());
            statement.setInt(7, request.getRole());
            statement.setInt(8, request.getStatus());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                totalUsers = result.getInt("TotalUser");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalUsers;
    }

    public int countFollowOtherUser(int userId) {
        int totalCategory = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountFollowOtherUser(?)}");
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                totalCategory = result.getInt("TotalUser");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCategory;
    }

    public int countFollowedByOthersUser(int followerId) {
        int totalCategory = 0;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call CountFollowedByOthersUser(?)}");
            statement.setInt(1, followerId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                totalCategory = result.getInt("TotalUser");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalCategory;
    }

    public List<UsersViewModel> getListFollowOtherUser(String serverUrl, int userId, boolean isFollowing, int pageIndex, int pageSize) {
        List<UsersViewModel> listUserViewModels = new ArrayList<>();
        pageIndex = pageIndex > 0 ? pageIndex : 1;
        pageSize = pageSize > 0 ? pageSize : 1;
        int status = isFollowing ? 0 : 1;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetListFollowOtherUser(?,?,?,?)}");
            statement.setInt(1, userId);
            statement.setInt(2, status);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
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
                userViewModel.setAvatar("");
                if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                    userViewModel.setAvatar(serverUrl + result.getString("Avatar"));
                }
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                userViewModel.setTotalRecipe(result.getInt("TotalRecipe"));
                userViewModel.setTotalViews(result.getInt("TotalViews"));
                userViewModel.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                userViewModel.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                userViewModel.setIsFollowerUser(true);
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    public List<UsersViewModel> getListFollowedByOthersUser(String serverUrl, int followerId, boolean isFollowing, int pageIndex, int pageSize, int loginUserId) {
        List<UsersViewModel> listUserViewModels = new ArrayList<>();
        pageIndex = pageIndex > 0 ? pageIndex : 1;
        pageSize = pageSize > 0 ? pageSize : 1;
        int status = isFollowing ? 0 : 1;
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetListFollowedByOthersUser(?,?,?,?,?)}");
            statement.setInt(1, followerId);
            statement.setInt(2, status);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            statement.setInt(5, loginUserId);
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
                userViewModel.setAvatar("");
                if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                    userViewModel.setAvatar(serverUrl + result.getString("Avatar"));
                }
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                userViewModel.setTotalRecipe(result.getInt("TotalRecipe"));
                userViewModel.setTotalViews(result.getInt("TotalViews"));
                userViewModel.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                userViewModel.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                userViewModel.setIsFollowerUser(result.getInt("CheckFollow") > 0);
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    public UsersViewModel getDataById(String serverUrl, Integer id, Integer loginUserId) {
        UsersViewModel userViewModel = new UsersViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetUserById(?,?)}");
            statement.setInt(1, id);
            statement.setInt(2, loginUserId);
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
                userViewModel.setAvatar("");
                if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                    userViewModel.setAvatar(serverUrl + result.getString("Avatar"));
                }
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                userViewModel.setTotalRecipe(result.getInt("TotalRecipe"));
                userViewModel.setTotalViews(result.getInt("TotalViews"));
                userViewModel.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                userViewModel.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                userViewModel.setIsFollowerUser(result.getInt("CheckFollow") > 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userViewModel;
    }

    public boolean insertData(Users t) {
        PreparedStatement statement;
        if (t.getAvatar().length() == 1) {
            t.setAvatar("");
        }
        try {
            statement = con.prepareCall("insert into Users(UserName, DisplayName, Sex, Address, PhoneNumber, Password, Email, Job, Role, Avatar, Description, Status, CreateDate, CreateUser) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, t.getUserName());
            statement.setString(2, t.getDisplayName());
            statement.setInt(3, t.getSex());
            statement.setString(4, t.getAddress());
            statement.setString(5, t.getPhoneNumber());
            statement.setString(6, encryptPassword(t.getPassword()));
            statement.setString(7, t.getEmail());
            statement.setString(8, t.getJob());
            statement.setInt(9, t.getRole());
            statement.setString(10, t.getAvatar());
            statement.setString(11, t.getDescription());
            statement.setInt(12, 0);
            statement.setDate(13, Date.valueOf(LocalDate.now()));
            statement.setInt(14, t.getCreateUser());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateData(Users t) {
        PreparedStatement statement;
        if (t.getAvatar().length() > 1) {
            try {
                statement = con.prepareCall("update Users set UserName=?, DisplayName=?, Sex=?, Address=?, PhoneNumber=?, Email=?, Job=?, Role=?, Avatar=?, Description=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setString(1, t.getUserName());
                statement.setString(2, t.getDisplayName());
                statement.setInt(3, t.getSex());
                statement.setString(4, t.getAddress());
                statement.setString(5, t.getPhoneNumber());
                statement.setString(6, t.getEmail());
                statement.setString(7, t.getJob());
                statement.setInt(8, t.getRole());
                statement.setString(9, t.getAvatar());
                statement.setString(10, t.getDescription());
                statement.setInt(11, t.getStatus());
                statement.setDate(12, Date.valueOf(LocalDate.now()));
                statement.setInt(13, t.getUpdateUser());
                statement.setInt(14, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                statement = con.prepareCall("update Users set UserName=?, DisplayName=?, Sex=?, Address=?, PhoneNumber=?, Email=?, Job=?, Role=?, Description=?, Status=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setString(1, t.getUserName());
                statement.setString(2, t.getDisplayName());
                statement.setInt(3, t.getSex());
                statement.setString(4, t.getAddress());
                statement.setString(5, t.getPhoneNumber());
                statement.setString(6, t.getEmail());
                statement.setString(7, t.getJob());
                statement.setInt(8, t.getRole());
                statement.setString(9, t.getDescription());
                statement.setInt(10, t.getStatus());
                statement.setDate(11, Date.valueOf(LocalDate.now()));
                statement.setInt(12, t.getUpdateUser());
                statement.setInt(13, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean updateNameUser(Users t) {
        PreparedStatement statement;
        if (t.getAvatar().length() > 1) {
            try {
                statement = con.prepareCall("update Users set UserName=?, DisplayName=?, Avatar=?, Description=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setString(1, t.getUserName());
                statement.setString(2, t.getDisplayName());
                statement.setString(3, t.getAvatar());
                statement.setString(4, t.getDescription());
                statement.setDate(5, Date.valueOf(LocalDate.now()));
                statement.setInt(6, t.getUpdateUser());
                statement.setInt(7, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                statement = con.prepareCall("update Users set UserName=?, DisplayName=?, Description=?, UpdateDate=?, UpdateUser=? where Id=?");
                statement.setString(1, t.getUserName());
                statement.setString(2, t.getDisplayName());
                statement.setString(3, t.getDescription());
                statement.setDate(4, Date.valueOf(LocalDate.now()));
                statement.setInt(5, t.getUpdateUser());
                statement.setInt(6, t.getId());
                if (statement.executeUpdate() > 0) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean updateInforUser(Users t) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Users set Sex=?, Address=?, PhoneNumber=?, Email=?, Job=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setInt(1, t.getSex());
            statement.setString(2, t.getAddress());
            statement.setString(3, t.getPhoneNumber());
            statement.setString(4, t.getEmail());
            statement.setString(5, t.getJob());
            statement.setDate(6, Date.valueOf(LocalDate.now()));
            statement.setInt(7, t.getId());
            statement.setInt(8, t.getId());
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

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
    
     public boolean reActiveData(Integer id, int userId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Users set Status = 0, UpdateDate=?, UpdateUser=? where Id=?");
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

    public boolean updatePassword(int id, String newPassword, int updateId) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("update Users set Password=?, UpdateDate=?, UpdateUser=? where Id=?");
            statement.setString(1, encryptPassword(newPassword));
            statement.setDate(2, Date.valueOf(LocalDate.now()));
            statement.setInt(3, updateId);
            statement.setInt(4, id);
            if (statement.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public UsersViewModel loginUser(String serverUrl, String loginUser, String password, boolean isLoginAdmin) {
        UsersViewModel user = new UsersViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call LoginUser(?,?)}");
            statement.setString(1, loginUser);
            statement.setString(2, encryptPassword(password));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                user.setId(result.getInt("Id"));
                user.setStatus(result.getInt("Status"));
                if (result.getInt("Status") == 0) {
                    user.setRole(result.getInt("Role"));
                    if (!isLoginAdmin || result.getInt("Role") == 1) {
                        user.setUserName(result.getString("UserName"));
                        user.setDisplayName(result.getString("DisplayName"));
                        user.setSex(result.getInt("Sex"));
                        user.setAddress(result.getString("Address"));
                        user.setPhoneNumber(result.getString("PhoneNumber"));
                        user.setEmail(result.getString("Email"));
                        user.setJob(result.getString("Job"));
                        user.setAvatar("");
                        if (result.getString("Avatar") != null && result.getString("Avatar").length() > 1) {
                            user.setAvatar(serverUrl + result.getString("Avatar"));
                        }
                        user.setDescription(result.getString("Description"));
                        user.setCreateDate(result.getDate("CreateDate"));
                        user.setCreateUser(result.getInt("CreateUser"));
                        user.setUpdateDate(result.getDate("UpdateDate"));
                        user.setUpdateUser(result.getInt("UpdateUser"));
                        user.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                        user.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                        user.setTotalRecipe(result.getInt("TotalRecipe"));
                        user.setTotalFollowOtherUser(result.getInt("TotalFollowOtherUser"));
                        user.setTotalFollowedByOthersUser(result.getInt("TotalFollowedByOthersUser"));
                        user.setIsFollowerUser(false);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
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

    public boolean isUserAdmin(int id) {
        if (id <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select * from Users where Id=? and Status = 0");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                if (result.getInt("Role") == 1) {
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean checkExistUserName(String userName, int id) {
        if (userName.length() <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            if (id == -1) {
                statement = con.prepareCall("select * from Users where UserName=? and Status = 0");
                statement.setString(1, userName);
            } else {
                statement = con.prepareCall("select * from Users where UserName=? and Status = 0 and id !=?");
                statement.setString(1, userName);
                statement.setInt(2, id);
            }
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean checkExistPhoneNumber(String phoneNumber, int id) {
        if (phoneNumber.length() <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            if (id == -1) {
                statement = con.prepareCall("select * from Users where PhoneNumber=? and Status = 0");
                statement.setString(1, phoneNumber);
            } else {
                statement = con.prepareCall("select * from Users where PhoneNumber=? and Status = 0 and id !=?");
                statement.setString(1, phoneNumber);
                statement.setInt(2, id);
            }
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean checkExistEmail(String email, int id) {
        if (email.length() <= 0) {
            return false;
        }
        PreparedStatement statement;
        try {
            if (id == -1) {
                statement = con.prepareCall("select * from Users where Email=? and Status = 0");
                statement.setString(1, email);
            } else {
                statement = con.prepareCall("select * from Users where Email=? and Status = 0 and id !=?");
                statement.setString(1, email);
                statement.setInt(2, id);
            }
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    public String getOldPassword(Integer id) {
        PreparedStatement statement;
        try {
            statement = con.prepareCall("select Password from Users where Id = ?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("Password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public String encryptPassword(String password) {
        String encryptedpassword = null;
        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            return encryptedpassword;
        }
        return encryptedpassword;
    }
}
