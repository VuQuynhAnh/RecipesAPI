/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import entity.Recipes;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class RecipeInputItem extends Recipes implements Serializable {

    private List<String> imageInput;

    public RecipeInputItem() {
    }

    public List<String> getImageInput() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public RecipeInputItem(int id, int categoryId, int authorId, String name, String origin, int serves, float calories, float fat, float protein, float carbo, String image, int totalViews, String cookTime, int status, Date createDate, int createUser, Date updateDate, int updateUser, List<String> imageInput) {
        super(id, categoryId, authorId, name, origin, serves, calories, fat, protein, carbo, image, totalViews, cookTime, status, createDate, createUser, updateDate, updateUser);
        this.imageInput = imageInput;
    }
}
