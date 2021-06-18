package com.desafio.TuCasitaTasaciones.service;

import com.desafio.TuCasitaTasaciones.dto.HouseDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseSquareFeetPerRoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseTotalSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseValueDTO;
import com.desafio.TuCasitaTasaciones.repository.IDistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("CalculateService")
@Primary
public class CalculateServiceImpl implements ICalculateService {

  IDistrictRepository districtRepository;

  @Autowired
  public CalculateServiceImpl(IDistrictRepository districtRepositoryParam) {
    this.districtRepository = districtRepositoryParam;
  }

  @Override
  public HouseResponseTotalSquareFeetDTO calculateTotalSquareFeet(HouseDTO house) {
    System.out.println(this);
    var response = new HouseResponseTotalSquareFeetDTO( calculateRoomSquareFeet(house) );
    return response;
  }

  @Override
  public HouseResponseValueDTO calculatePrice(HouseDTO house) throws NoSuchFieldException {
    var square = calculateRoomSquareFeet(house) * priceByDistrict( house.getDistrictName() );
    var response = new HouseResponseValueDTO( square );
    return response;
  }

  @Override
  public RoomDTO getBiggestRoom(HouseDTO house) {
    return calculateBiggestRoom(house);
  }

  @Override
  public HouseResponseSquareFeetPerRoomDTO calculateSquareFeetPerRoom(HouseDTO house) {
    var rooms = new ArrayList<RoomSquareFeetDTO>();

    for (RoomDTO room : house.getRooms()) {
      double squareFeet = getSquareFeet(room);
      rooms.add(new RoomSquareFeetDTO( room.getName(), squareFeet ));
    }

    var response = new HouseResponseSquareFeetPerRoomDTO( rooms );
    return response;
  }

  private double getSquareFeet(RoomDTO room) {
    return room.getWidth() * room.getLength();
  }

  private double calculateRoomSquareFeet(HouseDTO house) {
    double totalSquareFeet = 0;
    for (RoomDTO room : house.getRooms()) {
      totalSquareFeet += getSquareFeet(room);
    }

    return totalSquareFeet;
  }

  private RoomDTO calculateBiggestRoom(HouseDTO house) {
    RoomDTO biggest = null;
    double maxRoom = 0;
    for (RoomDTO room : house.getRooms()) {
      double squareFeet = getSquareFeet(room);
      if (biggest == null || squareFeet > maxRoom){
        biggest = room;
        maxRoom = squareFeet;
      }
    }

    return biggest;
  }

  private Double priceByDistrict(String districtName) throws NoSuchFieldException {
      var price = districtRepository.getDistrictByName(districtName).getPrice();
      return price;
  }
}