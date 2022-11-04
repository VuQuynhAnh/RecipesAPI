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
    private float minCalories;
    private float maxCalories;
    private float minFat;
    private float maxFat;
    private float minProtein;
    private float maxProtein;
    private float minCarbo;
    private float maxCarbo;
    private String cookTime;
    private int status;
    private boolean sortByIdDESC;
    private boolean sortByNameASC;
    private boolean sortByServesASC;
    private boolean sortByServesDESC;
    private boolean sortByTotalViewDESC;
    private boolean sortByAvgRatingDESC;
    private boolean sortByTotalRatingDESC;
    private boolean sortByCaloriesDESC;
    private boolean sortByFatDESC;
    private boolean sortByProteinDESC;
    private boolean sortByCarbo;
    private int pageIndex;
    private int pageSize;

    public RecipeFilterRequest() {
    }

    public String getKeyword() {
        return keyword;
    }

    public float getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(float minCalories) {
        this.minCalories = minCalories;
    }

    public float getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(float maxCalories) {
        this.maxCalories = maxCalories;
    }

    public float getMinFat() {
        return minFat;
    }

    public void setMinFat(float minFat) {
        this.minFat = minFat;
    }

    public float getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(float maxFat) {
        this.maxFat = maxFat;
    }

    public float getMinProtein() {
        return minProtein;
    }

    public void setMinProtein(float minProtein) {
        this.minProtein = minProtein;
    }

    public float getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(float maxProtein) {
        this.maxProtein = maxProtein;
    }

    public float getMinCarbo() {
        return minCarbo;
    }

    public void setMinCarbo(float minCarbo) {
        this.minCarbo = minCarbo;
    }

    public float getMaxCarbo() {
        return maxCarbo;
    }

    public void setMaxCarbo(float maxCarbo) {
        this.maxCarbo = maxCarbo;
    }

    public boolean isSortByCaloriesDESC() {
        return sortByCaloriesDESC;
    }

    public void setSortByCaloriesDESC(boolean sortByCaloriesDESC) {
        this.sortByCaloriesDESC = sortByCaloriesDESC;
    }

    public boolean isSortByFatDESC() {
        return sortByFatDESC;
    }

    public void setSortByFatDESC(boolean sortByFatDESC) {
        this.sortByFatDESC = sortByFatDESC;
    }

    public boolean isSortByProteinDESC() {
        return sortByProteinDESC;
    }

    public void setSortByProteinDESC(boolean sortByProteinDESC) {
        this.sortByProteinDESC = sortByProteinDESC;
    }

    public boolean isSortByCarbo() {
        return sortByCarbo;
    }

    public void setSortByCarbo(boolean sortByCarbo) {
        this.sortByCarbo = sortByCarbo;
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
