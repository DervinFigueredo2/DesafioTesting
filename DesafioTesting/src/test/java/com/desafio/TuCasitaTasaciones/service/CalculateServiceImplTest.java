package com.desafio.TuCasitaTasaciones.service;

import com.desafio.TuCasitaTasaciones.dto.HouseDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomDTO;
import com.desafio.TuCasitaTasaciones.dto.RoomSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseSquareFeetPerRoomDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseTotalSquareFeetDTO;
import com.desafio.TuCasitaTasaciones.dto.response.HouseResponseValueDTO;
import com.desafio.TuCasitaTasaciones.entities.District;
import com.desafio.TuCasitaTasaciones.repository.IDistrictRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CalculateServiceImplTest {
    @MockBean
    private IDistrictRepository districtRepository =
            Mockito.mock(IDistrictRepository.class);

    @InjectMocks
    private CalculateServiceImpl calculateService =
            new CalculateServiceImpl(districtRepository);

    /**
     * US-0001: Calcular el total de metros cuadrados de una propiedad.
     */
    @Test
    void testCalculateTotalSquareFeet() {
        //arrange
        HouseDTO house = new HouseDTO(
                "Casa",
                "belgrano",
                3000,
                Arrays.asList(
                        new RoomDTO(
                                "patio",
                                3,
                                3
                        ),
                        new RoomDTO(
                                "living",
                                2,
                                1
                        )
                )
        );
        //act
        HouseResponseTotalSquareFeetDTO calculate = calculateService.calculateTotalSquareFeet(house);

        //Assert
        Assertions.assertEquals(11.0, calculate.getSquareFeet());
    }

    /**
     * US-0002: Indicar el valor de una propiedad a partir de sus ambientes y medidas.
     * Tener en cuenta que los precios por metro cuadrado están determinados según el
     * barrio.
     * @throws NoSuchFieldException
     */
    @Test
    void testCalculatePrice() throws NoSuchFieldException {
        //Arrange
        HouseDTO house = new HouseDTO(
                "Casa",
                "belgrano",
                3000,
                Arrays.asList(
                        new RoomDTO(
                                "patio",
                                3,
                                3
                        ),
                        new RoomDTO(
                                "living",
                                2,
                                1
                        )
                )
        );

        District district = new District(
                "belgrano",
                8.0
        );
        HouseResponseValueDTO expectedResponse = new HouseResponseValueDTO(
                88.0 // ( (3 * 3) + (2 * 1) ) * 8
        );

        when(districtRepository.getDistrictByName(house.getDistrictName())).thenReturn(district);

        //Act
        HouseResponseValueDTO response = calculateService.calculatePrice(house);

        //Assert
        verify(districtRepository, atLeast(1)).getDistrictByName(house.getDistrictName());
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void testCalculatePriceNegative() throws Exception {
        //Arrange
        HouseDTO house = new HouseDTO(
                "Casa",
                "belgrano",
                3000,
                Arrays.asList(
                        new RoomDTO(
                                "patio",
                                -3, // negative value
                                3
                        ),
                        new RoomDTO(
                                "living",
                                2,
                                1
                        )
                ));

        District district = new District(
                "belgrano",
                8.0
        );

        when(districtRepository.getDistrictByName(house.getDistrictName())).thenReturn(null);

        //Act
        //Assert
        Assertions.assertThrows(Exception.class, () -> calculateService.calculatePrice(house));
    }

    /**
     * US-0003: Determinar cuál es el ambiente más grande.
     */
    @Test
    void testGetBiggestRoom() {
        //arrange
        HouseDTO house = new HouseDTO(
                "Casa",
                "belgrano",
                3000,
                Arrays.asList(
                        new RoomDTO(
                                "patio",
                                3,
                                3
                        ),
                        new RoomDTO(
                                "living",
                                2,
                                1
                        )
                )
        );

        //act
        RoomDTO calculate = calculateService.getBiggestRoom(house);

        //Assert
        RoomDTO expectative =  new RoomDTO("patio", 3, 3);
        Assertions.assertEquals(expectative, calculate);
    }

    /**
     * US-0004: Determinar la cantidad de metros cuadrados que tiene cada ambiente de
     * una propiedad.
     */
    @Test
    void testCalculateSquareFeetPerRoom() {
        //arrange
        HouseResponseSquareFeetPerRoomDTO result = new HouseResponseSquareFeetPerRoomDTO();
        List<RoomSquareFeetDTO> list = new ArrayList<>();
        list.add(new RoomSquareFeetDTO("patio",9.0));
        list.add(new RoomSquareFeetDTO("living",2.0));
        result.setRoomsSquareFeet(list);
        HouseDTO house = new HouseDTO(
                "Casa",
                "belgrano",
                3000,
                Arrays.asList(
                        new RoomDTO(
                                "patio",
                                3,
                                3
                        ),
                        new RoomDTO(
                                "living",
                                2,
                                1
                        )
                )
        );
        //act
        HouseResponseSquareFeetPerRoomDTO calculate = calculateService.calculateSquareFeetPerRoom(house);

        //Assert
        Assertions.assertEquals(result, calculate);
    }
}