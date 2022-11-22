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
import javax.ws.rs.core.UriInfo;
import responses.CategoryListResponse;
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
    public List<CategoryViewModel> getCategories(@Context UriInfo ui) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return categoryDao.getData(url);
    }

    @GET
    @Path("getLists")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryListResponse getCategories(
            @Context UriInfo ui,
            @QueryParam("keyword") String keyword,
            @QueryParam("isGetAll") boolean isGetAll,
            @QueryParam("sortIdDESC") boolean sortIdDESC,
            @QueryParam("sortNameASC") boolean sortNameASC,
            @QueryParam("sortTotalRecipeDESC") boolean sortTotalRecipeDESC,
            @QueryParam("pageIndex") int pageIndex,
            @QueryParam("pageSize") int pageSize
    ) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        List<CategoryViewModel> categoryViewModels = categoryDao.getData(url, keyword, isGetAll, sortIdDESC, sortNameASC, sortTotalRecipeDESC, pageIndex, pageSize);
        int totalCategory = categoryDao.countCategory(keyword, isGetAll);
        int totalPage = totalCategory / pageSize;
        if (totalCategory % pageSize != 0) {
            totalPage += 1;
        }
        return new CategoryListResponse(totalPage, categoryViewModels);
    }

    @GET
    @Path("detail")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryViewModel getCategoryById(@Context UriInfo ui, @QueryParam("id") int id) {
        String url = ui.getBaseUri().toString().replace("/recipesApi/", "/images/");
        return categoryDao.getDataById(url, id);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(final @Context ServletConfig config, Category category) {
        String path = config.getServletContext().getRealPath("/images");

        // validate
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (!usersDao.checkExistUser(category.getCreateUser())) {
            return "Category createUser with id = " + category.getCreateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(category.getCreateUser())) {
            return "Category createUser with id = " + category.getCreateUser() + " is not a admin!";
        }

        // convert image
        category.setImage("_");
        if (category.getImage() != null && category.getImage().length() > 1) {
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
        String path = config.getServletContext().getRealPath("/images");

        // validate
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (categoryDao.getDataById("", category.getId()).getId() <= 0) {
            return "Category width id = " + category.getId() + " is not exist or deleted!";
        } else if (!usersDao.checkExistUser(category.getUpdateUser())) {
            return "Category updateUser with id = " + category.getUpdateUser() + " is not exist or deleted!";
        } else if (!usersDao.isUserAdmin(category.getUpdateUser())) {
            return "Category updateUser with id = " + category.getUpdateUser() + " is not a admin!";
        }

        // convert image
        category.setImage("_");
        if (category.getImage() != null && category.getImage().length() > 1 && !category.getImage().contains(FolderNameConstant.category)) {
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
