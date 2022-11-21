/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import entity.Users;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DELL
 */
public class UserInputRequest extends Users implements Serializable {

    private List<String> imageInput;

    public List<String> getImageInput() {
        return imageInput;
    }

    public void setImageInput(List<String> imageInput) {
        this.imageInput = imageInput;
    }

    public UserInputRequest() {
    }
}
