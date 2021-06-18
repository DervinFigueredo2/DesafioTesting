package com.desafio.TuCasitaTasaciones.repository;

import com.desafio.TuCasitaTasaciones.entities.District;

public interface IDistrictRepository {
    District getDistrictByName(String name) throws NoSuchFieldException;

}
