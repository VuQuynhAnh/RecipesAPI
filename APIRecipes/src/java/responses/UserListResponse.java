/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.List;
import viewModel.UsersViewModel;

/**
 *
 * @author DELL
 */
public class UserListResponse {

    private int totalPage;
    private List<UsersViewModel> usersViewModels;

    public UserListResponse() {
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public UserListResponse(int totalPage, List<UsersViewModel> usersViewModels) {
        this.totalPage = totalPage;
        this.usersViewModels = usersViewModels;
    }

    public List<UsersViewModel> getUsersViewModels() {
        return usersViewModels;
    }

    public void setUsersViewModels(List<UsersViewModel> usersViewModels) {
        this.usersViewModels = usersViewModels;
    }

}
