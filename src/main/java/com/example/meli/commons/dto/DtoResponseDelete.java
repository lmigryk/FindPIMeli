package com.example.meli.commons.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class DtoResponseDelete {
    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private int number;
}
