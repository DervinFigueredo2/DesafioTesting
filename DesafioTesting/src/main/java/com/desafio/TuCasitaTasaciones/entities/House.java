package com.desafio.TuCasitaTasaciones.entities;

import com.desafio.TuCasitaTasaciones.dto.RoomDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class House {
    private String propName;
    private String districtName;
    private double districtPrice;
    private List<RoomDTO> rooms;

}
