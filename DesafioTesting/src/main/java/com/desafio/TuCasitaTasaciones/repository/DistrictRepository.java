package com.desafio.TuCasitaTasaciones.repository;


import com.desafio.TuCasitaTasaciones.entities.District;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class DistrictRepository implements IDistrictRepository {

    private List<District> districts;

    public DistrictRepository() throws IOException {
        this.districts = loadFromDB();
    }

    private List<District> loadFromDB() throws IOException {
        List<District> ret = null;

        File file = null;

        file = ResourceUtils.getFile("classpath:districts.json");

        var objectMapper = new ObjectMapper();

        ret = objectMapper.readValue(file, new TypeReference<List<District>>() {
        });

        return ret;
    }

    @Override
    public District getDistrictByName(String name) throws NoSuchFieldException {
        return this.districts.stream()
                .filter(district -> district.getName().equals(name))
                .findFirst().orElseThrow((() -> new NoSuchFieldException("El barrio " + name + ", no fue encontrado"))
                );
    }
}
