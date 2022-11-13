/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

import entity.NotificationType;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class NotificationTypeViewModel extends NotificationType implements Serializable {

    private String createUserDisplay;
    private String updateUserDisplay;

    public NotificationTypeViewModel() {
    }

    public String getCreateUserDisplay() {
        return createUserDisplay;
    }

    public void setCreateUserDisplay(String createUserDisplay) {
        this.createUserDisplay = createUserDisplay;
    }

    public String getUpdateUserDisplay() {
        return updateUserDisplay;
    }

    public void setUpdateUserDisplay(String updateUserDisplay) {
        this.updateUserDisplay = updateUserDisplay;
    }
}
