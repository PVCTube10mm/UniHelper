package com.github.UniHelper.model.categories;

import lombok.*;

import java.awt.*;
import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    public static final Category NONE = new Category("None", Color.GRAY);

    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String name;
    private Color color;

    public Category(String name, Color color) {
        this.name = name;
        this.color = color;
        id = UUID.randomUUID();
    }

    public Category(Category category) {
        id = category.getId();
        name = category.getName();
        color = category.getColor();
    }
}
