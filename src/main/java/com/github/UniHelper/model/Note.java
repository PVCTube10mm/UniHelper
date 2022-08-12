package com.github.UniHelper.model;

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

    public Note(String title, String text) {
        this.title = title;
        this.text = text;
        id = UUID.randomUUID();
    }

    public Note(Note note) {
        title = note.getTitle();
        text = note.getText();
        id = note.getId();
    }
}
