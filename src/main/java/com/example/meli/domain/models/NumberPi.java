package com.example.meli.domain.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class NumberPi implements Serializable{

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
