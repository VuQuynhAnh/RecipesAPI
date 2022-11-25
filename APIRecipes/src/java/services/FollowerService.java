/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.NotificationTypeIdConstant;
import dao.FollowerDao;
import dao.NotificationDao;
import dao.NotificationTypeDao;
import dao.RecipesDao;
import dao.UsersDao;
import entity.Followers;
import entity.NotificationType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import responses.SaveOutputResponse;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/followers")
public class FollowerService {

    RecipesDao recipesDao = null;
    UsersDao usersDao = null;
    FollowerDao followerDao = null;
    NotificationTypeDao notificationTypeDao = null;
    NotificationDao notificationDao = null;
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public FollowerService() {
        recipesDao = new RecipesDao();
        usersDao = new UsersDao();
        followerDao = new FollowerDao();
        notificationTypeDao = new NotificationTypeDao();
        notificationDao = new NotificationDao();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SaveOutputResponse followUser(Followers follower) {
        if (follower.getUserId() <= 0 || !usersDao.checkExistUser(follower.getUserId())) {
            return new SaveOutputResponse("User with Id = " + follower.getUserId() + " is not exist or deleted!");
        } else if (follower.getFollowerId() <= 0 || !usersDao.checkExistUser(follower.getFollowerId())) {
            return new SaveOutputResponse("User with Id = " + follower.getFollowerId() + " is not exist or deleted!");
        }
        Followers oldRecod = followerDao.getFollowerItem(follower.getUserId(), follower.getFollowerId());
        List<NotificationViewModel> notificationViewModels = new ArrayList<>();
        if (oldRecod.getStatus() <= 0 && oldRecod.getFollowerId() <= 0 && oldRecod.getUserId() <= 0) {
            if (followerDao.insertData(follower.getUserId(), follower.getFollowerId())) {
                notificationViewModels.add(sendNotificationFollow(follower.getUserId(), follower.getFollowerId()));
                return new SaveOutputResponse("Success!", notificationViewModels);
            }
        } else if (followerDao.reFollower(follower.getUserId(), follower.getFollowerId())) {
            notificationViewModels.add(sendNotificationFollow(follower.getUserId(), follower.getFollowerId()));
            return new SaveOutputResponse("Success!", notificationViewModels);
        }
        return new SaveOutputResponse("Failed!");
    }

    @DELETE
    @Path("unfollow")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("userId") int userId, @QueryParam("followerId") int followerId) {
        if (!followerDao.checkExistFollowerItem(userId, followerId)) {
            return "This item is not exist or delete!";
        } else if (followerDao.deleteData(userId, followerId)) {
            return "Success!";
        }
        return "Failed!";
    }

    private NotificationViewModel sendNotificationFollow(int userId, int followId) {
        int notificationTypeId = NotificationTypeIdConstant.followUser;
        NotificationType notificationType = notificationTypeDao.getDataById(notificationTypeId);
        String content = notificationType.getDescription();
        String typeName = notificationType.getName();

        if (content.contains("[userDisplay]")) {
            content = content.replace("[userDisplay]", usersDao.getDataById("", userId, 0).getDisplayName());
        }
        String createTime = simpleDateFormat.format(new Timestamp(System.currentTimeMillis()));
        if (notificationDao.insertData(followId, notificationTypeId, content, 0)) {
            return new NotificationViewModel(typeName, content, 0, createTime, userId);
        }
        return new NotificationViewModel();
    }

}
