/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import common.FolderNameConstant;
import dao.CategoryDao;
import dao.UploadImageDao;
import dao.UsersDao;
import entity.Category;
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
import viewModel.CategoryViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/category")
public class CategoryService {

    CategoryDao categoryDao = null;
    UploadImageDao uploadImageDao = null;
    UsersDao usersDao = null;
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
    LocalDateTime dateTimeNow = LocalDateTime.now();

    public CategoryService() {
        categoryDao = new CategoryDao();
        uploadImageDao = new UploadImageDao();
        usersDao = new UsersDao();
    }

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryViewModel> getCategories() {
        return categoryDao.getData();
    }

    @GET
    @Path("getLists")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryViewModel> getCategories(
            @QueryParam("keyword") String keyword,
            @QueryParam("isGetAll") boolean isGetAll,
            @QueryParam("sortIdDESC") boolean sortIdDESC,
            @QueryParam("sortNameASC") boolean sortNameASC,
            @QueryParam("sortTotalRecipeDESC") boolean sortTotalRecipeDESC,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize
    ) {
        return categoryDao.getData(keyword, isGetAll, sortIdDESC, sortNameASC, sortTotalRecipeDESC, pageIndex, pageSize);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryViewModel getCategoryById(@QueryParam("id") int id) {
        return categoryDao.getDataById(id);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(final @Context ServletConfig config, Category category) {
        String path = config.getServletContext().getRealPath("/WEB-INF/image");

        // validate
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (!usersDao.checkExistUser(category.getCreateUser())) {
            return "Category createUser with id = " + category.getCreateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(category.getCreateUser())) {
            return "Category createUser with id = " + category.getCreateUser() + " is not a admin!";
        }

        // convert image
        if (category.getImage().length() > 0) {
            String fileName = "cat_" + category.getCreateUser() + "_" + dateTimeNow.format(formatDate);
            category.setImage(uploadImageDao.uploadImage(category.getImage(), path, FolderNameConstant.category, fileName));
        }
        if (categoryDao.insertData(category)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(final @Context ServletConfig config, Category category) {
        String path = config.getServletContext().getRealPath("/WEB-INF/image");

        // validate
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (categoryDao.getDataById(category.getId()).getId() <= 0) {
            return "Category width id = " + category.getId() + " is not exist or deleted!";
        } else if (!usersDao.checkExistUser(category.getUpdateUser())) {
            return "Category updateUser with id = " + category.getUpdateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(category.getUpdateUser())) {
            return "Category updateUser with id = " + category.getUpdateUser() + " is not a admin!";
        }

        // convert image
        if (category.getImage().length() > 0 && !category.getImage().contains(FolderNameConstant.category)) {
            String fileName = "cat_" + category.getCreateUser() + "_" + dateTimeNow.format(formatDate);
            category.setImage(uploadImageDao.uploadImage(category.getImage(), path, FolderNameConstant.category, fileName));
        }
        if (categoryDao.updateData(category)) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@QueryParam("id") int id, @QueryParam("updateUser") int deleteId) {
        if (!categoryDao.checkExistCategory(id)) {
            return "Category width id = " + id + " is not exist or deleted!";
        } else if (!usersDao.checkExistUser(deleteId)) {
            return "Category updateUser with id = " + deleteId + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(deleteId)) {
            return "Category updateUser with id = " + deleteId + " is not a admin!";
        } else if (categoryDao.deleteData(id, deleteId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
