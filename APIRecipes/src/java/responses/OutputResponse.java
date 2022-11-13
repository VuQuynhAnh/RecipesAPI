/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
public class OutputResponse {

    private String message;
    private NotificationViewModel notificationModel;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationViewModel getNotificationModel() {
        return notificationModel;
    }

    public void setNotificationModel(NotificationViewModel notificationModel) {
        this.notificationModel = notificationModel;
    }

    public OutputResponse(String message, NotificationViewModel notificationModel) {
        this.message = message;
        this.notificationModel = notificationModel;
    }

}
