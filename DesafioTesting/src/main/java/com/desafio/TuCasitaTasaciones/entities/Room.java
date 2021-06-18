package com.desafio.TuCasitaTasaciones.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Room {
    private String name;
    private double width;
    private double length;
}
