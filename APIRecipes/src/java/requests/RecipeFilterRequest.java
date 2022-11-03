/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class RecipeFilterRequest implements Serializable {

    private String keyword;
    private int catId;
    private int authorId;
    private String name;
    private String origin;
    private String ingredient;
    private int minServer;
    private int maxServer;
    private int minTotalViews;
    private int maxTotalViews;
    private int minTotalRating;
    private int maxTotalRating;
    private int minAvgRating;
    private int maxAvgRating;
    private String cookTime;
    private int status;
    private boolean sortByIdDESC;
    private boolean sortByNameASC;
    private boolean sortByServesASC;
    private boolean sortByServesDESC;
    private boolean sortByTotalViewDESC;
    private boolean sortByAvgRatingDESC;
    private boolean sortByTotalRatingDESC;
    private int pageIndex;
    private int pageSize;

    public RecipeFilterRequest(String keyword, int catId, int authorId, String name, String origin, String ingredient, int minServer, int maxServer, int minTotalViews, int maxTotalViews, int minTotalRating, int maxTotalRating, int minAvgRating, int maxAvgRating, String cookTime, int status, boolean sortByIdDESC, boolean sortByNameASC, boolean sortByServesASC, boolean sortByServesDESC, boolean sortByTotalViewDESC, boolean sortByAvgRatingDESC, boolean sortByTotalRatingDESC, int pageIndex, int pageSize) {
        this.keyword = keyword;
        this.catId = catId;
        this.authorId = authorId;
        this.name = name;
        this.origin = origin;
        this.ingredient = ingredient;
        this.minServer = minServer;
        this.maxServer = maxServer;
        this.minTotalViews = minTotalViews;
        this.maxTotalViews = maxTotalViews;
        this.minTotalRating = minTotalRating;
        this.maxTotalRating = maxTotalRating;
        this.minAvgRating = minAvgRating;
        this.maxAvgRating = maxAvgRating;
        this.cookTime = cookTime;
        this.status = status;
        this.sortByIdDESC = sortByIdDESC;
        this.sortByNameASC = sortByNameASC;
        this.sortByServesASC = sortByServesASC;
        this.sortByServesDESC = sortByServesDESC;
        this.sortByTotalViewDESC = sortByTotalViewDESC;
        this.sortByAvgRatingDESC = sortByAvgRatingDESC;
        this.sortByTotalRatingDESC = sortByTotalRatingDESC;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public RecipeFilterRequest() {
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

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
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

    public int getMinTotalRating() {
        return minTotalRating;
    }

    public void setMinTotalRating(int minTotalRating) {
        this.minTotalRating = minTotalRating;
    }

    public int getMaxTotalRating() {
        return maxTotalRating;
    }

    public void setMaxTotalRating(int maxTotalRating) {
        this.maxTotalRating = maxTotalRating;
    }

    public int getMinAvgRating() {
        return minAvgRating;
    }

    public void setMinAvgRating(int minAvgRating) {
        this.minAvgRating = minAvgRating;
    }

    public int getMaxAvgRating() {
        return maxAvgRating;
    }

    public void setMaxAvgRating(int maxAvgRating) {
        this.maxAvgRating = maxAvgRating;
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

    public boolean isSortByIdDESC() {
        return sortByIdDESC;
    }

    public void setSortByIdDESC(boolean sortByIdDESC) {
        this.sortByIdDESC = sortByIdDESC;
    }

    public boolean isSortByNameASC() {
        return sortByNameASC;
    }

    public void setSortByNameASC(boolean sortByNameASC) {
        this.sortByNameASC = sortByNameASC;
    }

    public boolean isSortByServesASC() {
        return sortByServesASC;
    }

    public void setSortByServesASC(boolean sortByServesASC) {
        this.sortByServesASC = sortByServesASC;
    }

    public boolean isSortByServesDESC() {
        return sortByServesDESC;
    }

    public void setSortByServesDESC(boolean sortByServesDESC) {
        this.sortByServesDESC = sortByServesDESC;
    }

    public boolean isSortByTotalViewDESC() {
        return sortByTotalViewDESC;
    }

    public void setSortByTotalViewDESC(boolean sortByTotalViewDESC) {
        this.sortByTotalViewDESC = sortByTotalViewDESC;
    }

    public boolean isSortByAvgRatingDESC() {
        return sortByAvgRatingDESC;
    }

    public void setSortByAvgRatingDESC(boolean sortByAvgRatingDESC) {
        this.sortByAvgRatingDESC = sortByAvgRatingDESC;
    }

    public boolean isSortByTotalRatingDESC() {
        return sortByTotalRatingDESC;
    }

    public void setSortByTotalRatingDESC(boolean sortByTotalRatingDESC) {
        this.sortByTotalRatingDESC = sortByTotalRatingDESC;
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

}
