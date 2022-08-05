package com.github.UniHelper.model;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Note {
    private String title;
    private String data;
}
