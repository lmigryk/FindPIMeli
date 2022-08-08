package com.example.meli.domain.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NumberPi {
    @Getter
    @Setter
    private int param;

    @Getter
    @Setter
    private int random;

    @Getter
    @Setter
    private String PiCalc;

}
