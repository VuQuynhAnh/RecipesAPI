/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.CategoryDao;
import entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import viewModel.CategoryViewModel;

/**
 *
 * @author DELL
 */
@Stateless
@Path("/category")
public class CategoryService {

    CategoryDao dao = null;

    public CategoryService() {
        dao = new CategoryDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryViewModel> getCategories() {
        return dao.getData();
    }

    @GET
    @Path("{keyword}/{isGetAll}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryViewModel> getCategories(@PathParam("keyword") String keyword, @PathParam("isGetAll") boolean isGetAll) {
        return dao.getData(keyword, isGetAll);
    }

    @GET
    @Path("detail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryViewModel getCategoryById(@PathParam("id") int id) {
        return dao.getDataById(id);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String insert(Category category) {
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (dao.insertData(category)) {
            return "Success!";
        }
        return "Failed!";
    }

    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String update(Category category) {
        if (category.getName().trim().length() == 0) {
            return "Category name is requied!";
        } else if (dao.getDataById(category.getId()).getId() <= 0) {
            return "Category width id = " + category.getId() + " is not exist!";
        } else if (dao.updateData(category)) {
            return "Success!";
        }
        return "Failed!";
    }

    @DELETE
    @Path("delete/{id}/{deleteId}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public String delete(@PathParam("id") int id, @PathParam("deleteId") int deleteId) {
        if (!dao.checkExistCategory(id)) {
            return "Category width id = " + id + " is not exist or deleted!";
        } else if (dao.deleteData(id, deleteId)) {
            return "Success!";
        }
        return "Failed!";
    }

}
