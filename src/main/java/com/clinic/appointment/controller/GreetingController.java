package com.clinic.appointment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clinic/api/greeting")
public class GreetingController {

    @GetMapping
    public ResponseEntity<String> greeting(){
        return ResponseEntity.ok("greating from api hello");
    }
    @GetMapping("/sayGoodBye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("goodBye from api sayGoodBye");
    }
}
