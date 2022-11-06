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
public class UserFilterRequest {

    private String keyword;
    private String email;
    private String phoneNumber;
    private String displayName;
    private String userName;
    private int sex;
    private int role;
    private int status;
    private boolean sortByIdDESC;
    private boolean sortByTotalRecipeDESC;
    private boolean sortByTotalFollowOtherUserDESC;
    private boolean sortByTotalFollowedByOthersUserDESC;
    private boolean sortByTotalViewsDESC;
    private int pageIndex;
    private int pageSize;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSortByIdDESC() {
        return sortByIdDESC;
    }

    public void setSortByIdDESC(boolean sortByIdDESC) {
        this.sortByIdDESC = sortByIdDESC;
    }

    public boolean isSortByTotalRecipeDESC() {
        return sortByTotalRecipeDESC;
    }

    public void setSortByTotalRecipeDESC(boolean sortByTotalRecipeDESC) {
        this.sortByTotalRecipeDESC = sortByTotalRecipeDESC;
    }

    public boolean isSortByTotalFollowOtherUserDESC() {
        return sortByTotalFollowOtherUserDESC;
    }

    public void setSortByTotalFollowOtherUserDESC(boolean sortByTotalFollowOtherUserDESC) {
        this.sortByTotalFollowOtherUserDESC = sortByTotalFollowOtherUserDESC;
    }

    public boolean isSortByTotalFollowedByOthersUserDESC() {
        return sortByTotalFollowedByOthersUserDESC;
    }

    public void setSortByTotalFollowedByOthersUserDESC(boolean sortByTotalFollowedByOthersUserDESC) {
        this.sortByTotalFollowedByOthersUserDESC = sortByTotalFollowedByOthersUserDESC;
    }

    public boolean isSortByTotalViewsDESC() {
        return sortByTotalViewsDESC;
    }

    public void setSortByTotalViewsDESC(boolean sortByTotalViewsDESC) {
        this.sortByTotalViewsDESC = sortByTotalViewsDESC;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public UserFilterRequest() {
    }

}
