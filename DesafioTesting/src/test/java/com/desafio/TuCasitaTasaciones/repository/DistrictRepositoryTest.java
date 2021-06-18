package com.desafio.TuCasitaTasaciones.repository;

import com.desafio.TuCasitaTasaciones.entities.District;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictRepositoryTest {

    DistrictRepository district = new DistrictRepository();

    DistrictRepositoryTest() throws IOException {
    }

    //Se busca un district por nombre, en caso de existir.
    @Test
    void getDistrictByNameExist() throws NoSuchFieldException {
        //Arrange
        List<District> districts = new ArrayList<>();
        districts.add(new District("Caribe",20.000));
        districts.add(new District("LaGuaira",10.000));
        districts.add(new District("Macuto",15.000));
        districts.add(new District("CatilaLaMar",25.000));

        //Act
        ReflectionTestUtils.setField(district,"districts", districts);
        District result = district.getDistrictByName("Caribe");

        //Assert
        assertEquals(result.getName(),"Caribe");
    }

    //Se busca un district por nombre, en caso de  no existir se arroja NoSuchFieldException.
    @Test
    void getDistrictByNameNoExist() {
        //Arrange
        List<District> districts = new ArrayList<>();
        districts.add(new District("Caribe",20.000));
        districts.add(new District("LaGuaira",10.000));
        districts.add(new District("Macuto",15.000));
        districts.add(new District("CatilaLaMar",25.000));

        //Act
        ReflectionTestUtils.setField(district,"districts", districts);

        //Assert
        assertThrows(NoSuchFieldException.class,()->district.getDistrictByName("Maiquetia"));
    }
}