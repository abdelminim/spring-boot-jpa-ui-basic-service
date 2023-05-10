package com.clinic.appointment.repository;

import com.clinic.appointment.entity.Appointment;
import com.clinic.appointment.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

//    List<City> findByCityId(Long id);

    City save(City city);

    List<City> findAll();
}
