/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responses;

import java.util.List;
import viewModel.CategoryViewModel;

/**
 *
 * @author DELL
 */
public class CategoryResponse {

    private int totalPage;
    private List<CategoryViewModel> categoryViewModels;

    public CategoryResponse() {
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<CategoryViewModel> getCategoryViewModels() {
        return categoryViewModels;
    }

    public void setCategoryViewModels(List<CategoryViewModel> categoryViewModels) {
        this.categoryViewModels = categoryViewModels;
    }

    public CategoryResponse(int totalPage, List<CategoryViewModel> categoryViewModels) {
        this.totalPage = totalPage;
        this.categoryViewModels = categoryViewModels;
    }

}
