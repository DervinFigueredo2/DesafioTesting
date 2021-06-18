package com.desafio.TuCasitaTasaciones.service;

import com.desafio.TuCasitaTasaciones.dto.HouseDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseSquareFeetPerRoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseTotalSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseValueDTO;

public interface ICalculateService {

    HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(HouseDTO house);

    HouseResponseValueDTO calculatePrice(HouseDTO house) throws NoSuchFieldException;

    RoomDTO getBiggestRoom(HouseDTO house);

    HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(HouseDTO house);
}
