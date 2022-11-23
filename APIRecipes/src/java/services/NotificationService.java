/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.NotificationDao;
import dao.UsersDao;
import entity.Notifications;
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
import responses.NotificationListResponse;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/notification")
public class NotificationService {

    NotificationDao notificationDao = null;
    UsersDao usersDao = null;

    public NotificationService() {
        notificationDao = new NotificationDao();
        usersDao = new UsersDao();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NotificationViewModel> getCategories() {
        return notificationDao.getData();
    }

    @GET
    @Path("getNotificationByUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificationListResponse getNotificationByUserId(
            @QueryParam("userId") int userId,
            @QueryParam("status") int status,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize
    ) {
        int totalNotification = notificationDao.countNotificationByUserId(userId, status);
        int totalPage = totalNotification / pageSize;
        if (totalNotification % pageSize != 0) {
            totalPage += 1;
        }
        List<NotificationViewModel> notificationViewModels = notificationDao.getNotificationByUserId(userId, status, pageIndex, pageSize);
        return new NotificationListResponse(totalPage, notificationViewModels);
    }

    @GET
    @Path("getNotificationByCreateUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificationListResponse getNotificationByCreateUserId(
            @QueryParam("createUserId") int createUserId,
            @QueryParam("status") int status,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize) {
        int totalNotification = notificationDao.countNotificationByCreateUserId(createUserId, status);
        int totalPage = totalNotification / pageSize;
        if (totalNotification % pageSize != 0) {
            totalPage += 1;
        }
        List<NotificationViewModel> notificationViewModels = notificationDao.getNotificationByCreateUserId(createUserId, status, pageIndex, pageSize);
        return new NotificationListResponse(totalPage, notificationViewModels);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public NotificationViewModel getNotificationById(@QueryParam("id") int id) {
        return notificationDao.getDataById(id);
    }

    @PUT
    @Path("readNotification")
    @Produces(MediaType.TEXT_PLAIN)
    public String readNotification(@QueryParam("id") int id) {
        if (!notificationDao.checkExistNotification(id)) {
            return "Notification width id = " + id + " is not exist or deleted!";
        } else if (notificationDao.readNotification(id)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Path("unReadNotification")
    @Produces(MediaType.TEXT_PLAIN)
    public String unReadNotification(@QueryParam("id") int id) {
        if (!notificationDao.checkExistNotification(id)) {
            return "Notification width id = " + id + " is not exist or deleted!";
        } else if (notificationDao.unReadNotification(id)) {
            return "Success!";
        }
        return "Failed!";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Notifications notification) {
        // validate
        if (notification.getDescription().trim().length() == 0) {
            return "Notification description is requied!";
        } else if (notificationDao.insertData(notification.getUserId(), notification.getNotificationId(), notification.getDescription(), notification.getCreateUser())) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("id") int id) {
        if (!notificationDao.checkExistNotification(id)) {
            return "Notification width id = " + id + " is not exist or deleted!";
        } else if (notificationDao.deleteNotification(id)) {
            return "Success!";
        }
        return "Failed!";
    }

}
