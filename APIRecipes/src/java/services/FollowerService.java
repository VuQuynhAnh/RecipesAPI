/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.FollowerDao;
import dao.RecipesDao;
import dao.UsersDao;
import entity.Followers;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

    public FollowerService() {
        recipesDao = new RecipesDao();
        usersDao = new UsersDao();
        followerDao = new FollowerDao();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String followUser(Followers follower) {
        if (follower.getUserId() <= 0 || !usersDao.checkExistUser(follower.getUserId())) {
            return "User with Id = " + follower.getUserId() + " is not exist or deleted!";
        } else if (follower.getFollowerId() <= 0 || !usersDao.checkExistUser(follower.getFollowerId())) {
            return "User with Id = " + follower.getFollowerId() + " is not exist or deleted!";
        }
        Followers oldRecod = followerDao.getFollowerItem(follower.getUserId(), follower.getFollowerId());
        if (oldRecod.getStatus() <= 0 && oldRecod.getFollowerId() <= 0 && oldRecod.getUserId() <= 0) {
            if (followerDao.insertData(follower.getUserId(), follower.getFollowerId())) {
                return "Success!";
            }
        } else if (followerDao.reFollower(follower.getUserId(), follower.getFollowerId())) {
            return "Success!";
        }
        return "Failed!";
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

}
