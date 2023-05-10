package com.clinic.appointment;

import com.clinic.appointment.entity.City;
import com.clinic.appointment.repository.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class CityRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CityRunner.class);

    private final CityRepository cityRepository;

    public CityRunner(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void run( String... args ) throws Exception{


        logger.info("Saving cities");

//        cityRepository.save(new City("Cairo", 432000));
//        cityRepository.save(new City("Alex", 149000));
//        cityRepository.save(new City("AboKeer", 128000));
//        cityRepository.save(new City("Shebeen", 412007));
//        cityRepository.save(new City("Tanta", 36400));
    }
}
