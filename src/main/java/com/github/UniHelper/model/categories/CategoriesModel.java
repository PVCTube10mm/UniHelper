package com.github.UniHelper.model.categories;

import java.util.Set;

public interface CategoriesModel {

    void addOrModifyCategory(Category category);

    void deleteCategory(Category category);

    Set<Category> getAllCategories();

    void setCategories(Set<Category> categories);
}
