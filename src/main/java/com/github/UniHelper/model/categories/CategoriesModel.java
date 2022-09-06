package com.github.UniHelper.model.categories;

import java.util.ArrayList;

public interface CategoriesModel {

    void addOrModifyCategoryWithSameID(Category category);

    void deleteCategory(Category category);

    ArrayList<Category> getAllCategories();

    void setCategories(ArrayList<Category> categories);
}
