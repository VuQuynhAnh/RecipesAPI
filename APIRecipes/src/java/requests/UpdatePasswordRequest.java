/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

/**
 *
 * @author DELL
 */
public class UpdatePasswordRequest {

    private int id;
    private int updateId;
    private String oldPassword;
    private String newPassword;

    public UpdatePasswordRequest(int id, int updateId, String oldPassword, String newPassword) {
        this.id = id;
        this.updateId = updateId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UpdatePasswordRequest() {
    }
}
