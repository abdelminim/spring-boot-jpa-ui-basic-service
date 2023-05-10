package com.clinic.appointment.service;

import com.clinic.appointment.entity.City;

import java.util.List;

public interface ICityService {

    City findById(Long id);

    City save(City city);

    List<City> findAll();
}
