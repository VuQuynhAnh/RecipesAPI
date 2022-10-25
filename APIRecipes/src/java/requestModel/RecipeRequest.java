/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requestModel;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RecipeRequest implements Serializable{

    private String keyword;
    private int catId;
    private int authorId;
    private String name;
    private String origin;
    private int minServer;
    private int maxServer;
    private int minTotalViews;
    private int maxTotalViews;
    private String cookTime;
    private int status;

    public RecipeRequest() {
    }

    public RecipeRequest(String keyword, int catId, int authorId, String name, String origin, int minServer, int maxServer, int minTotalViews, int maxTotalViews, String cookTime, int status) {
        this.keyword = keyword;
        this.catId = catId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.minServer = minServer;
        this.maxServer = maxServer;
        this.minTotalViews = minTotalViews;
        this.maxTotalViews = maxTotalViews;
        this.cookTime = cookTime;
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getMinServer() {
        return minServer;
    }

    public void setMinServer(int minServer) {
        this.minServer = minServer;
    }

    public int getMaxServer() {
        return maxServer;
    }

    public void setMaxServer(int maxServer) {
        this.maxServer = maxServer;
    }

    public int getMinTotalViews() {
        return minTotalViews;
    }

    public void setMinTotalViews(int minTotalViews) {
        this.minTotalViews = minTotalViews;
    }

    public int getMaxTotalViews() {
        return maxTotalViews;
    }

    public void setMaxTotalViews(int maxTotalViews) {
        this.maxTotalViews = maxTotalViews;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
