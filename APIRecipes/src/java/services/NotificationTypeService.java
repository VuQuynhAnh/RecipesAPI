/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.NotificationTypeDao;
import dao.UsersDao;
import entity.NotificationType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import viewModel.NotificationTypeViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/notificationType")
public class NotificationTypeService {

    NotificationTypeDao notificationDao = null;
    UsersDao usersDao = null;
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
    LocalDateTime dateTimeNow = LocalDateTime.now();

    public NotificationTypeService() {
        notificationDao = new NotificationTypeDao();
        usersDao = new UsersDao();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NotificationTypeViewModel> getCategories() {
        return notificationDao.getData();
    }

    @GET
    @Path("getLists")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NotificationTypeViewModel> getCategories(@QueryParam("keyword") String keyword, @QueryParam("isGetAll") boolean isGetAll) {
        return notificationDao.getData(keyword, isGetAll);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificationTypeViewModel getCategoryById(@QueryParam("id") int id) {
        return notificationDao.getDataById(id);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(NotificationType type) {

        // validate
        if (type.getName().trim().length() == 0) {
            return "NotificationType name is requied!";
        } else if (!usersDao.checkExistUser(type.getCreateUser())) {
            return "NotificationType createUser with id = " + type.getCreateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(type.getCreateUser())) {
            return "NotificationType createUser with id = " + type.getCreateUser() + " is not a admin!";
        }
        if (notificationDao.insertData(type)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(NotificationType type) {
        // validate
        if (type.getName().trim().length() == 0) {
            return "NotificationType name is requied!";
        } else if (notificationDao.getDataById(type.getId()).getId() <= 0) {
            return "NotificationType width id = " + type.getId() + " is not exist or deleted!";
        } else if (!usersDao.checkExistUser(type.getUpdateUser())) {
            return "NotificationType updateUser with id = " + type.getUpdateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(type.getUpdateUser())) {
            return "NotificationType updateUser with id = " + type.getUpdateUser() + " is not a admin!";
        }
        if (notificationDao.updateData(type)) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("id") int id, @QueryParam("updateUser") int deleteId) {
        if (!notificationDao.checkExistNotificationType(id)) {
            return "NotificationType width id = " + id + " is not exist or deleted!";
        } else if (!usersDao.checkExistUser(deleteId)) {
            return "NotificationType updateUser with id = " + deleteId + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(deleteId)) {
            return "NotificationType updateUser with id = " + deleteId + " is not a admin!";
        } else if (notificationDao.deleteData(id, deleteId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
