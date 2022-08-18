package com.github.UniHelper.model.notes;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Note {

    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String text;
    private String category;

    public Note(String title, String text, String category) {
        this.title = title;
        this.text = text;
        this.category = category;
        id = UUID.randomUUID();
    }

    public Note(Note note) {
        title = note.getTitle();
        text = note.getText();
        category = note.category;
        id = note.getId();
    }
}
