/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.FolderNameConstant;
import dao.CategoryDao;
import dao.NotificationDao;
import dao.UploadImageDao;
import dao.UsersDao;
import entity.Category;
import entity.Notifications;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.Stateless;
import javax.management.Notification;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import viewModel.CategoryViewModel;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/notification")
public class NotificationService {

    NotificationDao categoryDao = null;
    UsersDao usersDao = null;

    public NotificationService() {
        categoryDao = new NotificationDao();
        usersDao = new UsersDao();
    }

//    @GET
//    @Path("getAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<NotificationViewModel> getCategories() {
//        return categoryDao.getData();
//    }
//
//    @GET
//    @Path("getLists")
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<NotificationViewModel> getCategories(
//            @QueryParam("keyword") String keyword,
//            @QueryParam("isGetAll") boolean isGetAll,
//            @QueryParam("sortIdDESC") boolean sortIdDESC,
//            @QueryParam("sortNameASC") boolean sortNameASC,
//            @QueryParam("sortTotalRecipeDESC") boolean sortTotalRecipeDESC,
//            @QueryParam("pageIndex") int pageIndex,
//            @QueryParam("pageSize") int pageSize
//    ) {
//        return categoryDao.getData(keyword, isGetAll, sortIdDESC, sortNameASC, sortTotalRecipeDESC, pageIndex, pageSize);
//    }
//
//    @GET
//    @Path("detail")
//    @Produces(MediaType.APPLICATION_JSON)
//    public NotificationViewModel getNotificationById(@QueryParam("id") int id) {
//        return categoryDao.getDataById(id);
//    }
//
//    @POST
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public String insert(Notifications noti) {
//
//        // validate
//        if (noti.getDescription().trim().length() == 0) {
//            return "Notification description is requied!";
//        } else if (!usersDao.checkExistUser(noti.getCreateUser())) {
//            return "Notification createUser with id = " + noti.getCreateUser() + " is not exist or deleted!";
//        } else if (!usersDao.checkExistUser(noti.getNotificationId())) {
//            return "Notification notificationId with id = " + noti.getCreateUser() + " is not exist or deleted!";
//        } else if (categoryDao.insertData(noti)) {
//            return "Success!";
//        }
//        return "Failed!";
//    }
//
//    @DELETE
//    @Path("delete")
//    @Produces(MediaType.TEXT_PLAIN)
//    @Consumes(MediaType.TEXT_PLAIN)
//    public String delete(@QueryParam("id") int id, @QueryParam("updateUser") int deleteId) {
//        if (!categoryDao.checkExistCategory(id)) {
//            return "Category width id = " + id + " is not exist or deleted!";
//        } else if (!usersDao.checkExistUser(deleteId)) {
//            return "Category updateUser with id = " + deleteId + " is not exist or deleted!";
//        } else if (!usersDao.isUserAdmin(deleteId)) {
//            return "Category updateUser with id = " + deleteId + " is not a admin!";
//        } else if (categoryDao.deleteData(id, deleteId)) {
//            return "Success!";
//        }
//        return "Failed!";
//    }

}
