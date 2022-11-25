/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.ArrayList;
import java.util.List;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
public class SaveOutputResponse {

    private String message;
    private List<NotificationViewModel> notificationModels;

    public SaveOutputResponse() {
    }

    public SaveOutputResponse(String message) {
        this.message = message;
        this.notificationModels = new ArrayList<>();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<NotificationViewModel> getNotificationModels() {
        return notificationModels;
    }

    public void setNotificationModels(List<NotificationViewModel> notificationModels) {
        this.notificationModels = notificationModels;
    }

    public SaveOutputResponse(String message, List<NotificationViewModel> notificationModels) {
        this.message = message;
        this.notificationModels = notificationModels;
    }

}
