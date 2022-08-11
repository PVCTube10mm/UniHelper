package com.github.UniHelper.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Note {
    private UUID id;
    private String title;
    private String data;

    public Note(String title, String data){
        this.title = title;
        this.data = data;
        id = UUID.randomUUID();
    }
}
