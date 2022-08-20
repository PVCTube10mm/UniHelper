package com.github.UniHelper.model.categories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.UniHelper.model.utils.ColorJsonDeserializer;
import com.github.UniHelper.model.utils.ColorJsonSerializer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class DefaultCategoriesModel implements CategoriesModel {

    private static volatile DefaultCategoriesModel instance;

    private final String saveFileName;
    private ArrayList<Category> categories;
    private final ObjectMapper categoryMapper;

    public static DefaultCategoriesModel getInstance() {
        DefaultCategoriesModel result = instance;
        if (result != null) {
            return result;
        }
        synchronized (DefaultCategoriesModel.class) {
            if (instance == null) {
                instance = new DefaultCategoriesModel();
            }
            return instance;
        }
    }

    @Override
    public void addOrModifyCategory(Category category) {
        if (categories.contains(category)) {
            return;
        }
        Category categoryWithSameId = findCategoryById(category.getId());
        if (categoryWithSameId == null) {
            Category categoryCopy = new Category(category);
            categories.add(categoryCopy);
        } else {
            categoryWithSameId.setColor(category.getColor());
            categoryWithSameId.setName(category.getName());
        }
        save();
    }

    @Override
    public void deleteCategory(Category category) {
        categories.remove(category);
        save();
    }

    @Override
    public ArrayList<Category> getAllCategories() {
        return getDeepCategoriesCopy(categories);
    }

    @Override
    public void setCategories(ArrayList<Category> categories) {
        this.categories = getDeepCategoriesCopy(categories);
        save();
    }

    private DefaultCategoriesModel() {
        categories = new ArrayList<>();
        saveFileName = "saved_categories.txt";
        categoryMapper = new ObjectMapper();
        addSerializersToMapper();
        load();
    }

    private void addSerializersToMapper() {
        SimpleModule awtModule = new SimpleModule("AWT Module");
        awtModule.addSerializer(Color.class, new ColorJsonSerializer());
        awtModule.addDeserializer(Color.class, new ColorJsonDeserializer());
        categoryMapper.registerModule(awtModule);
    }

    private Category findCategoryById(UUID id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void load() {
        try {
            categories = categoryMapper.readValue(new File(saveFileName), new TypeReference<>() {
            });
        } catch (IOException e) {
            createNewSaveFile();
        }
    }

    private void save() {
        try (PrintWriter out = new PrintWriter(saveFileName, StandardCharsets.UTF_8)) {
            ObjectWriter writer = categoryMapper.writer().withDefaultPrettyPrinter();
            String json = writer.writeValueAsString(categories);
            out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createNewSaveFile() {
        try {
            File f = new File(saveFileName);
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Category> getDeepCategoriesCopy(ArrayList<Category> originalCategories) {
        ArrayList<Category> deepCopy;
        try {
            deepCopy = categoryMapper
                    .readValue(categoryMapper.writeValueAsString(originalCategories), new TypeReference<>() {
                    });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return deepCopy;
    }
}
