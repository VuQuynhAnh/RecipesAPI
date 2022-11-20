/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.List;
import viewModel.NotificationViewModel;

/**
 *
 * @author DELL
 */
public class NotificationListResponse {

    private int totalPage;
    private List<NotificationViewModel> notificationViewModels;

    public int getTotalPage() {
        return totalPage;
    }

    public NotificationListResponse() {
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<NotificationViewModel> getCategoryViewModels() {
        return notificationViewModels;
    }

    public void setCategoryViewModels(List<NotificationViewModel> categoryViewModels) {
        this.notificationViewModels = categoryViewModels;
    }

    public NotificationListResponse(int totalPage, List<NotificationViewModel> categoryViewModels) {
        this.totalPage = totalPage;
        this.notificationViewModels = categoryViewModels;
    }
}
