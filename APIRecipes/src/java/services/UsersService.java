/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.FolderNameConstant;
import dao.LoginDeviceDao;
import dao.UploadImageDao;
import dao.UsersDao;
import entity.LoginDevice;
import entity.Users;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import requests.LoginRequest;
import requests.UpdateInforUserRequest;
import requests.UpdateNameUserRequest;
import requests.UpdatePasswordRequest;
import requests.UserFilterRequest;
import requests.UserInputRequest;
import responses.LoginResponse;
import responses.UserListResponse;
import viewModel.UsersViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/users")
public class UsersService {

    UsersDao userDao = null;
    LoginDeviceDao loginDeviceDao = null;
    UploadImageDao uploadImageDao = null;
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
    LocalDateTime dateTimeNow = LocalDateTime.now();

    public UsersService() {
        userDao = new UsersDao();
        loginDeviceDao = new LoginDeviceDao();
        uploadImageDao = new UploadImageDao();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsersViewModel> getAllData(@Context UriInfo ui) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return userDao.getData(url);
    }

    @GET
    @Path("getListFollowOtherUser")
    @Produces(MediaType.APPLICATION_JSON)
    public UserListResponse getListFollowOtherUser(
            @Context UriInfo ui,
            @QueryParam("userId") int userId,
            @QueryParam("isFollowing") boolean isFollowing,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        List<UsersViewModel> usersViewModels = userDao.getListFollowOtherUser(url, userId, isFollowing, pageIndex, pageSize);
        int totalUsers = userDao.countFollowOtherUser(userId);
        int totalPage = totalUsers / pageSize;
        if (totalUsers % pageSize != 0) {
            totalPage += 1;
        }
        return new UserListResponse(totalPage, usersViewModels);
    }

