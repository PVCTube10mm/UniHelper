package com.github.UniHelper.model.categories;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DefaultCategoriesModel implements CategoriesModel {

    private static volatile DefaultCategoriesModel instance;

    private final String saveFileName;
    private Set<Category> categories;

    public static DefaultCategoriesModel getInstance() {
        DefaultCategoriesModel result = instance;
        if (result != null) {
            return result;
        }
        synchronized(DefaultCategoriesModel.class) {
            if (instance == null) {
                instance = new DefaultCategoriesModel();
            }
            return instance;
        }
    }

    @Override
    public void addOrModifyCategory(Category category) {
        Category categoryWithSameId = findCategoryById(category.getId());
        if(categoryWithSameId == null) {
            Category categoryCopy = new Category(category);
            categories.add(categoryCopy);
        }
        else {
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
    public Set<Category> getAllCategories() {
        return getDeepCategoriesCopy(categories);
    }

    @Override
    public void setCategories(Set<Category> categories) {
        this.categories = getDeepCategoriesCopy(categories);
        save();
    }

    private DefaultCategoriesModel() {
        categories = new HashSet<>();
        saveFileName = "saved_categories.txt";
        load();
    }

    private Category findCategoryById(UUID id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private void load() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            categories = mapper.readValue(new File(saveFileName), new TypeReference<>() {});
        } catch (IOException e) {
            createNewSaveFile();
        }
    }

    private void save() {
        try (PrintWriter out = new PrintWriter(saveFileName, StandardCharsets.UTF_8)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
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

    private Set<Category> getDeepCategoriesCopy(Set<Category> originalCategories) {
        Set<Category> deepCopy;

        SimpleModule awtModule = new SimpleModule("AWT Module");
        awtModule.addSerializer(Color.class, new ColorJsonSerializer());
        awtModule.addDeserializer(Color.class, new ColorJsonDeserializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(awtModule);

        try {
            deepCopy = objectMapper
                    .readValue(objectMapper.writeValueAsString(originalCategories), new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return deepCopy;
    }

    private static class ColorJsonSerializer extends JsonSerializer<Color> {

        @Override
        public void serialize(Color value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value == null) {
                gen.writeNull();
                return;
            }
            gen.writeNumber(value.getRGB());
        }
    }

    private static class ColorJsonDeserializer extends JsonDeserializer<Color> {

        @Override
        public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new Color(p.getValueAsInt());
        }
    }
}
