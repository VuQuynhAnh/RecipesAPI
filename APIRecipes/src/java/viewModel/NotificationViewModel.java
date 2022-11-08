/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.Notifications;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class NotificationViewModel extends Notifications implements Serializable {

    private String createUserDisplay;
    private String userDisplay;

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public NotificationViewModel() {
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public String getUserDisplay() {
        return userDisplay;
    }

    public void setUserDisplay(String userDisplay) {
        this.userDisplay = userDisplay;
    }
}
