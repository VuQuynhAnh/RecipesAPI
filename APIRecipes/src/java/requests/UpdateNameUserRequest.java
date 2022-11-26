/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import java.util.List;

/**
 *
 * @author DELL
 */
public class UpdateNameUserRequest {

    private int id;
    private String userName;
    private String displayName;
    private String description;
    private List<String> imageInput;

    public List<String> getImageInput() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateNameUserRequest() {
    }
}
