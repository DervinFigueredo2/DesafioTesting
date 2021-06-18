package com.desafio.TuCasitaTasaciones.dto.response;

import com.desafio.TuCasitaTasaciones.dto.RoomSquareFeetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseSquareFeetPerRoomDTO {
    private List<RoomSquareFeetDTO> roomsSquareFeet;
}
