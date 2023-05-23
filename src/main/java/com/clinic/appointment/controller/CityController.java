package com.clinic.appointment.controller;

import com.clinic.appointment.entity.City;
import com.clinic.appointment.service.ICityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cityApi/auth/")
public class CityController {

    private final ICityService cityService;

    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "cities/{id}")
    public City getCity(@PathVariable Long id) {

        return cityService.findById(id);
    }

        @GetMapping("welcomeCity")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping(value = "createCity", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public City createCity(@RequestBody @Valid City city) {

        return cityService.save(city);
    }

    @GetMapping(value = "cities")
    public List<City> findAll() {

        return cityService.findAll();
    }
}
