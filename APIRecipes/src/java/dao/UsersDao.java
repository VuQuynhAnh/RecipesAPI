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
                userViewModel.setAvatar(result.getString("Avatar"));
                userViewModel.setDescription(result.getString("Description"));
                userViewModel.setStatus(result.getInt("Status"));
                userViewModel.setCreateDate(result.getDate("CreateDate"));
                userViewModel.setCreateUser(result.getInt("CreateUser"));
                userViewModel.setUpdateDate(result.getDate("UpdateDate"));
                userViewModel.setUpdateUser(result.getInt("UpdateUser"));
                userViewModel.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                userViewModel.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    public List<UsersViewModel> getData(String keyword, int sex, int role, int status) {
        List<UsersViewModel> listUsersViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call FilterListUsers(?,?,?,?)}");
            if (keyword.equalsIgnoreCase("_")) {
                keyword = "";
            }
            statement.setString(1, keyword);
            statement.setInt(2, sex);
            statement.setInt(3, role);
            statement.setInt(4, status);

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
                listUsersViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUsersViewModels;
    }

    public List<UsersViewModel> getListFollowOtherUser(int userId) {
        List<UsersViewModel> listUserViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetListFollowOtherUser(?)}");
            statement.setInt(1, userId);
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
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    public List<UsersViewModel> getListFollowedByOthersUser(int followerId) {
        List<UsersViewModel> listUserViewModels = new ArrayList<>();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetListFollowedByOthersUser(?)}");
            statement.setInt(1, followerId);
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
                listUserViewModels.add(userViewModel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUserViewModels;
    }

    @Override
    public UsersViewModel getDataById(Integer id) {
        UsersViewModel userViewModel = new UsersViewModel();
        PreparedStatement statement;
        try {
            statement = con.prepareCall("{call GetUserById(?)}");
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

    @Override
    public boolean updateData(Users t) {
        PreparedStatement statement;
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

    public UsersViewModel loginUser(String loginUser, String password, boolean isLoginAdmin) {
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
                        user.setAvatar(result.getString("Avatar"));
                        user.setDescription(result.getString("Description"));
                        user.setCreateDate(result.getDate("CreateDate"));
                        user.setCreateUser(result.getInt("CreateUser"));
                        user.setUpdateDate(result.getDate("UpdateDate"));
                        user.setUpdateUser(result.getInt("UpdateUser"));
                        user.setCreateUserDisplay(result.getString("CreateUserDisplay"));
                        user.setUpdateUserDisplay(result.getString("UpdateUserDisplay"));
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
            statement = con.prepareCall("{call GetUserById(?)}");
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
