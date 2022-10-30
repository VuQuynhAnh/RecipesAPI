/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.RatingDao;
import dao.RecipesDao;
import dao.UsersDao;
import entity.Rating;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/rating")
public class RatingService {

    RatingDao ratingDao = null;
    RecipesDao recipesDao = null;
    UsersDao usersDao = null;

    public RatingService() {
        ratingDao = new RatingDao();
        recipesDao = new RecipesDao();
        usersDao = new UsersDao();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Rating rating) {
        if (rating.getRating() < 0 || rating.getRating() > 5) {
            return "Rating min is 0 and max is 5!";
        } else if (!recipesDao.checkExistRecipe(rating.getRecipeId())) {
            return "Recipe with id = " + rating.getRecipeId() + " is not exist!";
        } else if (!usersDao.checkExistUser(rating.getUserId())) {
            return "User with id = " + rating.getUserId() + " is not exist!";
        } else if (ratingDao.insertData(rating)) {
            return "Success!";
        }
        return "Failed!";
    }

}
