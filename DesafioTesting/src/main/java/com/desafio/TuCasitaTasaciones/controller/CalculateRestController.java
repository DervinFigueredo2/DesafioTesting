package com.desafio.TuCasitaTasaciones.controller;

import com.desafio.TuCasitaTasaciones.dto.HouseDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseSquareFeetPerRoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseTotalSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseValueDTO;
import com.desafio.TuCasitaTasaciones.service.ICalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CalculateRestController {

  @Autowired
  private ICalculateService calculateService;

  /**
   * US-0001: Calcular el total de metros cuadrados de una propiedad
   * @param house
   * @return
   */
  @PostMapping("/calculateTotalSquareFeet")
  public HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(@Valid @RequestBody HouseDTO house){
    return calculateService.calculateTotalSquareFeet(house);
  }

  /**
   * US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.
   * Tener en cuenta que los precios por metro cuadrado están determinados según el
   * barrio.
   * @param house
   * @return
   */
  @PostMapping("/calculatePrice")
  public HouseResponseValueDTO calculatePrice(@Valid @RequestBody HouseDTO house) throws NoSuchFieldException {
    var ret = calculateService.calculatePrice(house);
    return ret;
  }

  /**
   * US-0003: Determinar cuál es el ambiente más grande.
   * @param house
   * @return
   */
  @PostMapping("/getBiggestRoom")
  public RoomDTO getBiggestRoom(@Valid @RequestBody HouseDTO house){
    return calculateService.getBiggestRoom(house);
  }

  /**
   * US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de
   * una propiedad.
   * @param house
   * @return
   */
  @PostMapping("/calculateSquareFeetPerRoom")
  public HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(@Valid @RequestBody HouseDTO house){
    return calculateService.calculateSquareFeetPerRoom(house);
  }

}