    @GET
    @Path("getListFollowedByOthersUser")
    @Produces(MediaType.APPLICATION_JSON)
    public UserListResponse getListFollowedByOthersUser(
            @Context UriInfo ui,
            @QueryParam("followerId") int followerId,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize,
            @QueryParam("isFollowing") boolean isFollowing,
            @QueryParam("loginUserId") int loginUserId) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        List<UsersViewModel> usersViewModels = userDao.getListFollowedByOthersUser(url, followerId, isFollowing, pageIndex, pageSize, loginUserId);
        int totalUsers = userDao.countFollowedByOthersUser(followerId);
        int totalPage = totalUsers / pageSize;
        if (totalUsers % pageSize != 0) {
            totalPage += 1;
        }
        return new UserListResponse(totalPage, usersViewModels);
    }

    @POST
    @Path("filterData")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserListResponse filterData(@Context UriInfo ui, UserFilterRequest request) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        List<UsersViewModel> usersViewModels = userDao.getData(url, request);
        int totalUsers = userDao.countUserFilter(request);
        int totalPage = totalUsers / request.getPageSize();
        if (totalUsers % request.getPageSize() != 0) {
            totalPage += 1;
        }
        return new UserListResponse(totalPage, usersViewModels);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public UsersViewModel getUsersById(@Context UriInfo ui, @QueryParam("id") int id, @QueryParam("loginUserId") int loginUserId) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return userDao.getDataById(url, id, loginUserId);
    }

    @POST
    @Path("insert")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(final @Context ServletConfig config, UserInputRequest user) {
        String path = config.getServletContext().getRealPath("/images");

        if (user.getUserName().trim().length() == 0) {
            return "User userName is requied!";
        } else if (user.getDisplayName().trim().length() == 0) {
            return "User displayName is requied!";
        } else if (user.getSex() < 0) {
            return "User sex is must more or equal 0!";
        } else if (user.getPhoneNumber().trim().length() == 0) {
            return "User phoneNumber is requied!";
        } else if (user.getPassword().trim().length() == 0) {
            return "User password is requied!";
        } else if (user.getRole() < 0) {
            return "User role is must more or equal 0!";
        } else if (userDao.checkExistUserName(user.getUserName(), -1)) {
            return "User userName is exist in DB, choose another userName!";
        } else if (userDao.checkExistPhoneNumber(user.getPhoneNumber(), -1)) {
            return "User phoneNumber is exist in DB, choose another phoneNumber!";
        } else if (user.getEmail().trim().length() > 0 && userDao.checkExistEmail(user.getEmail(), -1)) {
            return "User email is exist in DB, choose another email!";
        }

        // convert image
        user.setAvatar("_");
        if (!user.getImageInput().isEmpty()) {
            String fileName = "user_" + dateTimeNow.format(formatDate);
            String imageBase64 = "";
            imageBase64 = user.getImageInput().stream().map((item) -> item).reduce(imageBase64, String::concat);
            user.setAvatar(uploadImageDao.uploadImage(imageBase64, path, FolderNameConstant.user, fileName));
        }
        if (userDao.insertData(user)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Path("updateUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatefinal(@Context ServletConfig config, UserInputRequest user) {
        String path = config.getServletContext().getRealPath("/images");

        if (user.getUserName().trim().length() == 0) {
            return "User userName is requied!";
        } else if (user.getDisplayName().trim().length() == 0) {
            return "User displayName is requied!";
        } else if (user.getSex() < 0) {
            return "User sex is must more or equal 0!";
        } else if (user.getPhoneNumber().trim().length() == 0) {
            return "User phoneNumber is requied!";
        } else if (user.getRole() < 0) {
            return "User role is must more or equal 0!";
        } else if (user.getStatus() < 0) {
            return "User status is must more or equal 0!";
        } else if (userDao.checkExistUserName(user.getUserName(), user.getId())) {
            return "User userName is exist in DB, choose another userName!";
        } else if (userDao.checkExistPhoneNumber(user.getPhoneNumber(), user.getId())) {
            return "User phoneNumber is exist in DB, choose another phoneNumber!";
        } else if (user.getEmail().trim().length() > 0 && userDao.checkExistEmail(user.getEmail(), user.getId())) {
            return "User email is exist in DB, choose another email!";
        } else if (userDao.getDataById("", user.getId(), 0).getId() <= 0) {
            return "User width id = " + user.getId() + " is not exist!";
        }

        // convert image
        user.setAvatar("_");
        if (!user.getImageInput().isEmpty()) {
            String fileName = "user_" + dateTimeNow.format(formatDate);
            String imageBase64 = "";
            imageBase64 = user.getImageInput().stream().map((item) -> item).reduce(imageBase64, String::concat);
            user.setAvatar(uploadImageDao.uploadImage(imageBase64, path, FolderNameConstant.user, fileName));
        }
        if (userDao.updateData(user)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Path("updateNameUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateName(@Context ServletConfig config, UpdateNameUserRequest request) {
        String path = config.getServletContext().getRealPath("/images");
        if (request.getUserName().trim().length() == 0) {
            return "User userName is requied!";
        } else if (request.getDisplayName().trim().length() == 0) {
            return "User displayName is requied!";
        } else if (userDao.checkExistUserName(request.getUserName(), request.getId())) {
            return "User userName is exist in DB, choose another userName!";
        } else if (userDao.getDataById("", request.getId(), 0).getId() <= 0) {
            return "User width id = " + request.getId() + " is not exist!";
        }
        Users user = new Users();
        user.setId(request.getId());
        user.setUserName(request.getUserName());
        user.setDescription(request.getDescription());
        user.setDisplayName(request.getDisplayName());

        // convert image
        user.setAvatar("_");
        if (!request.getImageInput().isEmpty()) {
            String fileName = "user_" + dateTimeNow.format(formatDate);
            String imageBase64 = "";
            imageBase64 = request.getImageInput().stream().map((item) -> item).reduce(imageBase64, String::concat);
            user.setAvatar(uploadImageDao.uploadImage(imageBase64, path, FolderNameConstant.user, fileName));
        }
        if (userDao.updateNameUser(user)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Path("updateInforUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateInforUser(@Context ServletConfig config, UpdateInforUserRequest request) {
        if (request.getSex() < 0) {
            return "User sex is must more or equal 0!";
        } else if (request.getPhoneNumber().trim().length() == 0) {
            return "User phoneNumber is requied!";
        } else if (userDao.checkExistPhoneNumber(request.getPhoneNumber(), request.getId())) {
            return "User phoneNumber is exist in DB, choose another phoneNumber!";
        } else if (request.getEmail().trim().length() > 0 && userDao.checkExistEmail(request.getEmail(), request.getId())) {
            return "User email is exist in DB, choose another email!";
        } else if (userDao.getDataById("", request.getId(), 0).getId() <= 0) {
            return "User width id = " + request.getId() + " is not exist!";
        }
        Users user = new Users();
        user.setId(request.getId());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSex(request.getSex());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setJob(request.getJob());
        if (userDao.updateInforUser(user)) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("id") int id, @QueryParam("userId") int userId) {
        if (!userDao.checkExistUser(id)) {
            return "User width id = " + id + " is not exist or deleted!";
        } else if (userDao.deleteData(id, userId)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Path("updatePassword")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updatePassword(UpdatePasswordRequest request) {
        if (request.getNewPassword().trim().length() == 0) {
            return "User newPassword is requied!";
        } else if (request.getOldPassword().trim().length() == 0) {
            return "User oldPassword is requied!";
        } else if (request.getId() <= 0) {
            return "User id is requied!";
        } else if (request.getUpdateId() <= 0) {
            return "User updateId is requied!";
        }
        Users user = userDao.getDataById("", request.getId(), 0);

        if (user.getId() <= 0) {
            return "User width id = " + request.getId() + " is not exist!";
        } else if (user.getStatus() != 0) {
            return "User width id = " + request.getId() + " is not locked!";
        }
        String oldPassword = userDao.getOldPassword(request.getId());
        if (!oldPassword.equals(userDao.encryptPassword(request.getOldPassword()))) {
            return "User oldPassword is falled!";
        } else if (oldPassword.equals(userDao.encryptPassword(request.getNewPassword()))) {
            return "Success!";
        } else if (userDao.updatePassword(request.getId(), request.getNewPassword(), request.getUpdateId())) {
            return "Success!";
        }
        return "Failed!";
    }

//    Status code
//            0 - Success
//            1 - User is blocked
//            2 - User is not exist
//            3 - DeviceName is not exist
    @POST
    @Path("loginUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse loginUser(@Context UriInfo ui, LoginRequest request) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        UsersViewModel userLogin = userDao.loginUser(url, request.getLoginUser(), request.getPassword(), false);
        String message = "Success!";
        int statusCode = 0;
        if (userLogin.getStatus() != 0) {
            statusCode = 1;
            message = "User is blocked!";
            userLogin = new UsersViewModel();
        } else if (userLogin.getId() <= 0) {
            statusCode = 2;
            message = "User is not exist!";
            userLogin = new UsersViewModel();
        } else if (request.getDeviceName().length() == 0) {
            statusCode = 3;
            message = "DeviceName is not exist!";
            userLogin = new UsersViewModel();
        } else {
            LoginDevice device = loginDeviceDao.getDataById(userLogin.getId(), request.getDeviceName());
            if (device.getId() > 0) {
                loginDeviceDao.reLogin(request.getDeviceName(), userLogin.getId());
            } else {
                loginDeviceDao.fristLogin(request.getDeviceName(), userLogin.getId());
            }
        }
        return new LoginResponse(
                message,
                statusCode,
                userLogin
        );
    }

//    Status code
//            0 - Success
//            1 - Admin is blocked
//            2 - Admin do not exist
//            3 - User does not have permission to login admin
    @POST
    @Path("loginAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse loginAdmin(@Context UriInfo ui, LoginRequest request) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        UsersViewModel userLogin = userDao.loginUser(url, request.getLoginUser(), request.getPassword(), true);
        String message = "Success!";
        int statusCode = 0;
        if (userLogin.getStatus() != 0) {
            statusCode = 1;
            message = "Admin is blocked!";
            userLogin = new UsersViewModel();
        } else if (userLogin.getId() <= 0) {
            statusCode = 2;
            message = "Admin is not exist!";
            userLogin = new UsersViewModel();
        } else if (userLogin.getRole() != 1) {
            statusCode = 3;
            message = "User does not have permission to login admin!";
            userLogin = new UsersViewModel();
        }
        return new LoginResponse(
                message,
                statusCode,
                userLogin
        );
    }

    @GET
    @Path("checkUserIsLogin")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String checkIsLogin(@QueryParam("id") int id, @QueryParam("deviceName") String deviceName) {
        LoginDevice device = loginDeviceDao.getDataById(id, deviceName);
        if (device.getId() > 0 && device.getStatus() == 0) {
            return "Success!";
        }
        return "Failed!";
    }

    @GET
    @Path("logoutUser")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String logoutUser(@QueryParam("userId") int userId, @QueryParam("deviceName") String deviceName) {
        LoginDevice device = loginDeviceDao.getDataById(userId, deviceName);
        if (device.getId() > 0 && device.getStatus() == 0) {
            if (loginDeviceDao.logout(userId, deviceName)) {
                return "Success!";
            }
        } else if (device.getId() <= 0) {
            return "User with Id = " + userId + " is not exist!";
        } else if (device.getStatus() == 1) {
            return "Success!";
        }
        return "Failed!";
    }
}
